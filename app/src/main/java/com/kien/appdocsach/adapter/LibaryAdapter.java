package com.kien.appdocsach.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kien.appdocsach.OnClickItem;
import com.kien.appdocsach.R;
import com.kien.lib.RetrofitClient;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.BookModel;
import com.kien.lib.model.CategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibaryAdapter extends RecyclerView.Adapter<LibaryAdapter.LibaryViewHolder> {
    private List<BookModel.Data> list;
    private OnClickItem onClickItem;
    private String Category;
    public LibaryAdapter(List<BookModel.Data> list, OnClickItem clickItem) {
        this.list = list;
        this.onClickItem = clickItem;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LibaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_book, parent, false);
        return new LibaryAdapter.LibaryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LibaryAdapter.LibaryViewHolder holder, int position) {
        BookModel.Data book = list.get(position);
        if(book == null){
            return;
        }
        holder.txtBookName.setText(book.getBookName());
        holder.txtAuthor.setText("Tác giả: " + book.getAuthor());
        holder.txtDescription.setText(book.getDescription().substring(0,87)+"...");
        Picasso.get().load(RetrofitClient.getBase_Url() + book.getImageUrl()).into(holder.imgvCustomBook);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.ClickBookItem(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }
    public class LibaryViewHolder extends RecyclerView.ViewHolder {

        TextView txtBookName;
        TextView txtDescription;
        TextView txtAuthor;
        ImageView imgvCustomBook;
        LinearLayout layout;

        public LibaryViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.custom_book);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            imgvCustomBook = itemView.findViewById(R.id.imgvCustomBook);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
    /*private  void GetCategoryNameById(String id){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<CategoryModel> call = methods.GetCategoryById(id);
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                List<CategoryModel.Data> dataList = response.body().getData();
                Category = dataList.get(0).getCateName();
                Log.v("",Category);
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.e("onFailure",t.toString());
            }
        });
    }*/
}
