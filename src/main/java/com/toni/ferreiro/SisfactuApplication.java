package com.toni.ferreiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.toni.ferreiro.models.serviceInterface.UploadFileServiceInterface;

@SpringBootApplication
public class SisfactuApplication implements CommandLineRunner {

	@Autowired
	private UploadFileServiceInterface uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SisfactuApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
		
		String password="1234";
		
		for (int i=0;i<2;i++) {
			String bCryptPassword = passwordEncoder.encode(password);
			System.out.println(bCryptPassword);
		}
	}

}

