package com.kien.lib.model;

public class BookOfHistoryModel
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


