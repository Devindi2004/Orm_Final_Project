package org.example.ormfinalproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data                       // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // Default constructor
@AllArgsConstructor         // All-args constructor
public class PaymentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "payment_date")
    private String paymentDate;

    private double amount;

    @Column(name = "payment_method")
    private String paymentMethod;
}
