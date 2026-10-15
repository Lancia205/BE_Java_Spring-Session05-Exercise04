
package com.example.session05.dto;

import com.example.session05.entity.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
