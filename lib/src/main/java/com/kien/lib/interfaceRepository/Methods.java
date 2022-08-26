package com.kien.lib.interfaceRepository;

import com.kien.lib.insertModel.HistoryInsertModel;
import com.kien.lib.insertModel.UserInsertModel;
import com.kien.lib.insertModel.UserInsertModelResult;
import com.kien.lib.model.BookModel;
import com.kien.lib.model.CategoryModel;
import com.kien.lib.model.ChapterModel;
import com.kien.lib.model.HistoryModel;
import com.kien.lib.model.LoginModelResult;
import com.kien.lib.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Methods {
    @GET("api/Category/get-category/{id}")
    Call<CategoryModel> GetCategoryById(@Path("id") String id);
    //Book
    @GET("api/Book/get-book-list")
    Call<BookModel> GetBookList();
    @GET("api/Book/get-book-by-author/{author}")
    Call<BookModel> GetBookByAuthor(@Path("author") String name);
    @GET("api/Book/get-book-by-id/{bookId}")
    Call<BookModel> GetBookById(@Path("bookId") String id);
    //Chapter
    @GET("api/Chapter/get-chapter-by-id")
    Call<ChapterModel> GetChapterById(@Query("id") String id);
    @GET("api/Chapter/get-chapter-list/{bookId}")
    Call<ChapterModel> GetChapterByBook(@Path("bookId") String bookId);
    @GET("api/Chapter/get-chapter-list/{bookId}/{chapterNum}")
    Call<ChapterModel> GetChapterByNumber(@Path("bookId") String bookId, @Path("chapterNum") String chapterNum);
    //User
    @POST("api/User/insert-user")
    Call<UserInsertModelResult> CreateUser(@Body UserInsertModel userInsertModel);
    @POST("api/User/login")
    Call<LoginModelResult> Login (@Query("username") String Username, @Query("password") String Password);
    @GET("api/User/get-user-by-id")
    Call<UserModel> GetUserById(@Query("id") String Id);
    @PUT("api/User/update-user")
    Call<UserInsertModelResult> UpdateUser(@Query("id") String id,@Body UserInsertModel userInsertModel);
    //History
    @GET("api/History/get-history-list-by-user")
    Call<HistoryModel> GetHistoryList(@Query("id") String id);
    @DELETE("api/History/delete-history")
    Call<UserInsertModelResult> DeleteHistory(@Query("id") String id);
    @POST("api/History/insert-history")
    Call<UserInsertModelResult> InserHistory(@Body HistoryInsertModel historyInsertModel);
}
