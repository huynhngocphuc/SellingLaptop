package com.sellinglaptop.service;

import com.sellinglaptop.dao.DetailCategoryDAO;
import com.sellinglaptop.model.DetailCategoryModel;

import javax.inject.Inject;
import java.util.List;

public class DetailCategoryService {
    @Inject
    private DetailCategoryDAO detailCategoryDAO;
    public List<DetailCategoryModel> findByCategoryId(int cateId){
        return detailCategoryDAO.findByDetailCategoryID(cateId);
    }
    public List<DetailCategoryModel> findAll(){
        return detailCategoryDAO.findAll();
    }
}
