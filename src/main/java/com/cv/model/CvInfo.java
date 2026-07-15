package com.cv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "cv_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvInfo {

    @Id
    private String id;
    private PersonalInfo personalInfo;
    private List<Experience> experience;
    private List<Education> education;
    private List<SkillGroup> skills;
    private List<Language> languages;
    private List<Experience> volunteer;
}