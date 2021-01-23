package com.sellinglaptop.mapper;

import com.sellinglaptop.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements IRowMapper<CategoryModel>{
    public CategoryModel mapRow(ResultSet resultSet){
        CategoryModel categoryModel = new CategoryModel();
        try{
            categoryModel.setId(resultSet.getInt("id"));
            categoryModel.setCateName(resultSet.getString("cate_name"));
            categoryModel.setImage(resultSet.getString("image"));
        }
        catch (SQLException e){
            return null;
        }
        return categoryModel;
    }
}
