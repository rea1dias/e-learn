package com.example.elearn.service;

import com.example.elearn.model.course.CourseRequest;
import com.example.elearn.model.course.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse create(CourseRequest request);
    CourseResponse update(String title, CourseRequest request);
    void delete(String title);
    List<CourseResponse> get();
}
