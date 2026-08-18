package com.cv.config;

import com.cv.model.*;
import com.cv.repository.CvRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(CvRepository repository) {
        return args -> {
            // Delete old "main" document if it exists to force update with translated details
            repository.deleteById("main");
            
            System.out.println("Initializing database...");
            CvInfo cvInfo = new CvInfo();
            cvInfo.setId("main");

            PersonalInfo personalInfo = new PersonalInfo(
                "Tomás Rodrigues Francisco",
                "Software Engineer",
                "tom4s.fr4ncisco@gmail.com",
                "+351 935 081 663",
                "Torres Vedras, Portugal",
                "Computer Engineering graduate from ISCTE-IUL with a strong interest in technology and problem-solving. I enjoy tackling challenging problems and turning ideas into practical solutions. Currently seeking a new professional opportunity where I can apply my skills, continue learning, and grow professionally.",
                "",
                "https://www.linkedin.com/in/tomasrfrancisco",
                "https://github.com/dabs1"
            );
            cvInfo.setPersonalInfo(personalInfo);

            // Personal projects
            Experience currentProject = new Experience(
                "",
                "Personal Projects",
                "",
                "",
                "Projects developed in my personal time",
                Arrays.asList(
                    "Interactive CV Web Application (2026)::Developed a full-stack application with a RESTful Spring Boot backend, a MongoDB database, and a React and Tailwind CSS frontend.",
                    "Penha Garcia Summer School Website (2024)::Developed a website to showcase the village of Penha Garcia, built as part of a summer school initiative."
                )
            );

            Experience otherProjects = new Experience(
                "",
                "University Projects",
                "2023",
                "2026",
                "Projects for academic courses during the Computer Engineering degree",
                Arrays.asList(
                    "Sudoku in Java::Developed a fully functional Sudoku game, focusing on object-oriented design patterns.",
                    "ViniSys Relational Database::Designed and implemented a relational database in SQL for a management system.",
                    "Donkey Kong Clone in Java::Built an application applying advanced object-oriented software engineering principles.",
                    "ViniSys Requirements Engineering::Drafted software requirements for a management system and implemented them in OutSystems.",
                    "Computer Networks Simulator::Configured and simulated routing protocols and network interactions within a virtual environment.",
                    "Atari Go Clone::Solved algorithmic challenges using pure functional programming concepts.",
                    "Multi-Agent System (SMA) Simulator::Developed a simulator to model autonomous agents, exploring their behaviors and interactions within the environments.",
                    "Fullstack Java Web Application::Created a complete web platform integrating a RESTful Java backend with a dynamic Vanilla JavaScript and CSS frontend.",
                    "Data Analysis::Processed, analyzed, and visualized datasets to extract meaningful trends and business insights.",
                    "Kahoot Clone in Java::Built a real-time, multi-threaded quiz application utilizing network sockets for distributed communication.",
                    "Enterprise Systems Integration::Developed an integration project between different data sources and web services."
                )
            );
            cvInfo.setExperience(Arrays.asList(currentProject, otherProjects));

            // Academic timeline mapping transitions professionally
            Education lei = new Education(
                "ISCTE - University Institute of Lisbon",
                "Degree in Computer Engineering",
                "2022",
                "2026"
            );
            Education iselYear1 = new Education(
                "ISEL - Lisbon Higher Institute of Engineering",
                "1st year of Electronics, Telecommunications and Computer Engineering",
                "2021",
                "2022"
            );
            cvInfo.setEducation(Arrays.asList(lei, iselYear1));

            // Extracurricular details (Lisboa Games Week Volunteer)
            Experience lgwVolunteer = new Experience(
                "Lisboa Games Week",
                "Event Operations Volunteer",
                "2022",
                "",
                "Volunteer work at Portugal's largest gaming convention.",
                Arrays.asList(
                    "Provided logistical support, assisted attendees, and supervised game testing areas."
                )
            );
            
            Experience pgWebsite = new Experience(
                "Summer School of ISCTE in Penha Garcia",
                "Website for village in Castelo Branco",
                "2024",
                "",
                "Interdisciplinary project focusing on environmental analysis and local history.",
                Arrays.asList(
                    "Led the development of a website dedicated to the history of Penha Garcia using the SCRUM framework. The project involved analyzing air, noise, and water quality, and documenting historical smuggling routes."
                )
            );
            cvInfo.setVolunteer(Arrays.asList(lgwVolunteer, pgWebsite));

            // Skill Groups
            SkillGroup languages = new SkillGroup(
                "Languages",
                Arrays.asList("Java", "Scala", "JavaScript", "CSS", "Python")
            );
            SkillGroup frameworks = new SkillGroup(
                "Frameworks & Libraries",
                Arrays.asList("Spring Boot", "React", "Tailwind CSS")
            );
            SkillGroup tools = new SkillGroup(
                "Databases & Tools",
                Arrays.asList("MongoDB", "Git", "Maven", "SQL", "PostgreSQL", "OutSystems")
            );
            cvInfo.setSkills(Arrays.asList(languages, frameworks, tools));

            // Languages
            Language portuguese = new Language("Portuguese", "Native");
            Language english = new Language("English", "");
            cvInfo.setLanguages(Arrays.asList(portuguese, english));

            repository.save(cvInfo);
            System.out.println("CV initialized in database successfully.");
        };
    }
}
