package com.gamebase.general.model.dao;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Repository
public class UploadImgDAO {

	final ImgurAPI imgurApi = createImgurAPI();

	public String uploadImg(MultipartFile uploadImg) {
		String returnURL = "";

		try {
			// 轉換檔案型別：MultipartFile → File
			CommonsMultipartFile commonsmultipartfile = (CommonsMultipartFile) uploadImg;
			DiskFileItem diskFileItem = (DiskFileItem) commonsmultipartfile.getFileItem();
			File image = diskFileItem.getStoreLocation();

			// 上傳圖片
			RequestBody request = RequestBody.create(MediaType.parse("image/*"), image);
			Call<ImageResponse> call = imgurApi.postImage(request);
			Response<ImageResponse> res = call.execute();

			returnURL = res.body().data.link;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnURL;
	}

	private ImgurAPI createImgurAPI() {
		Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
				.baseUrl(ImgurAPI.SERVER).build();
		return retrofit.create(ImgurAPI.class);
	}

}
