package com.gamebase.general.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamebase.general.model.dao.UploadImgDAO;

@Service
public class UploadImgService {

	private UploadImgDAO uploadImgDAO;

	@Autowired
	public UploadImgService(UploadImgDAO uploadImgDAO) {
		this.uploadImgDAO = uploadImgDAO;
	}

	public String uploadImg(String path) {
		return uploadImgDAO.uploadImg(path);

	}
}
