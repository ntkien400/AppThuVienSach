package com.kien.appdocsach.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kien.appdocsach.OnClickItem;
import com.kien.appdocsach.R;
import com.kien.lib.model.ChapterModel;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private List<ChapterModel.Data> list;
    private OnClickItem onClickItem;

    public ChapterAdapter(List<ChapterModel.Data> list, OnClickItem clickItem){
        this.list = list;
        this.onClickItem = clickItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChapterAdapter.ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chapter, parent, false);
        return new ChapterAdapter.ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ChapterViewHolder holder, int position) {
        ChapterModel.Data chapter = list.get(position);
        if(chapter == null){
            return;
        }
        holder.txtChapNumber.setText("Chapter "+chapter.getChapNumber()+":");
        holder.txtChapName.setText(chapter.getChapName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.ClickChapterItem(chapter);
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
    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView txtChapNumber;
        TextView txtChapName;
        LinearLayout layout;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.custom_chapter);
            txtChapNumber = itemView.findViewById(R.id.txtChapNumber);
            txtChapName = itemView.findViewById(R.id.txtChapName);
        }
    }
}
