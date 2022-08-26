package com.kien.lib.model;

import java.util.List;

public class ChapterModel
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
        private String chapNumber;

        private String updateDate;

        private String uploadDate;

        private BookModel book;

        private String id;

        private String chapContent;

        private String chapName;

        private String bookId;

        public String getChapNumber ()
        {
            return chapNumber;
        }

        public void setChapNumber (String chapNumber)
        {
            this.chapNumber = chapNumber;
        }

        public String getUpdateDate ()
        {
            return updateDate;
        }

        public void setUpdateDate (String updateDate)
        {
            this.updateDate = updateDate;
        }

        public String getUploadDate ()
        {
            return uploadDate;
        }

        public void setUploadDate (String uploadDate)
        {
            this.uploadDate = uploadDate;
        }

        public BookModel getBook ()
        {
            return book;
        }

        public void setBook (BookModel book)
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

        public String getChapContent ()
        {
            return chapContent;
        }

        public void setChapContent (String chapContent)
        {
            this.chapContent = chapContent;
        }

        public String getChapName ()
        {
            return chapName;
        }

        public void setChapName (String chapName)
        {
            this.chapName = chapName;
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
            return "ClassPojo [chapNumber = "+chapNumber+", updateDate = "+updateDate+", uploadDate = "+uploadDate+", book = "+book+", id = "+id+", chapContent = "+chapContent+", chapName = "+chapName+", bookId = "+bookId+"]";
        }
    }
}
