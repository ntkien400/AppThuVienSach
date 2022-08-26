package com.kien.lib.model;

import java.util.List;

public class BookModel
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
        private String updateDate;

        private String uploadDate;

        private String author;

        private String imageUrl;

        private String description;

        private String id;

        private String bookName;

        private String categoryId;

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

        public String getAuthor ()
        {
            return author;
        }

        public void setAuthor (String author)
        {
            this.author = author;
        }

        public String getImageUrl ()
        {
            return imageUrl;
        }

        public void setImageUrl (String imageUrl)
        {
            this.imageUrl = imageUrl;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getBookName ()
        {
            return bookName;
        }

        public void setBookName (String bookName)
        {
            this.bookName = bookName;
        }

        public String getCategoryId ()
        {
            return categoryId;
        }

        public void setCategoryId (String categoryId)
        {
            this.categoryId = categoryId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [updateDate = "+updateDate+", uploadDate = "+uploadDate+", author = "+author+", imageUrl = "+imageUrl+", description = "+description+", id = "+id+", bookName = "+bookName+", categoryId = "+categoryId+"]";
        }
    }
}

