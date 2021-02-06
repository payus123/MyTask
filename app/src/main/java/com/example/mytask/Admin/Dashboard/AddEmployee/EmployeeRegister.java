package com.example.mytask.Admin.Dashboard.AddEmployee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytask.Admin.Dashboard.AdminDashboard;
import com.example.mytask.Admin.Register.RegisterAdmin;
import com.example.mytask.Admin.Register.RegisterAdmin2;
import com.example.mytask.Admin.SharePref.SharedPref;
import com.example.mytask.R;
import com.google.android.material.textfield.TextInputLayout;

public class EmployeeRegister extends AppCompatActivity {
    private ImageView leftarrow;
    private Button next;
    private TextView Title;
    private TextView dontHaveText;
    private TextView loginHereText;


    private TextInputLayout address;
    private TextInputLayout designation;
    private TextInputLayout firstname;
    private TextInputLayout lastname;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_employee_register);


        leftarrow = findViewById(R.id.leftarrow);
        next = findViewById(R.id.Next);
        Title = findViewById(R.id.CreateAcc);
        dontHaveText = findViewById(R.id.DontHave);
        loginHereText = findViewById(R.id.LoginHere);

        designation = findViewById(R.id.Designation);
        address = findViewById(R.id.FirstName);
        firstname = findViewById(R.id.FirstName);
        lastname = findViewById(R.id.LastName);




    }

    public void CallNextRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), EmployeeRegister2.class);


        String Designation = designation.getEditText().getText().toString();
        String Address = address.getEditText().getText().toString();
        String FirstName = firstname.getEditText().getText().toString();
        String LastName = lastname.getEditText().getText().toString();

        intent.putExtra("designation",Designation);
        intent.putExtra("address",Address);
        intent.putExtra("firstname",FirstName);
        intent.putExtra("lastname",LastName);


        Pair[] pairs = new Pair[5];

        pairs[4] = new Pair<View,String>(leftarrow,"leftArrow");
        pairs[3] = new Pair<View,String>(Title,"TransitionCreateAcc");
        pairs[2] = new Pair<View,String>(next,"Next");



        startActivity(intent);
    }

}