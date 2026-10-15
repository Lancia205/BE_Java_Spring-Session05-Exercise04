
package com.example.session05.controller;

import com.example.session05.dto.ApiResponse;
import com.example.session05.dto.CourseResponse;
import com.example.session05.dto.CourseResponseV2;
import com.example.session05.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.session05.dto.PageResponse;
import com.example.session05.entity.CourseStatus;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }



    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status) {

        PageResponse<CourseResponse> data =
                courseService.getPagedCoursesByStatus(
                        page, size, sortBy, direction, status);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Success", data)
        );
    }

    @GetMapping("/v2")
    public ApiResponse<PageResponse<CourseResponseV2>> getCoursesV2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status) {

        PageResponse<CourseResponseV2> data =
                courseService.getPagedCoursesV2(page, size, sortBy, direction, status);

        return new ApiResponse<>(200, "Success", data);
    }
}
