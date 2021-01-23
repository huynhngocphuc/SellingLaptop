package com.sellinglaptop.dao;

import com.sellinglaptop.mapper.DetailCategoryMapper;
import com.sellinglaptop.model.DetailCategoryModel;

import java.util.List;

public class DetailCategoryDAO extends GenericDAO<DetailCategoryModel>{
    public List<DetailCategoryModel> findByDetailCategoryID(int cate_id){
        String sql = "Select * from DetailCategory where cate_id=?";
        return query(sql, new DetailCategoryMapper(),cate_id);
    }
    public List<DetailCategoryModel> findAll(){
        String sql="Select * from DetailCategory";
        return query(sql,new DetailCategoryMapper());
    }


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
    public List<DetailCategoryModel> findByDetailCategoryID(int cateId) {
        List<DetailCategoryModel> result = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "Select * from DetailCategory where cate_id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1,cateId);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    DetailCategoryModel detailCategory = new DetailCategoryModel();
                    detailCategory.setId(resultSet.getInt("id"));
                    detailCategory.setDetailCateName(resultSet.getString("cate_name"));
                    detailCategory.setImage(resultSet.getString("image"));
                    detailCategory.setCateId(resultSet.getInt("cate_id"));
                    result.add(detailCategory);
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
