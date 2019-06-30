package com.catsserver.dao;

import java.util.List;
import java.util.Map;

public interface GetCatsDAO {

    public List<Map<String, String>> getAll(String ... param);
}