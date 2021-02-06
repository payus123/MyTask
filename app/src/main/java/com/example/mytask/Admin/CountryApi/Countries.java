package com.example.mytask.Admin.CountryApi;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Countries {
    private String name;
    private String code;
    private ArrayList<States> states;
}
