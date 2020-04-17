package com.gamebase.tradesystem.model.dao;

import com.gamebase.tradesystem.model.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IProductDao {
	public JSONObject add(String form);
	public JSONArray query();
	public JSONObject delete(int id);
	public JSONObject update(String b);
	public JSONArray search(String a);
	public JSONArray getSearch(String a);

}
