package com.gamebase.general.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.util.MultipartInputStreamFileResource;

@Service
@Transactional
public class GeneralService {

	private final String IMGUR_UPLOAD_URL = "https://api.imgur.com/3/upload";
	private final String IMGUR_CLIENT_ID = "e1d6333cdc6b9dd";
	
	public String uploadToImgur(MultipartFile image) {
		// use REST Template to throw request and response
		System.out.println("Debug Line1");
		RestTemplate template = new RestTemplate();
		System.out.println("Debug Line2");
		// prepare for body content
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		System.out.println("Debug Line3");
		try {
			body.add("image", new MultipartInputStreamFileResource(image.getInputStream(),image.getOriginalFilename()));
			System.out.println("Debug Line4");
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Debug Line4-1");
			System.out.println(e.getMessage());
		}
		System.out.println("Debug Line5");
		// get request entity
		URI uri = URI.create(IMGUR_UPLOAD_URL);
		System.out.println("Debug Line6");
		RequestEntity<LinkedMultiValueMap<String, Object>> req = RequestEntity
							.post(uri)
							.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
							.contentType(MediaType.MULTIPART_FORM_DATA)
							.body(body);
		
		// get response entity
		System.out.println("Debug Line7");
		ResponseEntity<Map> res = template.exchange(req,Map.class);
		
		
		// check http status is 200 OK
		System.out.println("Debug Line8");
		if(res.getStatusCodeValue() == 200) {
			System.out.println("Debug Line8-1");
			String imgUrl = ((Map)res.getBody().get("data")).get("link").toString();
			System.out.println(imgUrl);
			System.out.println("Debug Line8-2");
			return imgUrl;
		} else {
			System.out.println("Debug Line8-3");
			return "false";
		}
	}
	
	public String uploadToImgur(InputStream subImage,String imageName) {
		// use REST Template to throw request and response
		RestTemplate template = new RestTemplate();
		
		// prepare for body content
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("image", new MultipartInputStreamFileResource(subImage,imageName));
		
		// get request entity
		URI uri = URI.create(IMGUR_UPLOAD_URL);
		RequestEntity<LinkedMultiValueMap<String, Object>> req = RequestEntity
							.post(uri)
							.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
							.contentType(MediaType.MULTIPART_FORM_DATA)
							.body(body);
		
		// get response entity
		ResponseEntity<Map> res = template.exchange(req,Map.class);
		
		
		// check http status is 200 OK
		if(res.getStatusCodeValue() == 200) {
			System.out.println("Hello 200");
			String imgUrl = ((Map)res.getBody().get("data")).get("link").toString();
			
			return imgUrl;
		} else {
			System.out.println("Hello not 200");
			return null;
		}
	}
}
