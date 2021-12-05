package com.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learning.dao.CategoryRepository;
import com.learning.dao.TrainingRepository;
import com.learning.entities.Category;
import com.learning.entities.Role;
import com.learning.entities.Training;
import com.learning.service.AccountService;

@SpringBootApplication
public class TraineeshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraineeshipApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService accountService,
													CategoryRepository categoryRepository,
													TrainingRepository trainingRepository,
													RepositoryRestConfiguration repositoryRestConfiguration) {
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Training.class, Category.class);

			accountService.saveRole(new Role(null, "USER"));
			accountService.saveRole(new Role(null, "ADMIN"));
			Stream.of("user1", "user2", "user3", "admin").forEach(u -> {
					accountService.saveEmployee(u,
						u,
						u +"@gmail.com",
						123456789,
						u,
						"1234",
						"1234");
				});
			accountService.addRoleToUser("admin", "ADMIN");

			Category category1 = categoryRepository.save(new Category(null, "Soft Skills", null));
			Category category2 = categoryRepository.save(new Category(null, "Hard Skills", null));

			List<Training> trainingList = new ArrayList<Training>();

			trainingList.add(new Training(null,
				"Communication",
				"Write and speak more professionally in English to increase your business success.",
				"This Specialization helps you improve your professional communication in English for successful" +
					" business interactions. Each course focuses on a particular area of communication in English:  " +
					"writing emails, speaking at meetings and interviews, giving presentations, and networking online. " +
					"Whether you want to communicate to potential employers, employees, partners or clients, " +
					"better English communication can help you achieve your language and professional goals. " +
					"The Capstone course will focus especially on making those important connections to " +
					"take your career or business to the next level. Make yourself more competitive by improving " +
					"your English through this Specialization: Improve Your English Communication Skills.",
				"Advanced",
				10,
				false,
				"Communication.png",
				category1,
				null));

			trainingList.add(new Training(null,
				"Public Speaking",
				"If you’re a beginner, this course will help you quickly master the fundamentals of speaking.",
				"This course gives you a reliable model for preparing and delivering effective presentations. \n" +
					"In business, in school, and in public life, we are often called upon to “make a few comments.” Often, " +
					"people tasked with such speeches become flummoxed. They might not know what to talk about, or ramble " +
					"without making a point, or simply be confusing to listen to. This course is designed to help you shine " +
					"where others falter.",
				"Beginner",
				10,
				false,
				"Public Speaking" + ".png",
				category1,
				null));

			trainingList.add(new Training(null,
					"JAVA",
					"Become an application developer with Java, one of the most widely used languages in the world.",
					"The Java language has become essential and it" +
						"must be included in all training plans for IT specialists who do not yet know it." +
						"It occupies such an important place today in all areas of development (from embedded to the Web)" +
						"that we offer a complete course dedicated to Java training. When designing this course," +
						"we have sought to facilitate entry into the Java world as much as possible by offering introductory courses" +
						"which are suitable for different profiles ( Java without knowing the object, Java for developer Object, ...)." +
						"Then, we sought to meet the recurring training needs for professional developers" +
						"while addressing more specific topics in our advanced java courses." +
						"Fundamental",
					"Fundamental",
					8,
					true,
					"JAVA.png",
					category2,
					null));

			trainingList.add(new Training(null,
				"Angular",
				"Write Angular like an expert.\n" +
					"Build real structured apps with confidence.",
				"Learn Angular 11 and build responsive, enterprise-strength applications that run smoothly on desktop" +
					" and mobile devices with this Angular training course. Angular provides a robust framework that facilitates " +
					"the development of richly interactive applications running on multiple platforms. Gain experience building " +
					"components, creating directives, modularizing applications, and building template-driven forms.",
				"Fundamental",
				8,
				true,
				"Angular.png",
				category2,
				null));

			trainingList.add(new Training(null,
				"Spring Boot",
				"The Spring Professional training is designed to test and validate your understanding " +
					"of and familiarity with core aspects",
				"Building on the benefits of the Spring framework, Spring Boot represents the next chapter in " +
					"Java development. Spring Boot’s opinionated approach removes much of the boilerplate and configuration " +
					"that characterizes Spring, making development faster and deployment easier. This course offers hands-on " +
					"experience with Spring Boot and its major features, including auto-configuration, Spring data, Actuator, " +
					"Spring Boot testing and more. On completion, participants will have a foundation for creating " +
					"enterprise-ready applications.",
				"Fundamental",
				8,
				true,
				"Spring Boot.png",
				category2,
				null));

			trainingList.add(new Training(null,
				"Python",
				"Build the skills you need to get your first Python programming job",
				"This course is aimed at complete beginners who have never programmed before, as well as existing " +
					"programmers who want to increase their career options by learning Python." +
					"The fact is, Python is one of the most popular programming languages in the world – Huge companies " +
					"like Google use it in mission critical applications like Google Search." +
					"And Python is the number one language choice for machine learning, data science and artificial intelligence." +
					" To get those high paying jobs you need an expert knowledge of Python, and that’s what you will " +
					"get from this course.",
				"Fundamental",
				8,
				true,
				"Python.png",
				category2,
				null));

			trainingList.forEach(training -> {
				trainingRepository.save(training);
			});
		};

	}

	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
