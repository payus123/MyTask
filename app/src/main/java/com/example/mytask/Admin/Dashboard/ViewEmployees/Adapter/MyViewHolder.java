package com.example.mytask.Admin.Dashboard.ViewEmployees.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytask.Admin.Dashboard.ViewEmployees.Model.EmployeesView;
import com.example.mytask.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyViewHolder extends RecyclerView.ViewHolder {
  public TextView employee;
  public TextView desig;
  public CircleImageView image;
    public static View mView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mView =itemView;
         employee =(TextView) itemView.findViewById(R.id.Employee);
         desig =(TextView) itemView.findViewById(R.id.desig);


    }
    public  void setimage(String profileimage) {
        image =(CircleImageView) mView.findViewById(R.id.passport);
        Picasso.with(EmployeesView.mcont).load(profileimage).placeholder(R.drawable.placeholder).into(image);


    }
}
