package com.gamebase.general.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.general.model.dao.UploadImgDAO;

@Service
public class UploadImgService {

	private UploadImgDAO uploadImgDAO;

	@Autowired
	public UploadImgService(UploadImgDAO uploadImgDAO) {
		this.uploadImgDAO = uploadImgDAO;
	}

	public String uploadImg(MultipartFile uploadImg) {
		return uploadImgDAO.uploadImg(uploadImg);

	}
}
