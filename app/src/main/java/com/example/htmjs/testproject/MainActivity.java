package com.example.htmjs.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String userUrl = "http://10.211.114.168/jerephp/Mobiiliohjelmointi/get_users.php";
    List<Users> _users;
    RecyclerView recyclerView;
    Button lisaa;
    Button haeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvUsers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        _users = new ArrayList<>();

        loadUsers();

        lisaa = findViewById(R.id.btnAddActivity);

        lisaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
                MainActivity.this.startActivity(intent);
            }
        });

        haeID = findViewById(R.id.btnGetByID);

        haeID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetByID.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void loadUsers() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, userUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("Jere", response);
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("user");

                    for(int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        _users.add(new Users(
                                object.getInt("ID"),
                                object.getString("kayttajatunnus"),
                                object.getString("nimi"),
                                object.getString("lisatieto")
                        ));

                    }

                    UsersAdapter adapter = new UsersAdapter(MainActivity.this, _users);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Jere", "Error: " + error.getMessage());
            }
        }
        );

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
