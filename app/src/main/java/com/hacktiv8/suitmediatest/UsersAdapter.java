package com.hacktiv8.suitmediatest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context context;
    private List<UsersModel> usersList;

    public UsersAdapter(List<UsersModel> usersList) {
        this.usersList = usersList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View theView = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
        context = parent.getContext();
        return new ViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsersModel user = usersList.get(position);
        holder.tvFirstName.setText(user.getFirstName());
        holder.tvLastName.setText(user.getLastName());
        holder.tvEmail.setText(user.getEmail());
        Picasso.get().load(user.getAvatar()).fit().centerCrop().into(holder.imgAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendData1 = new Intent(v.getRootView().getContext(), SecondActivity.class);

                TextView lblUserThird = v.getRootView().findViewById(R.id.txtUserThird);
                String userFullname = user.getFirstName() + " " + user.getLastName();
                sendData1.putExtra("dataNamaUser", userFullname);
                sendData1.putExtra("userName", lblUserThird.getText().toString());
                context.startActivity(sendData1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFirstName, tvLastName, tvEmail;
        public TextView tvUserName;
        public ImageView imgAvatar;

        public ViewHolder(View itemView){
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.user_firstname);
            tvLastName = itemView.findViewById(R.id.user_lastname);
            tvEmail = itemView.findViewById(R.id.user_email);
            imgAvatar = itemView.findViewById(R.id.user_logo);
        }
    }

    public UsersAdapter(Context context, List<UsersModel> usersList){
        this.context = context;
        this.usersList = usersList;
    }

}
