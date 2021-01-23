package com.sellinglaptop.dao;

import com.sellinglaptop.mapper.CategoryMapper;
import com.sellinglaptop.model.CategoryModel;

import java.util.*;

public class CategoryDAO extends GenericDAO<CategoryModel>{

    public List<CategoryModel> findAll(){
        String sql="Select * from Category";
        return query(sql,new CategoryMapper());
    }
//    public CategoryModel findOne(int id){
//        String sql="Select * from Category where id=?";
//        return query(sql,new Cate)
//    }
    /*
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/website_tmdt";
            String user="root";
            String password="123456";
            System.out.println("Connect success");
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
    public List<CategoryModel> findAll() {
        List<CategoryModel> result = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "Select * from Category ";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    CategoryModel category = new CategoryModel();
                    category.setId(resultSet.getInt("id"));
                    category.setCateName(resultSet.getString("cate_name"));
                    category.setImage(resultSet.getString("image"));
                    result.add(category);
                }

                return result;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
            finally {
                try{
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                }
                catch (SQLException e){
                    return  null;
                }

            }
        }
        return  null;
    }

     */

}
