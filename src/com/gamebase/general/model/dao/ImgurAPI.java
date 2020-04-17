package com.gamebase.general.model.dao;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ImgurAPI {
	String SERVER = "https://api.imgur.com";
	public static final String AUTH = "e1d6333cdc6b9dd";

	@Headers("Authorization: Client-ID " + AUTH) //for Retrofit
	@POST("/3/upload")
	Call<ImageResponse> postImage(@Body RequestBody image);

}