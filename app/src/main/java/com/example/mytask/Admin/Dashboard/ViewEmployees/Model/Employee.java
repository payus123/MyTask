package com.example.mytask.Admin.Dashboard.ViewEmployees.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private Date dob;
    private String passport;
    private String address;
    private String country;
    private String state;

}