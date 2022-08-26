package com.kien.lib.insertModel;

public class UserInsertModel
{
    private String UserTitle;

    private String Email;

    private String Username;

    private String Image;

    private String Password;

    private  String Name;

    public String getName() {return Name;}

    public void setName(String name) {Name = name;}

    public String getUserTitle ()
    {
        return UserTitle;
    }

    public void setUserTitle (String UserTitle)
    {
        this.UserTitle = UserTitle;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getUsername ()
    {
        return Username;
    }

    public void setUsername (String Username)
    {
        this.Username = Username;
    }

    public String getImage ()
    {
        return Image;
    }

    public void setImage (String Image)
    {
        this.Image = Image;
    }

    public String getPassword ()
    {
        return Password;
    }

    public void setPassword (String Password)
    {
        this.Password = Password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UserTitle = "+UserTitle+", Email = "+Email+", Username = "+Username+", Image = "+Image+", Password = "+Password+", Name = "+Name+"]";
    }
}

