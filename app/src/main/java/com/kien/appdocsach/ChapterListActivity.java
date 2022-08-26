package com.kien.appdocsach;

import static com.kien.lib.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kien.appdocsach.adapter.ChapterAdapter;
import com.kien.lib.RetrofitClient;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.BookModel;
import com.kien.lib.model.BookOfHistoryModel;
import com.kien.lib.model.ChapterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterListActivity extends AppCompatActivity {
    RecyclerView rvListChapter;
    ChapterAdapter chapterAdapter;
    String bookId, bookAuthor;
    int maxChapter;
    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_list_activity);
        rvListChapter = findViewById(R.id.rvListChapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListChapter.setLayoutManager(linearLayoutManager);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        bookId = (String)bundle.get("bookId") ;
        bookAuthor = (String)bundle.get("bookAuthor");
        GetChapterByBook(bookId);

    }
    private void GetChapterByBook(String id){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<ChapterModel> call = methods.GetChapterByBook(id);
        call.enqueue((new Callback<ChapterModel>() {
            @Override
            public void onResponse(Call<ChapterModel> call, Response<ChapterModel> response) {
                List<ChapterModel.Data> dataList = response.body().getData();
                maxChapter = dataList.size();
                chapterAdapter = new ChapterAdapter(dataList, new OnClickItem() {
                    @Override
                    public void ClickBookItem(BookModel.Data bookModel) {

                    }

                    @Override
                    public void ClickChapterItem(ChapterModel.Data chapterModel) {
                        GoOnChapterDetail(bookId,chapterModel);
                    }

                    @Override
                    public void ClickHistoryItem(BookOfHistoryModel bookOfHistoryModel) {

                    }
                });
                rvListChapter.setAdapter(chapterAdapter);
            }

            @Override
            public void onFailure(Call<ChapterModel> call, Throwable t) {

            }
        }));
    }
    private void GoOnChapterDetail(String id, ChapterModel.Data chapterModel){
        Intent intent = new Intent(ChapterListActivity.this, ChapterDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chapterNum", chapterModel.getChapNumber());
        bundle.putSerializable("bookId", id);
        bundle.putSerializable("chapterId", chapterModel.getId());
        bundle.putSerializable("maxChapter", maxChapter);
        bundle.putSerializable("bookAuthor", bookAuthor);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
