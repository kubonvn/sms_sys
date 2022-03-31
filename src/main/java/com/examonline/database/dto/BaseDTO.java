package com.examonline.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    private Long id;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private  String updatedBy;
}
