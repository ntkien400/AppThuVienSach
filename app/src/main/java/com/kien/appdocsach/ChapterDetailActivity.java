package com.kien.appdocsach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.text.LineBreaker;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kien.lib.RetrofitClient;
import com.kien.lib.insertModel.HistoryInsertModel;
import com.kien.lib.insertModel.UserInsertModelResult;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.ChapterModel;
import com.kien.lib.model.HistoryModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ChapterDetailActivity extends AppCompatActivity {
    TextView txtBookContent;
    TextView txtChapterTitle;
    ImageView imgvBack;
    ImageView imgvListChapter;
    ImageView imgvFont;
    ImageView imgvPreviousChapter;
    ImageView imgvNextChapter;
    ImageView imgvComment;
    ImageView imgvFavorite;
    String chapterNum, bookId, chapterId, userId ;
    int maxChapter;
    HistoryInsertModel data = new HistoryInsertModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail);
        Setlayout();
        SetFunction();
    }
    private void Setlayout(){
        imgvBack = findViewById(R.id.imgvBack);
        imgvListChapter = findViewById(R.id.imgvListChapter);
        imgvFont = findViewById(R.id.imgvFont);
        imgvPreviousChapter = findViewById(R.id.imgvPreviousChapter);
        imgvNextChapter = findViewById(R.id.imgvNextChapter);
        imgvComment = findViewById(R.id.imgvComment);
        imgvFavorite = findViewById(R.id.imgvFavorite);
        txtBookContent = findViewById(R.id.txtBookContent);
        txtChapterTitle = findViewById(R.id.txtChapterTitle);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        chapterId = (String)  bundle.get("chapterId");
        GetChapterById(chapterId);
        bookId = (String) bundle.get("bookId");
        maxChapter = (int) bundle.get("maxChapter");
        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginInfo", MODE_PRIVATE);
        userId = sharedPreferences.getString("UserId",null);
    }
    private void SetFunction(){
        //chuc nang cho nut chuyen chap
        imgvPreviousChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(chapterNum) <=2){
                    imgvPreviousChapter.setVisibility(View.INVISIBLE);
                    GetChapterByNumber(bookId, String.valueOf(Integer.parseInt(chapterNum)  -1));

                }
                else {
                    GetChapterByNumber(bookId, String.valueOf(Integer.parseInt(chapterNum)  -1));
                    imgvNextChapter.setVisibility(View.VISIBLE);
                }

            }
        });
        imgvNextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(chapterNum) >= maxChapter-1){
                    imgvNextChapter.setVisibility(View.INVISIBLE);
                    GetChapterByNumber(bookId, String.valueOf(Integer.parseInt(chapterNum)  +1));
                }
                else {
                    GetChapterByNumber(bookId, String.valueOf(Integer.parseInt(chapterNum)  +1));
                    imgvPreviousChapter.setVisibility(View.VISIBLE);

                }
            }
        });
        //chuc nang cho nut danh sach
        imgvListChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToChapterList();
            }
        });
        //chuc nang nut back
        imgvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToHome();
            }
        });


    }
    private void GetChapterByNumber(String id, String chapNum){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<ChapterModel> call = methods.GetChapterByNumber(id,chapNum);
        call.enqueue(new Callback<ChapterModel>() {
            @Override
            public void onResponse(Call<ChapterModel> call, Response<ChapterModel> response) {
                List<ChapterModel.Data> dataList = response.body().getData();
                String content = dataList.get(0).getChapContent();
                String title = dataList.get(0).getChapNumber();
                txtBookContent.setText(content);
                txtChapterTitle.setText("Chương "+title);
                chapterNum = dataList.get(0).getChapNumber();
                DeleteHistory(chapterId);
                chapterId = dataList.get(0).getId();
                data.setChapterId(chapterId);
                data.setBookId(bookId);
                data.setUserId(userId);
                data.setUpdateDate(String.valueOf(Calendar.getInstance().getTime()));
                InsertHistory(data);

            }

            @Override
            public void onFailure(Call<ChapterModel> call, Throwable t) {
                Log.e("onFailure",t.toString());
            }
        });
    }
    private void GetChapterById(String id){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<ChapterModel> call = methods.GetChapterById(id);
        call.enqueue(new Callback<ChapterModel>() {
            @Override
            public void onResponse(Call<ChapterModel> call, Response<ChapterModel> response) {
                ChapterModel.Data chapter = response.body().getData().get(0);
                txtBookContent.setText(chapter.getChapContent());
                txtBookContent.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                txtChapterTitle.setText("Chương "+chapter.getChapNumber());
                chapterNum  = chapter.getChapNumber();
                chapterId = chapter.getId();
                if(Integer.parseInt(chapterNum) <2){
                    imgvPreviousChapter.setVisibility(View.INVISIBLE);
                }
                if(Integer.parseInt(chapterNum) >= maxChapter){
                    imgvNextChapter.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ChapterModel> call, Throwable t) {

            }
        });
    }
    private  void BackToChapterList(){
        Intent intent = new Intent(ChapterDetailActivity.this, ChapterListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookId", bookId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void BackToHome(){
        Intent intent = new Intent(ChapterDetailActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    private void InsertHistory(HistoryInsertModel historyInsertModel){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<UserInsertModelResult> call = methods.InserHistory(historyInsertModel);
        call.enqueue(new Callback<UserInsertModelResult>() {
            @Override
            public void onResponse(Call<UserInsertModelResult> call, Response<UserInsertModelResult> response) {
                Log.e("onResponse",response.toString());
            }

            @Override
            public void onFailure(Call<UserInsertModelResult> call, Throwable t) {

            }
        });
    }
    private void DeleteHistory(String id){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<UserInsertModelResult> call = methods.DeleteHistory(id);
        call.enqueue(new Callback<UserInsertModelResult>() {
            @Override
            public void onResponse(Call<UserInsertModelResult> call, Response<UserInsertModelResult> response) {
                Log.e("onResponse",response.toString());
            }

            @Override
            public void onFailure(Call<UserInsertModelResult> call, Throwable t) {

            }
        });
    }
}