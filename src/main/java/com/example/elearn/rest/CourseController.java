package com.example.elearn.rest;

import com.example.elearn.model.course.CourseRequest;
import com.example.elearn.model.course.CourseResponse;
import com.example.elearn.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PostMapping("/create")
    public ResponseEntity<CourseResponse> create
            (
            @RequestBody CourseRequest request
    ) {
        try {
            CourseResponse response = service.create(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAll() {
        try {
            List<CourseResponse> responses = service.get();
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{title}")
    public ResponseEntity<CourseResponse> update
            (
            @PathVariable String title,
            @RequestBody CourseRequest request
    ) {
        try {
            CourseResponse updated = service.update(title, request);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> delete
            (
            @PathVariable String title
    ) {
        try {
            service.delete(title);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
