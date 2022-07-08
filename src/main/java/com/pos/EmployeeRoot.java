package com.pos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeRoot {

    @JsonProperty("EmpId")
    private int EmpId;
    private String Name;
    private ArrayList<Address> Addresses;
}
