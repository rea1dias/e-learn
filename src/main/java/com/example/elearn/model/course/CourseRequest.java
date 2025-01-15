package com.example.elearn.model.course;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CourseRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private String status;
}
