package com.cv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private String name;
    private String title;
    private String email;
    private String phone;
    private String location;
    private String summary;
    private String photo;
    private String linkedin;
    private String github;
}