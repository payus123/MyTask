package com.example.mytask.Admin.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mytask.Admin.Dashboard.AddEmployee.EmployeeRegister;
import com.example.mytask.Admin.Dashboard.ViewEmployees.Model.EmployeesView;
import com.example.mytask.Admin.Login.Login;
import com.example.mytask.Admin.Register.RegisterAdmin3;
import com.example.mytask.Admin.SharePref.SharedPref;
import com.example.mytask.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminDashboard extends AppCompatActivity {
private SharedPref sharedPref;
    private CircleImageView circleImageView;
    private  String Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        sharedPref = new SharedPref(this);
        Bundle data =getIntent().getExtras();

           if(data!=null) {
               Image = sharedPref.getImage();
           }
        circleImageView =findViewById(R.id.profile_image);
        Picasso.with(AdminDashboard.this).load(Image).placeholder(R.drawable.placeholder).into(circleImageView);

    }

    public void Add(View view) {
        startActivity(new Intent(getApplicationContext(), EmployeeRegister.class));
    }

    public void Logout(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        sharedPref.saveLogStat(false);
        sharedPref.saveImage(null);
        finish();


    }

    public void ViewEmp(View view) {

        startActivity(new Intent(getApplicationContext(),EmployeesView.class));
    }
}