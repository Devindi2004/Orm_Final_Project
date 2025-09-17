package org.example.ormfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor

public class StudentDTO {
    private String studentId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String registerFee;
    private String registrationDate;

}