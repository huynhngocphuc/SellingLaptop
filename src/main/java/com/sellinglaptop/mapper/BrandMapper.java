package com.sellinglaptop.mapper;

import com.sellinglaptop.model.BrandModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements IRowMapper<BrandModel> {

    @Override
    public BrandModel mapRow(ResultSet resultSet) {
        BrandModel brandModel = new BrandModel();
        try{
            brandModel.setId(resultSet.getInt("id"));
            brandModel.setBrandName(resultSet.getString("brand_name"));
            brandModel.setImage(resultSet.getString("image"));
        }
        catch (SQLException e){
            return null;
        }
        return brandModel;
    }
}
