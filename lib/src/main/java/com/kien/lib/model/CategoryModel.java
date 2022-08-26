package com.kien.lib.model;

import java.util.List;

public class CategoryModel
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
        private String id;

        private String cateName;

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getCateName ()
        {
            return cateName;
        }

        public void setCateName (String cateName)
        {
            this.cateName = cateName;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", cateName = "+cateName+"]";
        }
    }
}

