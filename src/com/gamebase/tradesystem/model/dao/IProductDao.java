package com.gamebase.tradesystem.model.dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IProductDao {
	public boolean add(JSONObject jobj);
	public JSONArray query();
	public boolean delete(int id);
	public boolean update(JSONObject jobj);
	public JSONArray search(String a);
	public JSONArray getSearch(String a);
}
