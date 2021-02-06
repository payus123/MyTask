package com.example.mytask.Admin.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytask.Admin.Dashboard.AdminDashboard;
import com.example.mytask.Admin.SharePref.SharedPref;
import com.example.mytask.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterAdmin extends AppCompatActivity {

    private ImageView leftarrow;
    private Button next;
    private TextView Title;
    private TextView dontHaveText;
    private TextView loginHereText;


    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout firstname;
    private TextInputLayout lastname;
    private SharedPref sharedPref;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(this);
        setContentView(R.layout.activity_register_admin);




        leftarrow = findViewById(R.id.leftarrow);
        next = findViewById(R.id.Next);
        Title = findViewById(R.id.CreateAcc);
        dontHaveText = findViewById(R.id.DontHave);
        loginHereText = findViewById(R.id.LoginHere);

        username = findViewById(R.id.UserName);
        password = findViewById(R.id.Password);
        firstname = findViewById(R.id.FirstName);
        lastname = findViewById(R.id.LastName);




    }

    public void CallNextRegister(View view) {
        Intent intent = new Intent(getApplicationContext(),RegisterAdmin2.class);


        String Username = username.getEditText().getText().toString();
        String Password = password.getEditText().getText().toString();
        String FirstName = firstname.getEditText().getText().toString();
        String LastName = lastname.getEditText().getText().toString();

        intent.putExtra("username",Username);
        intent.putExtra("password",Password);
        intent.putExtra("firstname",FirstName);
        intent.putExtra("lastname",LastName);


        Pair[] pairs = new Pair[5];

        pairs[4] = new Pair<View,String>(leftarrow,"leftArrow");
        pairs[3] = new Pair<View,String>(Title,"TransitionCreateAcc");
        pairs[2] = new Pair<View,String>(next,"Next");
        pairs[1] = new Pair<View,String>(dontHaveText,"DontHave");
        pairs[0] = new Pair<View,String>(loginHereText,"LoginHere");

        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(RegisterAdmin.this,pairs);

        startActivity(intent,options.toBundle());
    }

}