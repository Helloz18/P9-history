package com.mediscreen.history;


import java.util.ArrayList;
import java.util.List;

import com.mediscreen.history.model.Note;
import com.mediscreen.history.model.PatientHistory;
import com.mediscreen.history.repository.MongoDbPatientHistoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class HistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoryApplication.class, args);
		
		}

}
