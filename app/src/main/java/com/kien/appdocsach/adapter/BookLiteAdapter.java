package com.kien.appdocsach.adapter;

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
import com.kien.lib.model.BookModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookLiteAdapter extends RecyclerView.Adapter<BookLiteAdapter.BookLiteViewHolder> {
    private List<BookModel.Data> list;
    private OnClickItem onClickItem;
    public BookLiteAdapter(List<BookModel.Data> list, OnClickItem clickItem) {
        this.list = list;
        this.onClickItem = clickItem;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BookLiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_booklite, parent, false);
        return new BookLiteAdapter.BookLiteViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BookLiteAdapter.BookLiteViewHolder holder, int position) {
        BookModel.Data book = list.get(position);
        if(book == null){
            return;
        }
        holder.txtBookLiteName.setText(book.getBookName());
        Picasso.get().load(RetrofitClient.getBase_Url() + book.getImageUrl()).into(holder.imgvBookLite);
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
    public class BookLiteViewHolder extends RecyclerView.ViewHolder {

        TextView txtBookLiteName;
        ImageView imgvBookLite;
        LinearLayout layout;

        public BookLiteViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.custom_booklite);
            txtBookLiteName = itemView.findViewById(R.id.txtBookLiteName);
            imgvBookLite =itemView.findViewById(R.id.imgvBookLite);
        }
    }
}
