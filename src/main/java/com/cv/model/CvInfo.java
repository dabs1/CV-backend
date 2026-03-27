package com.cv.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "cv_info")
public class CvInfo {

    @Id
    private String id;
    private PersonalInfo personalInfo;
    private List<Experience> experience;
    private List<Education> education;
    private List<SkillGroup> skills;
    private List<Language> languages;

    public CvInfo() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public PersonalInfo getPersonalInfo() { return personalInfo; }
    public void setPersonalInfo(PersonalInfo personalInfo) { this.personalInfo = personalInfo; }

    public List<Experience> getExperience() { return experience; }
    public void setExperience(List<Experience> experience) { this.experience = experience; }

    public List<Education> getEducation() { return education; }
    public void setEducation(List<Education> education) { this.education = education; }

    public List<SkillGroup> getSkills() { return skills; }
    public void setSkills(List<SkillGroup> skills) { this.skills = skills; }

    public List<Language> getLanguages() { return languages; }
    public void setLanguages(List<Language> languages) { this.languages = languages; }
}