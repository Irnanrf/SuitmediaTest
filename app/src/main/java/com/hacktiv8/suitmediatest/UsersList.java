package com.hacktiv8.suitmediatest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class UsersList {

    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("data")
    public List<DataUsers> data = new ArrayList<>();

    public class DataUsers {
        @SerializedName("id")
        public Integer id;
        @SerializedName("first_name")
        public String first_name;
        @SerializedName("last_name")
        public String last_name;
        @SerializedName("email")
        public String email;
        @SerializedName("avatar")
        public String avatar;

    }
}
