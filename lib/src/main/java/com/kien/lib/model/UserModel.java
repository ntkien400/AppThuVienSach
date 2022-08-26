package com.kien.lib.model;


import java.util.List;

public class UserModel
{
    private String userTitle;

    private String image;

    private String password;

    private String id;

    private String email;

    private String username;
    private  String name;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getUserTitle ()
    {
        return userTitle;
    }

    public void setUserTitle (String userTitle)
    {
        this.userTitle = userTitle;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userTitle = "+userTitle+", image = "+image+", password = "+password+", id = "+id+", email = "+email+", username = "+username+", name = "+name+"]";
    }
}


