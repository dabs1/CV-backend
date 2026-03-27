package com.cv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience { // (Repeat this pattern for PersonalInfo, Education, etc.)
    private String company;
    private String position;
    private String startDate;
    private String endDate;
    private String description;
    private List<String> highlights;
}