package com.catsserver.dao;

import com.catsserver.model.Cat;

import java.sql.SQLException;

public interface AddCatsDAO {

    public void addCats(Cat cat) throws SQLException;
}
