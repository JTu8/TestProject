package com.example.htmjs.testproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddUser extends AppCompatActivity {

    private ProgressDialog progressDialog;

    EditText kayttajanimi;
    EditText nimi;
    EditText salasana;
    EditText lisatieto;
    Button lisaa;

    String KayttajanimiHolder;
    String NimiHolder;
    String SalasanaHolder;
    String LisatietoHolder;

    private static String lisaaKayttajaUrl = "http://10.211.114.168/jerephp/Mobiiliohjelmointi/create_user.php";

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        kayttajanimi = findViewById(R.id.editTextKayttajatunnus);
        nimi = findViewById(R.id.editTextNimi);
        salasana = findViewById(R.id.editTextSalasana);
        lisatieto = findViewById(R.id.editTextLisatieto);

        lisaa = findViewById(R.id.btnAdd);

        requestQueue = Volley.newRequestQueue(AddUser.this);

        progressDialog = new ProgressDialog(AddUser.this);


        lisaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Lisätään käyttäjä");
                progressDialog.show();

                GetValueFromEditText();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, lisaaKayttajaUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressDialog.dismiss();
                                Log.d("Jere", response);
                                Toast.makeText(AddUser.this, response, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {


                                progressDialog.dismiss();


                                Toast.makeText(AddUser.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {


                        Map<String, String> params = new HashMap<String, String>();


                        params.put("kayttajatunnus", KayttajanimiHolder);
                        params.put("nimi", NimiHolder);
                        params.put("salasana", SalasanaHolder);
                        params.put("lisatieto", LisatietoHolder);

                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(AddUser.this);
                requestQueue.add(stringRequest);
            }
        });
    }

    public void GetValueFromEditText() {

        KayttajanimiHolder = kayttajanimi.getText().toString().trim();
        NimiHolder = nimi.getText().toString().trim();
        SalasanaHolder = salasana.getText().toString().trim();
        LisatietoHolder = lisatieto.getText().toString().trim();
    }
}
