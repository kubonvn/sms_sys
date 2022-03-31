package com.examonline.database.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseBO {
    private Long id;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private  String updatedBy;
}
