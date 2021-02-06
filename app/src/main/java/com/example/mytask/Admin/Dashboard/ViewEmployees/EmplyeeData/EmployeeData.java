package com.example.mytask.Admin.Dashboard.ViewEmployees.EmplyeeData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytask.Admin.Dashboard.ViewEmployees.Model.EmployeesView;
import com.example.mytask.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeData extends AppCompatActivity {
    String userFirstName ,userLastName,userDesig,userAddress,Gender,DoB,Country,State,Passport;

    private TextView UserFirstName;
    private TextView UserLastName;
    private TextView UserDesig;
    private TextView UserAddress;
    private TextView gender;
    private TextView doB;
    private TextView country;
    private TextView state;
    private CircleImageView passport;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_data);

        UserFirstName =findViewById(R.id.FirstNameText);
        UserLastName =findViewById(R.id.LastNameText);
        UserDesig =findViewById(R.id.DesignationText);
        gender =findViewById(R.id.GenderText);
        doB =findViewById(R.id.DateOfBirthText);
        country =findViewById(R.id.CountryText);
        state =findViewById(R.id.StateText);
        passport =findViewById(R.id.profile_image);
        UserAddress =findViewById(R.id.AddressText);

        Bundle data = getIntent().getExtras();
           if(data!=null) {
               Intent intent = getIntent();
               userFirstName = data.getString("firstname");
               userLastName = data.getString("lastname");
               userDesig = data.getString("designation");
               userAddress = intent.getStringExtra("address");
               Gender = intent.getStringExtra("gender");
               DoB = intent.getStringExtra("Dob");
               Country = intent.getStringExtra("country");
               State = intent.getStringExtra("state");
               Passport = intent.getStringExtra("passport");

               UserFirstName.setText(userFirstName);
               UserLastName.setText(userLastName);
               UserDesig.setText(userDesig);
               UserAddress.setText(userAddress);
               gender.setText(Gender);
               doB.setText(DoB);
               country.setText(Country);
               state.setText(State);
               Picasso.with(EmployeesView.mcont).load(Passport).placeholder(R.drawable.placeholder).into(passport);




           }
           else {
               Toast.makeText(getApplicationContext(), "Contries.get(2)", Toast.LENGTH_LONG).show();
           }


    }
}