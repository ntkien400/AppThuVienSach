package com.kien.appdocsach;

import static com.kien.lib.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kien.appdocsach.adapter.ChapterAdapter;
import com.kien.appdocsach.adapter.HistoryAdapter;
import com.kien.appdocsach.adapter.LibaryAdapter;
import com.kien.lib.RetrofitClient;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.BookModel;
import com.kien.lib.model.BookOfHistoryModel;
import com.kien.lib.model.ChapterModel;
import com.kien.lib.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvHistoryList;
    HistoryAdapter historyAdapter;
    Button btnLibary;
    Button btnProfile;
    String UserId;
    int maxChapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Setlayout();
        GetHistory();
    }
    private void GetHistory(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginInfo", MODE_PRIVATE);
        UserId = sharedPreferences.getString("UserId", null);
        if(UserId !=null){
            Methods methods = getRetrofit().create(Methods.class);
            Call<HistoryModel> call = methods.GetHistoryList(UserId);
            call.enqueue(new Callback<HistoryModel>() {
                @Override
                public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                    List<HistoryModel.Data> dataList = response.body().getData();
                    List<BookOfHistoryModel> listBook = new ArrayList<>();
                    for (HistoryModel.Data data : dataList) {
                        listBook.add(data.getBook());
                    }
                    historyAdapter = new HistoryAdapter(listBook, new OnClickItem() {
                        @Override
                        public void ClickBookItem(BookModel.Data bookModel) {

                        }

                        @Override
                        public void ClickChapterItem(ChapterModel.Data chapterModel) {

                        }

                        @Override
                        public void ClickHistoryItem(BookOfHistoryModel bookOfHistoryModel) {
                            GetMaxChapter(bookOfHistoryModel.getId(), dataList.get(0));
                        }
                    });
                    rvHistoryList.setAdapter(historyAdapter);
                }

                @Override
                public void onFailure(Call<HistoryModel> call, Throwable t) {

                }
            });
        }

    }
    private void Setlayout(){
        rvHistoryList = findViewById(R.id.rvHistoryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvHistoryList.setLayoutManager(linearLayoutManager);
        btnLibary = findViewById(R.id.btnLibary);
        btnProfile = findViewById(R.id.btnProfile);

        btnLibary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToLibaryPage();
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoOnProfile();
            }
        });
    }

    private void GoOnProfile() {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void GoToLibaryPage() {
        Intent intent = new Intent(HomeActivity.this, LibaryActivity.class);
        startActivity(intent);
    }
    private void GetMaxChapter(String id, HistoryModel.Data historyModel){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<ChapterModel> call = methods.GetChapterByBook(id);
        call.enqueue((new Callback<ChapterModel>() {
            @Override
            public void onResponse(Call<ChapterModel> call, Response<ChapterModel> response) {
                List<ChapterModel.Data> dataList = response.body().getData();
                maxChapter = dataList.size();
                GoOnChapterDetail(historyModel);
            }

            @Override
            public void onFailure(Call<ChapterModel> call, Throwable t) {

            }
        }));
    }
    private void GoOnChapterDetail(HistoryModel.Data historyModel) {
        Intent intent = new Intent(HomeActivity.this, ChapterDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("maxChapter",maxChapter);
        bundle.putSerializable("chapterId", historyModel.getChapterId());
        bundle.putSerializable("bookId", historyModel.getBookId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
