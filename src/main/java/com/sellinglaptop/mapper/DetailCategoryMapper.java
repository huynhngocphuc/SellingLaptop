package com.sellinglaptop.mapper;

import com.sellinglaptop.model.DetailCategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailCategoryMapper implements IRowMapper<DetailCategoryModel>{

    @Override
    public DetailCategoryModel mapRow(ResultSet resultSet) {
        DetailCategoryModel detailCategoryModel = new DetailCategoryModel();
        try{
            detailCategoryModel.setId(resultSet.getInt("id"));
            detailCategoryModel.setDetailCateName(resultSet.getString("detail_cate_name"));
            detailCategoryModel.setImage(resultSet.getString("image"));
            detailCategoryModel.setCateId(resultSet.getInt("cate_id"));
        }
        catch (SQLException e){
            return null;
        }
        return detailCategoryModel;
    }
}
