package org.example.ormfinalproject.model;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CourseDTO {
    private long courseId;
    private String name;
    private String duration;
    private String fee;

}
