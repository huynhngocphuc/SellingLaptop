package com.sellinglaptop.mapper;

import com.sellinglaptop.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements IRowMapper<ProductModel>{

    @Override
    public ProductModel mapRow(ResultSet resultSet) {
        ProductModel productModel = new ProductModel();
        try{
            productModel.setId(resultSet.getInt("id"));
            productModel.setProductName(resultSet.getString("product_name"));
            productModel.setImage(resultSet.getString("image"));
            productModel.setPrice(resultSet.getDouble("price"));
            productModel.setQuantity(resultSet.getInt("quantity"));
            productModel.setDescribePro(resultSet.getString("describe_pro"));
            productModel.setDetailCateId(resultSet.getInt("detail_cate_id"));
        }
        catch (SQLException e){
            return null;
        }
        return productModel;
    }
}
