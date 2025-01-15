package com.example.elearn.mapper;

import com.example.elearn.entity.Course;
import com.example.elearn.enums.course.Status;
import com.example.elearn.model.course.CourseRequest;
import com.example.elearn.model.course.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    Course toEntity(CourseRequest request);

    Course toEntity(CourseResponse response);

    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    CourseResponse toResponse(Course course);

    @Named("stringToStatus")
    default Status stringToStatus(String status) {
        if (status == null) {
            return null;
        }
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException  e) {
            throw new IllegalArgumentException("Invalid status value: " + status, e);
        }
    }

    @Named("statusToString")
    default String statusToString(Status status) {
        return status == null ? null : status.name();
    }

}
