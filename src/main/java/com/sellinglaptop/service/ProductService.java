package com.sellinglaptop.service;

import com.sellinglaptop.dao.ProductDAO;
import com.sellinglaptop.model.ProductModel;

import javax.inject.Inject;
import java.util.List;

public class ProductService {
    @Inject
    private ProductDAO productDAO;

    public ProductModel save(ProductModel productModel){
        int id = productDAO.save(productModel);
        return productDAO.findOne(id);
    }
    public ProductModel update(ProductModel productModel){
        productDAO.update(productModel);
        return productDAO.findOne(productModel.getId());
    }
    public void delete(int id){
            productDAO.delete(id);
    }
    public List<ProductModel> findAll(){
        return productDAO.findAll();
    }
    public ProductModel findOne(int id){
        return productDAO.findOne(id);
    }


    public List<ProductModel> findAllByPage(int offset,int limit){
        return productDAO.findAllByPage(offset, limit);
    }
    public List<ProductModel> findByCategory(int cate_id){
        return productDAO.findByCategory(cate_id);
    }
    public List<ProductModel> findByDetailCategory(int detail_cate_id) {
        return productDAO.findByDetailCategory(detail_cate_id);
    }
    public List<ProductModel> findByDetailCategoryAndPage(int detail_cate_id,int offset,int limit) {
        return productDAO.findByDetailCategoryAndPage(detail_cate_id, offset, limit);
    }
    public List<ProductModel> findByCategoryAndPage(int detail_cate_id,int offset,int limit) {
        return productDAO.findByCategoryAndPage(detail_cate_id, offset, limit);
    }

    public List<ProductModel> searchByKeywordAndPage(String keyword,int offset,int limit) {
        return productDAO.searchByKeywordAndPage(keyword, offset, limit);
    }

    public List<ProductModel> searchByKeyword(String keyword) {
        return productDAO.searchByKeyword(keyword);
    }

    public Object[] searchByProperties(List<Object> map, int offset, int limit){
        return productDAO.searchByProperties(map,offset,limit);
    }
}
