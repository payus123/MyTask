package com.example.mytask.Admin.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytask.R;

import java.util.Date;

public class RegisterAdmin2 extends AppCompatActivity {
    private ImageView leftarrow;
    private Button next;
    private TextView Title;
    private TextView dontHaveText;
    private TextView loginHereText;

    private String Gender = null;
    private RadioGroup radioGroup;
    private DatePicker datePicker;

    private String date;
    private String user,userP,userF,userL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin2);
        leftarrow = findViewById(R.id.leftarrow);
        next = findViewById(R.id.Next);
        Title = findViewById(R.id.CreateAcc);
        dontHaveText = findViewById(R.id.DontHave);
        loginHereText = findViewById(R.id.LoginHere);
        radioGroup =findViewById(R.id.RadioGender);
        datePicker = findViewById(R.id.datePicker);

        Bundle data =getIntent().getExtras();

        Intent intent = getIntent();
         userF = data.getString("firstname");
         userL = data.getString("lastname");
         user = data.getString("username");
         userP = intent.getStringExtra("password");



        Toast.makeText(this, userF+userL+user, Toast.LENGTH_LONG).show();






    }


    public void LoginPage(View view) {
    }

    public void CallNextRegister(View view) {
        Intent intent = new Intent(getApplicationContext(),RegisterAdmin3.class);
        int id =radioGroup.getCheckedRadioButtonId();
        //get sex
        switch (id){
            case R.id.male:
                Gender = "Male";
                break;
            case R.id.female:
                Gender ="Female";
                break;

            case R.id.others:
                Gender ="Others";
                break;

        }
        //get age
        int day =datePicker.getDayOfMonth();
        int month =(datePicker.getMonth()+1);
        int year =datePicker.getYear();











       if(day<10&month<10) {
           date = (year+"-"+"0"+day+"-"+"0"+month);
       }
       else  if (day<10&& month>=10){
           date = (year+"-"+"0"+day+"-"+month);
        }
       else  if (day>=10&& month<10){
           date = (year+"-"+day+"-"+"0"+month);
       }
       else {
           date = (year+"-"+ day+"-"+month);
       }



        Pair[] pairs = new Pair[5];

        pairs[0] = new Pair<View,String>(leftarrow,"leftArrow");
        pairs[1] = new Pair<View,String>(Title,"TransitionCreateAcc");
        pairs[2] = new Pair<View,String>(next,"Next");
        pairs[3] = new Pair<View,String>(dontHaveText,"DontHave");
        pairs[4] = new Pair<View,String>(loginHereText,"LoginHere");

        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(RegisterAdmin2.this,pairs);

        intent.putExtra("gender",Gender);
        intent.putExtra("Dob",date);

        intent.putExtra("username",user);
        intent.putExtra("password",userP);
        intent.putExtra("firstname",userF);
        intent.putExtra("lastname",userL);


        startActivity(intent,options.toBundle());


    }
    }
