package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
public class DemoApplication {

	private static final long MAX_FILE_SIZE = 16 * 1024 * 1024; // 16 MB

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	String sayHello() {
		return "Hello World!";
	}

	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            return ResponseEntity.status(HttpStatus.REQUEST_ENTITY_TOO_LARGE)
                                 .body("File size exceeds the maximum limit of 16 MB.");
        }

        // Process the file (e.g., save it, process its content, etc.)
        // ...

        return ResponseEntity.ok("File uploaded successfully!");
    }
}
