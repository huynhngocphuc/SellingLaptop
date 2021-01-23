package com.sellinglaptop.dao;

import com.sellinglaptop.mapper.BrandMapper;
import com.sellinglaptop.model.BrandModel;

import java.util.List;

public class BrandDAO extends GenericDAO<BrandModel> {
    public List<BrandModel> findAll(){
        String sql="Select * from Brand";
        List<BrandModel> brands = query(sql,new BrandMapper());
        return brands.isEmpty() ? null : brands;
    }
    public int save(BrandModel brandModel){
        String sql="insert into Brand(brand_name,image) values(?,?)";
        return insert(sql,brandModel.getBrandName(),brandModel.getImage());
    }
    public BrandModel findOne(int id){
        String sql="Select * from Brand where id =?";
        List<BrandModel> brands = query(sql,new BrandMapper(),id);
        return brands.isEmpty() ? null : brands.get(0);
    }
    public void delete(int id){
        String sql="delete from brand where id=?";
        update(sql,id);
    }
    public void update(BrandModel brandModel){
        String sql="update Brand set brand_name=?,image=? where id=?";
        update(sql,brandModel.getBrandName(),brandModel.getImage(),brandModel.getId());
    }
}
