package com.example.elearn.service.impl;

import com.example.elearn.entity.Course;
import com.example.elearn.enums.course.Status;
import com.example.elearn.mapper.CourseMapper;
import com.example.elearn.model.course.CourseRequest;
import com.example.elearn.model.course.CourseResponse;
import com.example.elearn.repository.CourseRepository;
import com.example.elearn.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Override
    public CourseResponse create(CourseRequest request) {
        Course course = mapper.toEntity(request);
        return mapper.toResponse(repository.save(course));
    }

    @Override
    public List<CourseResponse> get() {
        List<Course> courses = repository.findAll();
        return courses.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse update(String title, CourseRequest request) {
        Course course = repository.findByTitle(title);
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        if (course.getStatus() == null) {
            course.setStatus(Status.valueOf(request.getStatus()));
        }
        return mapper.toResponse(repository.save(course));
    }

    @Override
    public void delete(String title) {
        Course course = repository.findByTitle(title);
        repository.delete(course);
    }
}
