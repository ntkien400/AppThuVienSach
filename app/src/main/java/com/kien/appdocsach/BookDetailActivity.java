package com.kien.appdocsach;

import static com.kien.lib.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kien.appdocsach.adapter.BookLiteAdapter;
import com.kien.appdocsach.adapter.ChapterAdapter;
import com.kien.lib.RetrofitClient;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.BookModel;
import com.kien.lib.model.BookOfHistoryModel;
import com.kien.lib.model.ChapterModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {
    RecyclerView rvAuthorBook;
    BookLiteAdapter bookLiteAdapter;
    TextView txtBookDetailName;
    TextView txtBookDetailAuthor;
    TextView txtBookChapterNumber;
    TextView txtBookDetailUpdateDate;
    TextView txtBookDetailDescription;
    TextView txtChapterList;
    ImageView imgvBook;
    String bookAuthor, bookId;
    int maxChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_activity);
        txtBookDetailName = findViewById(R.id.txtBookDetailName);
        txtBookDetailAuthor =findViewById(R.id.txtBookDetailAuthor);
        txtBookChapterNumber = findViewById(R.id.txtBookChapterNumber);
        txtBookDetailUpdateDate = findViewById(R.id.txtBookDetailUpdateDate);
        txtBookDetailDescription = findViewById(R.id.txtBookDetailDescription);
        txtChapterList = findViewById(R.id.txtChapterList);
        rvAuthorBook = findViewById(R.id.rvAuthorBook);
        imgvBook = findViewById(R.id.imgvBook);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvAuthorBook.setLayoutManager(linearLayoutManager);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        bookAuthor = (String)bundle.get("bookAuthor");
        bookId = (String)bundle.get("bookId");
        txtBookDetailDescription.setText((String)bundle.get("bookDescription"));
        txtBookDetailAuthor.setText(bookAuthor);
        GetChapterAmount(bookId);
        GetBookById(bookId);
        GetBookByAuthor(bookAuthor);
        txtChapterList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoOnChapterList();
            }
        });


    }
    private void GetBookById(String id){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<BookModel> call = methods.GetBookById(id);
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                Log.e("onResponse", response.toString());
                List<BookModel.Data> dataList = response.body().getData();
                BookModel.Data book = dataList.get(0);
                txtBookDetailName.setText(book.getBookName());
                txtBookDetailUpdateDate.setText("Ngày cập nhật: "+book.getUpdateDate().substring(0,16));
                txtBookDetailDescription.setText(book.getDescription());
                Picasso.get().load(RetrofitClient.getBase_Url() + book.getImageUrl()).into(imgvBook);

            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {

            }
        });
    }
    private void GetBookByAuthor(String name){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<BookModel> call = methods.GetBookByAuthor(name);
        call.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                Log.e("onResponse", response.toString());
                List<BookModel.Data> dataList = response.body().getData();

                bookLiteAdapter = new BookLiteAdapter(dataList, new OnClickItem() {
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
                rvAuthorBook.setAdapter(bookLiteAdapter);

            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {

            }
        });
    }
    private  void GetChapterAmount(String id){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<ChapterModel> call = methods.GetChapterByBook(id);
        call.enqueue((new Callback<ChapterModel>() {
            @Override
            public void onResponse(Call<ChapterModel> call, Response<ChapterModel> response) {
                List<ChapterModel.Data> dataList = response.body().getData();
                maxChapter = dataList.size();
                txtBookChapterNumber.setText("Số chương: "+String.valueOf(maxChapter));
            }

            @Override
            public void onFailure(Call<ChapterModel> call, Throwable t) {

            }
        }));
    }
    private  void  GoOnChapterList(){
        Intent intent = new Intent(BookDetailActivity.this, ChapterListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookId", bookId);
        bundle.putSerializable("bookAuthor",bookAuthor);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void GoOnBookDetail(BookModel.Data bookModel) {
        Intent intent = new Intent(BookDetailActivity.this, BookDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookId", bookModel.getId());
        bundle.putSerializable("bookAuthor", bookModel.getAuthor());
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
