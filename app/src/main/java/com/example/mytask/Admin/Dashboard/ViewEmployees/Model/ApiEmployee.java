package com.example.mytask.Admin.Dashboard.ViewEmployees.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiEmployee {
    private String code;
    private ArrayList<Employee> employees;
}
