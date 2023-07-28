package com.backend.inventotest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestModel implements Serializable {

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private int age;

}
