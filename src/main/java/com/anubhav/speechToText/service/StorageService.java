package com.anubhav.speechToText.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

@Service
public class StorageService {

	@Value("{application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3Client;
	
	public String uploadFile(MultipartFile file) {
		File fileObject = convertMultipartFileToFile(file);
		String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		s3Client.putObject(bucketName, fileName, fileObject);
		fileObject.delete();
		return "uploaded file";	
	}
	
	private File convertMultipartFileToFile(MultipartFile multipartFile) {
		File convertedFile = new File(multipartFile.getOriginalFilename());
		try {
			FileOutputStream fos = new FileOutputStream(convertedFile);
			fos.write(multipartFile.getBytes());
		}catch (IOException e) {
			e.printStackTrace();
		}
		return convertedFile;
	}
}
