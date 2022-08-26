package com.kien.appdocsach;

import com.kien.lib.model.BookModel;
import com.kien.lib.model.BookOfHistoryModel;
import com.kien.lib.model.ChapterModel;

public interface OnClickItem {
    void ClickBookItem(BookModel.Data bookModel);
    void ClickChapterItem(ChapterModel.Data chapterModel);
    void ClickHistoryItem(BookOfHistoryModel bookOfHistoryModel);
}
