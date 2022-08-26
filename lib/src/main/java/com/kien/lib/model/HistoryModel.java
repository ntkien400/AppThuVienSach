package com.kien.lib.model;

import java.util.List;

public class HistoryModel
{
    private List<Data> data;

    private String status;

    public List<Data> getData ()
    {
        return data;
    }

    public void setData (List<Data> data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
    public class Data
    {
        private ChapterModel chapter;

        private String updateDate;

        private String chapterId;

        private BookOfHistoryModel book;

        private String id;

        private String userId;

        private UserModel user;

        private String bookId;

        public ChapterModel getChapter ()
    {
        return chapter;
    }

        public void setChapter (ChapterModel chapter)
        {
            this.chapter = chapter;
        }

        public String getUpdateDate ()
        {
            return updateDate;
        }

        public void setUpdateDate (String updateDate)
        {
            this.updateDate = updateDate;
        }

        public String getChapterId ()
        {
            return chapterId;
        }

        public void setChapterId (String chapterId)
        {
            this.chapterId = chapterId;
        }

        public BookOfHistoryModel getBook ()
    {
        return book;
    }

        public void setBook (BookOfHistoryModel book)
        {
            this.book = book;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getUserId ()
        {
            return userId;
        }

        public void setUserId (String userId)
        {
            this.userId = userId;
        }

        public UserModel getUser ()
    {
        return user;
    }

        public void setUser (UserModel user)
        {
            this.user = user;
        }

        public String getBookId ()
        {
            return bookId;
        }

        public void setBookId (String bookId)
        {
            this.bookId = bookId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [chapter = "+chapter+", updateDate = "+updateDate+", chapterId = "+chapterId+", book = "+book+", id = "+id+", userId = "+userId+", user = "+user+", bookId = "+bookId+"]";
        }
    }
}

