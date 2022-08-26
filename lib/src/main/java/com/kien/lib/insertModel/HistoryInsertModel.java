package com.kien.lib.insertModel;

public class HistoryInsertModel
{
    private String UpdateDate;

    private String BookId;

    private String UserId;

    private String ChapterId;

    public String getUpdateDate ()
    {
        return UpdateDate;
    }

    public void setUpdateDate (String UpdateDate)
    {
        this.UpdateDate = UpdateDate;
    }

    public String getBookId ()
    {
        return BookId;
    }

    public void setBookId (String BookId)
    {
        this.BookId = BookId;
    }

    public String getUserId ()
    {
        return UserId;
    }

    public void setUserId (String UserId)
    {
        this.UserId = UserId;
    }

    public String getChapterId ()
    {
        return ChapterId;
    }

    public void setChapterId (String ChapterId)
    {
        this.ChapterId = ChapterId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UpdateDate = "+UpdateDate+", BookId = "+BookId+", UserId = "+UserId+", ChapterId = "+ChapterId+"]";
    }
}


