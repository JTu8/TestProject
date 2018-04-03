package com.example.htmjs.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by htmjs on 20.3.2018.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private Context context;
    private List<Users> usersList;

    public UsersAdapter(Context _context, List<Users> _users) {

        this.context = _context;
        this.usersList = _users;
    }

    @Override
    public UsersAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.users_list, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.UserViewHolder holder, int position) {
        Users users = usersList.get(position);

        holder.kayttajatunnus.setText(users.getKayttajatunnus());
        holder.nimi.setText(users.getNimi());
        holder.lisatieto.setText(users.getLisatieto());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView kayttajatunnus;
        TextView nimi;
        TextView lisatieto;



        public UserViewHolder(View itemView) {
            super(itemView);

            kayttajatunnus = itemView.findViewById(R.id.tvKayttajatunnus);
            nimi = itemView.findViewById(R.id.tvNimi);
            lisatieto = itemView.findViewById(R.id.tvLisatieto);
        }
    }
}
