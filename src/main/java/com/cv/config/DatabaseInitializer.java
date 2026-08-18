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

            // Pesonal projects
            Experience currentProject = new Experience(
                "",
                "Personal Projects",
                "",
                "",
                "Projects developed in my personal time.",
                Arrays.asList(
                    "Interactive CV Web Application (2026)::Designed and implemented a RESTful Spring Boot backend with MongoDB and built a responsive React/Tailwind frontend.",
                    "Penha Garcia Summer School Website (2024)::Implemented a website for the village of Penha Garcia."
                )
            );

            Experience otherProjects = new Experience(
                "",
                "University Projects",
                "2023",
                "2026",
                "Projects for academic courses during the Computer Engineering degree.",
                Arrays.asList(
                    "Sudoku in Java::Enhanced understanding of object-oriented programming while creating a Sudoku game implementation, focused on robust game logic and advanced Java concepts.",
                    "ViniSys Relational Database::Strengthened data modeling and SQL proficiency by designing and implementing a comprehensive relational database for an advanced wine management system.",
                    "Advanced OOP in Java::Solidified core software engineering principles by building a robust Java application demonstrating advanced design patterns and object-oriented architecture.",
                    "ViniSys Requirements Engineering::Developed crucial systems analysis skills by drafting comprehensive software specifications and requirements (Caderno de Encargos) for a complex wine management platform.",
                    "Computer Networks Simulator::Gained hands-on experience in network architecture by configuring and simulating complex routing protocols and network interactions.",
                    "Functional Programming Solutions::Expanded algorithmic problem-solving capabilities by addressing complex challenges utilizing pure functional programming paradigms.",
                    "Multi-Agent System (SMA) Simulator::Explored artificial intelligence concepts by developing a simulator to model autonomous agents, their intricate behaviors, and complex environmental interactions.",
                    "Fullstack Java Web Application::Demonstrated end-to-end development capabilities by creating a complete web platform utilizing a RESTful Java backend and a dynamic Vanilla JavaScript/CSS frontend.",
                    "Comprehensive Data Analysis::Honed data science skills by processing, analyzing, and visualizing complex datasets to extract meaningful trends and business insights.",
                    "Kahoot Clone in Java::Mastered concurrent and distributed programming by implementing a real-time, multi-threaded Kahoot-style quiz application utilizing network sockets.",
                    "Enterprise Systems Integration (PISID)::Strengthened enterprise-level architecture skills by building an integration project that effectively connects and manages disparate data sources and web services."
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
                "Volunteer work at the largest gaming convention in Portugal.",
                Arrays.asList(
                    "Provided logistical support in public assistance and supervised game testing areas."
                )
            );
            
            Experience pgWebsite = new Experience(
                "Summer School of ISCTE in Penha Garcia",
                "Website for village in Castelo Branco",
                "2024",
                "",
                "Analysis of air, noise, and water quality in the border territory and description of the smuggling route.",
                Arrays.asList(
                    "Led the development of a website for the village of Penha Garcia and their history. Also implemented the SCRUM methodology."
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
