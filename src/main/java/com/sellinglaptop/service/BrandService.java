package com.sellinglaptop.service;

import com.sellinglaptop.dao.BrandDAO;
import com.sellinglaptop.model.BrandModel;

import javax.inject.Inject;
import java.util.List;

public class BrandService {
    @Inject
    private BrandDAO brandDAO;
    public List<BrandModel> findAll() {
        return brandDAO.findAll();
    }
    public BrandModel save(BrandModel brandModel) {
        int id= brandDAO.save(brandModel);
        return brandDAO.findOne(id);
    }

    public void delete(int id) {
        brandDAO.delete(id);
    }
    public BrandModel findOne(int id) {
        return brandDAO.findOne(id);
    }
    public void update(BrandModel brandModel) {
        BrandModel brand = findOne(brandModel.getId());
        brand.setBrandName(brandModel.getBrandName());
        brand.setImage(brandModel.getImage());
        brandDAO.update(brand);
    }
}
