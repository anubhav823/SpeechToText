package com.anubhav.speechToText.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {

//	@Value("{cloud.aws.credentials.access-key}")
	private String accessKey = "AKIASGNA5IBB2W4MZWLA";
//	@Value("{cloud.aws.credentials.secret-key}")
	private String secretKey = "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";
//	@Value("{cloud.aws.region.static}")
//	private String region;

	@Bean
	AmazonS3 generateS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		System.out.println(accessKey);
		System.out.println(secretKey);
//		System.out.println();
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_SOUTH_1).build();
	}
}
