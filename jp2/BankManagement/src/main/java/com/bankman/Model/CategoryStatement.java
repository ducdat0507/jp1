package com.bankman.Model;

import java.sql.ResultSet;

import com.bankman.Entity.Category;
import com.mysql.cj.PreparedQuery;

import javafx.collections.ObservableList;

public class CategoryStatement {
    private static 
    public ObservableList<Category> list(long limit, long offset) {
        String sql = "SELECT * FROM category LIMIT ?, ?";
        try {
            PreparedQuery query = 
            ResultSet result = 
        } catch (Exception e) {
            
        }
    }
}
