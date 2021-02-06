package com.example.mytask.Admin.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mytask.Admin.CountryApi.RequestQ;
import com.example.mytask.Admin.Dashboard.AdminDashboard;
import com.example.mytask.Admin.Register.RegisterAdmin;
import com.example.mytask.Admin.SharePref.SharedPref;
import com.example.mytask.R;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private String Username;
    private String Password;
    private Button loginButton;
    private JSONObject object1 = new JSONObject();

    private TextInputLayout username;
    private TextInputLayout password;
    ProgressDialog progressDialog;
    private String Token;
    private SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data =getIntent().getExtras();





        sharedPref = new SharedPref(this);
        if (sharedPref.getLogStat()==true){
            startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
            finish();
        }
        setContentView(R.layout.activity_login2);


        username = findViewById(R.id.UserName);
        password = findViewById(R.id.Password);
        loginButton = findViewById(R.id.Login1);
        progressDialog =new ProgressDialog(this);

    }

    public void Logi(View view) {
        Username = username.getEditText().getText().toString();
        Password = password.getEditText().getText().toString();
        progressDialog.show();


        try {
            object1.put("username",Username);
            object1.put("password",Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQ.q(this).add(new JsonObjectRequest("https://victorvero.herokuapp.com/admin/login", object1,this::onLoginSuccess, this::onLoginError));


    }
    private void onLoginError(VolleyError volleyError) {

    }

    private void onLoginSuccess(JSONObject object) {
        progressDialog.setMessage("Logging in Administrator");
        progressDialog.setTitle("Please Wait");
        progressDialog.show();


        try {
            if(object.getString("respCode").equalsIgnoreCase("00")){
                Token = object.getString("respBody");
                startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                sharedPref.saveToken(Token);
                sharedPref.saveLogStat(true);
                progressDialog.dismiss();
                finish();




            }
            else {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    public void CallNextRegister(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterAdmin.class));
    }
}