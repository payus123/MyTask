package com.example.mytask.Admin.Dashboard.AddEmployee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.mytask.Admin.CountryApi.Api;
import com.example.mytask.Admin.CountryApi.Countries;
import com.example.mytask.Admin.CountryApi.RequestQ;
import com.example.mytask.Admin.CountryApi.States;
import com.example.mytask.Admin.Dashboard.AdminDashboard;
import com.example.mytask.Admin.Register.RegisterAdmin3;
import com.example.mytask.Admin.SharePref.SharedPref;
import com.example.mytask.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeRegister3 extends AppCompatActivity {

    private int Gallery_Request_Code = 2;
    private StorageReference mImageStorage;
    private Spinner spinnerCou;
    private Spinner spinnerState;
    private Api api;
    private Countries countries1;
    private String CountryName;
    private String StateName;
    private String ProfileImage;
    private CircleImageView circleImageView;
    private TextInputLayout address;
    private JSONObject object1 = new JSONObject();
    ProgressDialog progressDialog;
    ProgressDialog progressDialog1;
    private String Token;
    private SharedPref sharedPref;


    private String userAddress, userDesig, userFirstName, userLastName, Gender, DoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(this);
        Token = sharedPref.getToken();

        RequestQ.q(this).add(new StringRequest("https://api.printful.com/countries", this::onCountryGet, this::onCountryError));




        setContentView(R.layout.activity_employee_register3);
        mImageStorage = FirebaseStorage.getInstance().getReference();
        spinnerCou = findViewById(R.id.SelectC);
        spinnerState = findViewById(R.id.SelectS);
        circleImageView = findViewById(R.id.profile_image);
        address = findViewById(R.id.Address);
        progressDialog = new ProgressDialog(this);
        progressDialog1 = new ProgressDialog(this);


        ///Get Data of User//
        Bundle data = getIntent().getExtras();

        Intent intent = getIntent();
        userFirstName = data.getString("firstname");
        userLastName = data.getString("lastname");
        userDesig= data.getString("designation");
        userAddress = intent.getStringExtra("address");
        Gender = intent.getStringExtra("gender");
        DoB = intent.getStringExtra("Dob");


        ///GET States
        spinnerCou.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                ArrayList<String> States1 = new ArrayList<>();
                States1.clear();
                CountryName = String.valueOf(spinnerCou.getSelectedItem());


                for (Countries country : api.getResult()) {
                    if (country.getName().equalsIgnoreCase(CountryName)) {
                        countries1 = country;


                        if (countries1.getStates() != null) {
                            for (States states : countries1.getStates()) {
                                ////Get States name
                                if (states != null) {
                                    States1.add(states.getName());
                                }
                            }

                            //Add states to spinner
                            ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>
                                    (getApplicationContext(), android.R.layout.simple_spinner_item,
                                            States1); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter1.setDropDownViewResource(android.R.layout
                                    .simple_spinner_dropdown_item);
                            spinnerState.setAdapter(spinnerArrayAdapter1);
                            StateName = String.valueOf(spinnerState.getSelectedItem());
                        } else {
                            States1.add("No States Available");
                            ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>
                                    (getApplicationContext(), android.R.layout.simple_spinner_item,
                                            States1); //selected item will look like a spinner set from XML
                            spinnerArrayAdapter1.setDropDownViewResource(android.R.layout
                                    .simple_spinner_dropdown_item);
                            spinnerState.setAdapter(spinnerArrayAdapter1);

                            StateName = String.valueOf(spinnerState.getSelectedItem());
                        }

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

    }

    private void onCountryError(VolleyError volleyError) {
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
    }

    private void onCountryGet(String s) {
        Gson gson = new Gson();
        api = gson.fromJson(s, Api.class);

        UpdateList(api);
    }

    private void UpdateList(Api api) {
        ArrayList<String> Contries = new ArrayList<>();
        for (Countries country : api.getResult()) {
            Contries.add(country.getName());

        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        Contries); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinnerCou.setAdapter(spinnerArrayAdapter);


    }


    /////////////////////////////////////
    public void SetImage(View view) {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose profile image"), Gallery_Request_Code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_Request_Code && resultCode == RESULT_OK) {
            Uri ImageUri = data.getData();
            CropImage.activity(ImageUri)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();
                Uri resultUri = result.getUri();
                File imageFilepath = new File(resultUri.getPath());

                Intent intent = getIntent();
                String userF = intent.getStringExtra("firstname");
                String userL = intent.getStringExtra("lastname");

                progressDialog1.setMessage("Loading Passport");
                progressDialog1.setTitle("Please Wait");

                 progressDialog1.show();
                final StorageReference filepath = mImageStorage.child("Profileimages").child(userF + userL + ".jpg");
                filepath.putFile(Uri.fromFile(imageFilepath)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ProfileImage = uri.toString();
                                    Picasso.with(EmployeeRegister3.this).load(ProfileImage).placeholder(R.drawable.placeholder).into(circleImageView);
                                   progressDialog1.dismiss();
                                }
                            });
                        }
                    }
                });


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }


    }

    public void CallNextRegister(View view) {

        progressDialog.setMessage("Creating Employee");
        progressDialog.setTitle("Please Wait");
        progressDialog.show();






        JSONObject object = new JSONObject();


        try {
            object.put("firstName", userFirstName);
            object.put("lastName", userLastName);
            object.put("address", userAddress);
            object.put("designation", userDesig);
            object.put("gender", userFirstName);
            object.put("dob", DoB);
            object.put("passport", ProfileImage);
            object.put("country", CountryName);
            object.put("state", StateName);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("oj", object.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (!object.isNull("passport")) {
                Toast.makeText(getApplicationContext(), "Please Fill All Fields ", Toast.LENGTH_LONG).show();
            } else {
                RequestQ.q(this).add(new JsonObjectRequest("https://victorvero.herokuapp.com/employee/register", object, this::onPostSuccess, this::onPostError)

                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> customHeaders = new HashMap<>();

                        customHeaders.put("Authorization", "Bearer " + Token);

                        return customHeaders;
                    }});

            }
        }

    }

    private void onPostError(VolleyError volleyError) {
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
    }


    private void onPostSuccess(JSONObject object) {
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

        try {
            if(object.getString("respCode").equalsIgnoreCase("00")){
                startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                sharedPref.saveToken(Token);
                sharedPref.saveLogStat(true);
                sharedPref.saveImage(ProfileImage);
                progressDialog.dismiss();
                Activity activity = getParent();
                activity.finish();




            }
            else {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


}