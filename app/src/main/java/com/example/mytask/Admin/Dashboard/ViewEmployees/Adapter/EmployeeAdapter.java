package com.example.mytask.Admin.Dashboard.ViewEmployees.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mytask.Admin.CountryApi.RequestQ;
import com.example.mytask.Admin.Dashboard.ViewEmployees.EmplyeeData.EmployeeData;
import com.example.mytask.Admin.Dashboard.ViewEmployees.Model.Employee;
import com.example.mytask.Admin.Dashboard.ViewEmployees.Model.EmployeesView;
import com.example.mytask.Admin.SharePref.SharedPref;
import com.example.mytask.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


