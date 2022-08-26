package com.kien.appdocsach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.kien.appdocsach.adapter.LibaryAdapter;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.BookModel;
import com.kien.lib.model.BookOfHistoryModel;
import com.kien.lib.model.ChapterModel;

import static com.kien.lib.RetrofitClient.getRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibaryActivity extends AppCompatActivity {
    RecyclerView rvBookList;
    LibaryAdapter libaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libary_activity);
        rvBookList = findViewById(R.id.rvBookList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvBookList.setLayoutManager(linearLayoutManager);

        Methods methods = getRetrofit().create(Methods.class);
        Call<BookModel> call = methods.GetBookList();
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                List<BookModel.Data> dataList = response.body().getData();
                libaryAdapter = new LibaryAdapter(dataList, new OnClickItem() {
                    @Override
                    public void ClickBookItem(BookModel.Data bookModel) {

                        GoOnBookDetail(bookModel);
                    }

                    @Override
                    public void ClickChapterItem(ChapterModel.Data chapterModel) {

                    }

                    @Override
                    public void ClickHistoryItem(BookOfHistoryModel bookOfHistoryModel) {

                    }
                });
                rvBookList.setAdapter(libaryAdapter);
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {

            }
        });
    }

    private void GoOnBookDetail(BookModel.Data bookModel) {
        Intent intent = new Intent(LibaryActivity.this, BookDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookId", bookModel.getId());
        bundle.putSerializable("bookCategory", bookModel.getCategoryId());
        bundle.putSerializable("bookAuthor", bookModel.getAuthor());
        intent.putExtras(bundle);
        startActivity(intent);

    }
}