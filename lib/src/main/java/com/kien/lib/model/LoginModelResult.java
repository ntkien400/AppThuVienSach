package com.kien.lib.model;

public class LoginModelResult
{
    private String message;

    private UserModel user;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public UserModel getUser ()
    {
        return user;
    }

    public void setUser (UserModel user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", user = "+user+"]";
    }
}

