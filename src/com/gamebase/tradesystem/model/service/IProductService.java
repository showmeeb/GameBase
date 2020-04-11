package com.gamebase.tradesystem.model.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IProductService {
	public boolean add(JSONObject jobj);
	public JSONArray query();
	public boolean delete(int id);
	public boolean update(JSONObject jobj);
	public JSONArray search(String a);
}
