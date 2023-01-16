package com.hacktiv8.suitmediatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    boolean isLoading = false;

    TextView txtUserThird;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    UsersAdapter adapter;
    List<UsersModel> usersList = new ArrayList<>();

    InterfaceAPI apiInterface;

    int countPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("Third Screen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUserThird = findViewById(R.id.txtUserThird);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("userName");
            if(value == null){

            } else {
                txtUserThird.setText(value);
            }
        }

        recyclerView = findViewById(R.id.user_recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UsersAdapter(usersList);
        recyclerView.setAdapter(adapter);
        getUserList(1);

        countPage = 2;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == usersList.size() - 1) {
                        getUserList(countPage);
                        countPage++;
                        isLoading = true;
                    }
                }
                isLoading = false;
            }
        });

        ;
    }

    private void getUserList(int countPage){

        apiInterface = RetrofitClient.getRetrofitClient();
        Call<UsersList> call2 = apiInterface.doGetUserList(String.valueOf(countPage));
        call2.enqueue(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {

                UsersList userList = response.body();
//                Integer text = userList.page;
//                Integer total = userList.total;
//                Integer totalPages = userList.totalPages;
                List<UsersList.DataUsers> userDataList = userList.data;

                for (UsersList.DataUsers dataUser : userDataList) {
                    String firstName = String.valueOf(dataUser.first_name);
                    String lastName = String.valueOf(dataUser.last_name);
                    String email = String.valueOf(dataUser.email);
                    String avatar = String.valueOf(dataUser.avatar);

                    usersList.add(new UsersModel(firstName, lastName, email, avatar));
                }

                if(userDataList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Next Page Data is Empty!", Toast.LENGTH_SHORT).show();

                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                call.cancel();
            }
        });
    }

}