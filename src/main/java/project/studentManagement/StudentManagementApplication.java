package project.studentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
Created by: Yifei Lin
This is where to start the app
Direct to localhost:8080
Username and passwords:
yifei.lin   123
user		456
sam.silas 	321

they have different roles and the page will have the different functionalities
 */
@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
