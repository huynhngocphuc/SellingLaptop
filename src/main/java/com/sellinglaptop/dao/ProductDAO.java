package com.sellinglaptop.dao;

import com.sellinglaptop.mapper.ProductMapper;
import com.sellinglaptop.model.ProductModel;

import java.util.List;

public class ProductDAO extends GenericDAO<ProductModel>{

    public ProductModel findOne(int id){
        String sql="Select * from Product where id =?";
        List<ProductModel> products = query(sql,new ProductMapper(),id);
        return products.isEmpty() ? null : products.get(0);
    }
    public List<ProductModel> findAll(){
        String sql="Select * from Product";
        List<ProductModel> products = query(sql,new ProductMapper());
        return products.isEmpty() ? null : products;
    }
    public int save(ProductModel productModel){
        String sql="insert into Product(product_name,image,price,quantity,describe_pro,detail_cate_id) values(?,?,?,?,?,?)";
        return insert(sql,productModel.getProductName(),productModel.getImage()
                ,productModel.getPrice(),productModel.getQuantity(), productModel.getDescribePro(),productModel.getDetailCateId());
    }
    public void update(ProductModel productModel){
        String sql="update Product set product_name=?,image=?,price=?,quantity=?,describe_pro=?,detail_cate_id=? where id=?";
        update(sql,productModel.getProductName(),productModel.getImage(),productModel.getPrice()
                ,productModel.getQuantity(),productModel.getDescribePro(),productModel.getDetailCateId(),productModel.getId());
    }
    public void delete(int id){
        String sql="delete from Product where id=?";
        update(sql,id);
    }

    public List<ProductModel> findByCategory(int cate_id){
        String sql="Select * from Product,DetailCategory,Category " +
                "where Product.detail_cate_id=DetailCategory.id and DetailCategory.cate_id=Category.id and Category.id =?";
        List<ProductModel> products = query(sql,new ProductMapper(),cate_id);
        return products.isEmpty() ? null : products;
    }
    public List<ProductModel> findByDetailCategory(int detail_cate_id){
        String sql="Select * from Product " +
                "where Product.detail_cate_id=?";
        List<ProductModel> products = query(sql,new ProductMapper(),detail_cate_id);
        return products.isEmpty() ? null : products;
    }
    public List<ProductModel> findAllByPage(int offset,int limit) {
        String sql = "Select * from Product "+
                      "limit "+offset+","+limit;
        List<ProductModel> products = query(sql, new ProductMapper());
        return products.isEmpty() ? null : products;
    }

    public List<ProductModel> findByCategoryAndPage(int cate_id,int offset,int limit){
        String sql="Select * from Product,DetailCategory,Category " +
                "where Product.detail_cate_id=DetailCategory.id and DetailCategory.cate_id=Category.id and Category.id =? " +
                "limit "+offset+","+limit ;
        List<ProductModel> products = query(sql,new ProductMapper(),cate_id);
        return products.isEmpty() ? null : products;
    }
    public List<ProductModel> findByDetailCategoryAndPage(int detail_cate_id,int offset,int limit){
        String sql="Select * from Product " +
                "where Product.detail_cate_id=? " +
                "limit "+offset+","+limit ;
        List<ProductModel> products = query(sql,new ProductMapper(),detail_cate_id);
        return products.isEmpty() ? null : products;
    }
    public List<ProductModel> searchByKeywordAndPage(String keyword,int offset,int limit){
        String sql="Select * from Product " +
                "where product_name like '%"+keyword+"%' "+
                "limit "+offset+","+limit ;
        List<ProductModel> products = query(sql,new ProductMapper(),keyword);
        return products.isEmpty() ? null : products;
    }

    public List<ProductModel> searchByKeyword(String keyword){
        String sql="Select * from Product " +
                "where product_name like '%"+keyword+"%' ";
        List<ProductModel> products = query(sql,new ProductMapper());
        return products.isEmpty() ? null : products;
    }

    public Object[] searchByProperties(List<Object> value, int offset, int limit)
    {

        String qString = "select Product.* from Product,DetailCategory,Category,Brand " +
                "where 1=1 and Product.detail_cate_id=DetailCategory.id " +
                "and DetailCategory.cate_id=Category.id and Brand.id = Product.brand_id";
        if (value.get(0) !=null) {
            qString+=" and brand_id = "+value.get(0);
        }
        if (value.get(1) !=null) {
            qString += " and Category.id = "+value.get(1);
        }
        if(value.get(2)!=null) {
            qString+= " and DetailCategory.id =  "+value.get(2);
        }
        qString += " and Product.price >= ? and Product.price <= ? ";
        if (value.get(5) != null) {
            qString += " and product_name like '%"+value.get(5)+"%' " ;
        }

        List<ProductModel> products = query(qString,new ProductMapper(),value.get(3),value.get(4));

        int totalProduct = products.size();

        qString += "limit "+offset+","+limit ;

        List<ProductModel> productList = query(qString,new ProductMapper(),value.get(3),value.get(4));

        Object[] result ={productList,totalProduct};

        return result ;
    }

}
