
package com.example.session05.service;

import com.example.session05.dto.CourseResponse;
import com.example.session05.dto.CourseResponseV2;
import com.example.session05.entity.Course;
import com.example.session05.entity.CourseStatus;
import com.example.session05.repository.CourseRepository;
import org.springframework.data.domain.*;
import com.example.session05.dto.PageResponse;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public PageResponse<CourseResponse> getPagedCourses(
            int page, int size, String sortBy, Sort.Direction direction) {

        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";

        Pageable pageable = PageRequest.of(
                page, size, Sort.by(direction, sortBy));

        Page<Course> courses = courseRepository.findAll(pageable);

        Page<CourseResponse> mapped = courses.map(course ->
                new CourseResponse(
                        course.getId(),
                        course.getTitle(),
                        course.getStatus().name()
                )
        );

        return new PageResponse<>(
                mapped.getContent(),
                mapped.getNumber(),
                mapped.getSize(),
                (int) mapped.getTotalElements(),
                mapped.getTotalPages(),
                mapped.isLast()
        );
    }

    public PageResponse<CourseResponse> getPagedCoursesByStatus(
            int page, int size, String sortBy,
            Sort.Direction direction, CourseStatus status) {

        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";

        Pageable pageable = PageRequest.of(
                page, size, Sort.by(direction, sortBy));

        Page<CourseResponse> mapped = courseRepository
                .findAllByStatus(status, pageable)
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getTitle(),
                        course.getStatus().name()
                ));

        return new PageResponse<>(
                mapped.getContent(),
                mapped.getNumber(),
                mapped.getSize(),
                (int) mapped.getTotalElements(),
                mapped.getTotalPages(),
                mapped.isLast()
        );
    }

    public PageResponse<CourseResponseV2> getPagedCoursesV2(
            int page, int size, String sortBy,
            Sort.Direction direction, CourseStatus status) {

        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by(direction, sortBy == null ? "id" : sortBy)
        );

        Page<CourseResponseV2> result =
                courseRepository.findAllProjectedByStatus(status, pageable);

        return new PageResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                (int) result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
    }

}
