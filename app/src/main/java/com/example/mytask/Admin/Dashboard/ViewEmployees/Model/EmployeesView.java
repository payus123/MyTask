package com.example.mytask.Admin.Dashboard.ViewEmployees.Model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mytask.Admin.CountryApi.Api;
import com.example.mytask.Admin.CountryApi.Countries;
import com.example.mytask.Admin.CountryApi.RequestQ;
import com.example.mytask.Admin.Dashboard.ViewEmployees.Adapter.MyViewHolder;
import com.example.mytask.Admin.Dashboard.ViewEmployees.EmplyeeData.EmployeeData;
import com.example.mytask.Admin.SharePref.SharedPref;
import com.example.mytask.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeesView extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static Context mcont;
    private RecyclerView recyclerView;
    private ApiEmployee employeeApi;
    private RecyclerView.Adapter adapter;
    private String Token;
    private SharedPref sharedPref;
    private  ArrayList<Employee> employees = new ArrayList<>();
    private  ArrayList<String> employe1 = new ArrayList<>();
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_view);

        progressDialog =new ProgressDialog(this);

        progressDialog.setMessage("Loading");
        progressDialog.setTitle("Please Wait");
        progressDialog.show();

        sharedPref = new SharedPref(this);
        Token = sharedPref.getToken();

        RequestQ.q(this).add(new StringRequest("https://victorvero.herokuapp.com/employee/list", this::onGetEmployees, this::onGetError) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> customHeaders = new HashMap<>();

                customHeaders.put("Authorization", "Bearer " + Token);

                return customHeaders;
            }});


        mcont =getApplicationContext();


    }

    private void onGetError(VolleyError volleyError) {

    }

    private void onGetEmployees(String s) {
        Gson gson = new Gson();
        employeeApi = gson.fromJson(s, ApiEmployee.class);

        UpdateList(employeeApi);


     LoadRecycler();
     progressDialog.dismiss();

    }

    private void UpdateList(ApiEmployee api) {

        for (Employee employee : employeeApi.getEmployees()) {
            employees.add(employee);
            employe1.add(employee.getLastName()+" "+employee.getFirstName());

        }






    }



    private void LoadRecycler(){
        recyclerView = findViewById(R.id.RecyclerEmployee);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new EmployeeAdapter(employees,this);
        recyclerView.setAdapter(adapter);
    }





    //////////////////// Adapter
    public class EmployeeAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Employee> employeeArrayList;
        private Context context;

        public EmployeeAdapter(ArrayList<Employee> employeeArrayList, Context context) {
            this.employeeArrayList = employeeArrayList;
            this.context = context;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_data,parent,false);
            return new MyViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Employee employee =employeeArrayList.get(position);
            holder.employee.setText(employee.getFirstName()+" "+employee.getLastName());
            holder.desig.setText(employee.getDesignation());
            holder.setimage(employee.getPassport());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String FirstName = employee.getFirstName();
                    String LastName = employee.getLastName();
                    String Gender = employee.getGender();
                    String Passport = employee.getPassport();
                    String Designation = employee.getDesignation();
                    String Dob = employee.getDob().toString();
                    String Country = employee.getCountry();
                    String State = employee.getState();
                    String Address = employee.getAddress();

                    Intent intent = new Intent(getApplicationContext(), EmployeeData.class);
                    intent.putExtra("gender",Gender);
                    intent.putExtra("Dob",Dob);
                    intent.putExtra("designation",Designation);
                    intent.putExtra("address",Address);
                    intent.putExtra("firstname",FirstName);
                    intent.putExtra("lastname",LastName);
                    intent.putExtra("passport",Passport);
                    intent.putExtra("country",Country);
                    intent.putExtra("state",State);

                   startActivity(intent);

                }

                private void onGetError(VolleyError volleyError) {

                }

                private void onGetEmployees(String s) {

                }
            });


        }

        @Override
        public int getItemCount() {
            return employeeArrayList.size();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tool_earch,menu);
        MenuItem menuItem = menu.findItem(R.id.SearchTool);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        String userInput =s.toLowerCase();

        for (Employee employee : employeeApi.getEmployees()) {
            if((employee.getLastName().toLowerCase().contentEquals(userInput))){
                employees.clear();
                employees.add(employee);
                 recyclerView.setAdapter(adapter);
            }else {
                employees.clear();
                recyclerView.setAdapter(adapter);
            }

        }
        if(s==null){
            for (Employee employee : employeeApi.getEmployees()) {
                employees.add(employee);
                recyclerView.setAdapter(adapter);
            }
            }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if(s==null){
            for (Employee employee : employeeApi.getEmployees()) {
                employees.add(employee);
                recyclerView.setAdapter(adapter);

            }
        }








        return false;
    }
}