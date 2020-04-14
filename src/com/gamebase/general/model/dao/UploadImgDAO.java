package com.gamebase.general.model.dao;

import java.io.File;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
			// 暫存檔案(沒寫刪除，會覆蓋)
			String savePath = "C:/temp/tempImg.jpg";
			File saveFile = new File(savePath);
			uploadImg.transferTo(saveFile);

			// 上傳圖片
			File image = new File("C:/temp/tempImg.jpg");
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
