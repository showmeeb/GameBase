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

import com.gamebase.general.model.Webflow;
import com.gamebase.general.model.dao.WebflowDAO;
import com.gamebase.general.model.util.MultipartInputStreamFileResource;

@Service
@Transactional
public class GeneralService {


	private final String IMGUR_UPLOAD_URL = "https://api.imgur.com/3/upload";
	private final String IMGUR_CLIENT_ID = "e1d6333cdc6b9dd";
	
	public String uploadToImgur(MultipartFile image) {
		// use REST Template to throw request and response
		RestTemplate template = new RestTemplate();

		// prepare for body content
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		try {
			body.add("image",
					new MultipartInputStreamFileResource(image.getInputStream(), image.getOriginalFilename()));
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		// get request entity
		URI uri = URI.create(IMGUR_UPLOAD_URL);
		RequestEntity<LinkedMultiValueMap<String, Object>> req = RequestEntity.post(uri)
				.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID).contentType(MediaType.MULTIPART_FORM_DATA)
				.body(body);

		// get response entity
		ResponseEntity<Map> res = template.exchange(req, Map.class);

		// check http status is 200 OK
		if (res.getStatusCodeValue() == 200) {

			String imgUrl = ((Map) res.getBody().get("data")).get("link").toString();
			
			return imgUrl;
		} else {

			return null;
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
	
	public String UserProfileuploadToImgur(MultipartFile image) {
		// use REST Template to throw request and response	
		RestTemplate template = new RestTemplate();
	
		// prepare for body content
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		try {
			body.add("image", new MultipartInputStreamFileResource(image.getInputStream(),image.getOriginalFilename()));

		} catch (IOException e) {
//			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
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

			String imgUrl = ((Map)res.getBody().get("data")).get("link").toString();
			System.out.println(imgUrl);
			return imgUrl;
		} else {
			return "false";
		}
	}

}
