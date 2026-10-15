package com.example.session05.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String title;
    private String status;

    public CourseResponse(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }
}
