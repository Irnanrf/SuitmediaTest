package com.hacktiv8.suitmediatest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface InterfaceAPI {
    @GET("/users")
    Call<List<UsersModel>> getUsers();

    @GET("/api/users?")
    Call<UsersList> doGetUserList(@Query("page") String page);
}
