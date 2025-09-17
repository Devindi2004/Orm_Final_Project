package org.example.ormfinalproject.Entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity                     // 🔑 Mark as Entity
@Table(name = "student")    // 🔑 Map to table (default = class name if not specified)
public class Student {

    @Id                     // 🔑 Primary Key
    @Column(name = "student_id")
    private String studentId;   // Make it String, since you’re generating IDs like "S001"

    private String name;
    private String email;
    private String phone;
    private String address;

    @Column(name = "register_fee")
    private String registerFee;

    @Column(name = "registration_date")
    private String registrationDate;
}
