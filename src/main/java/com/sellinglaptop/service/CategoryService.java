package com.sellinglaptop.service;

import com.sellinglaptop.dao.CategoryDAO;
import com.sellinglaptop.model.CategoryModel;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    private CategoryDAO categoryDAO;

    public List<CategoryModel> findAll() {
        return  categoryDAO.findAll();
    }
//    public CategoryModel findOne(){
//        return  categoryDAO.find
//    }
}
