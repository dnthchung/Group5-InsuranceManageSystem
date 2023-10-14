/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Blog_tag;
import Model.Blog_type;
import Model.Blogs;
import Model.NewBl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chun
 */
public class BlogDAO extends DBContext {

    //get list blog
    public ArrayList<Blogs> getAllBlogs() {
        try {
            ArrayList<Blogs> getAll = new ArrayList<>();
            String sql = "select * from Blogs";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String bl_id = rs.getString(1);
                String bl_title = rs.getString(2);
                String bl_content = rs.getString(3);
                int bl_like = rs.getInt(4);
                String bl_img = rs.getString(5);
                int user_id = rs.getInt(6);
                String bl_creationdate = rs.getString(7);
                String bl_type_name = rs.getString(8);
                String bl_tag_tagname = rs.getString(9);
                int bl_view = rs.getInt(10);
                String bl_status = rs.getString(11);

                getAll.add(new Blogs(bl_id, bl_title, bl_content, bl_like, bl_img, user_id, bl_creationdate, bl_type_name, bl_tag_tagname, bl_view, bl_status));
            }
            return getAll;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //get top 5 blog hving view cao
    public ArrayList<Blogs> getTop5Blogs() {
        try {
            ArrayList<Blogs> getTop5Blogs = new ArrayList<>();
            String sql = "select top 5 * from Blogs order by bl_view desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String bl_id = rs.getString(1);
                String bl_title = rs.getString(2);
                String bl_content = rs.getString(3);
                int bl_like = rs.getInt(4);
                String bl_img = rs.getString(5);
                int user_id = rs.getInt(6);
                String bl_creationdate = rs.getString(7);
                String bl_type_name = rs.getString(8);
                String bl_tag_tagname = rs.getString(9);
                int bl_view = rs.getInt(10);
                String bl_status = rs.getString(11);
                
                getTop5Blogs.add(new Blogs(bl_id, bl_title, bl_content, bl_like, bl_img, user_id, bl_creationdate, bl_type_name, bl_tag_tagname, bl_view, bl_status));

            }
            return getTop5Blogs;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //get top 15 blog hving view cao
    public ArrayList<Blogs> getTop15Blogs() {
        try {
            ArrayList<Blogs> getTop15Blogs = new ArrayList<>();
            String sql = "select top 5 * from Blogs order by bl_view desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String bl_id = rs.getString(1);
                String bl_title = rs.getString(2);
                String bl_content = rs.getString(3);
                int bl_like = rs.getInt(4);
                String bl_img = rs.getString(5);
                int user_id = rs.getInt(6);
                String bl_creationdate = rs.getString(7);
                String bl_type_name = rs.getString(8);
                String bl_tag_tagname = rs.getString(9);
                int bl_view = rs.getInt(10);
                String bl_status = rs.getString(11);

                getTop15Blogs.add(new Blogs(bl_id, bl_title, bl_content, bl_like, bl_img, user_id, bl_creationdate, bl_type_name, bl_tag_tagname, bl_view, bl_status));

            }
            return getTop15Blogs;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //get blog with user name
    public ArrayList<NewBl> getBlogsWithUserName() {
        try {
            ArrayList<NewBl> getBlogs = new ArrayList<>();
            String sql = "SELECT  B.* , U.user_fullname from Blogs B JOIN Users U ON B.user_id = U.user_id;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String bl_id = rs.getString(1);
                String bl_title = rs.getString(2);
                String bl_content = rs.getString(3);
                int bl_like = rs.getInt(4);
                String bl_img = rs.getString(5);
                int user_id = rs.getInt(6);
                String bl_creationdate = rs.getString(7);
                String bl_type_name = rs.getString(8);
                String bl_tag_tagname = rs.getString(9);
                String bl_view = rs.getString(10);
                String bl_status = rs.getString(11);
                String username = rs.getString(12);

                getBlogs.add(new  NewBl(username, bl_id, bl_title, bl_content, bl_like, bl_img, user_id, bl_creationdate, bl_type_name, bl_tag_tagname, bl_like, bl_status));

            }
            return getBlogs;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    //create new blog
     public boolean createBlog(
            String bl_title,
            String bl_content,
            int bl_like,
            String bl_img,
            int user_id,
            String bl_creationdate,
            String bl_type_name,
            String bl_tag_tagname,
            int bl_view,
            String bl_status) {
        try {
            String sql = "insert into Blogs values((SELECT COALESCE(MAX(bl_id), 0) from Blogs)+1,?,?,?,?,?,?,?,?,?,?) ;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, bl_title);
            pstm.setString(2, bl_content);
            pstm.setInt(3, bl_like);
            pstm.setString(4, bl_img);
            pstm.setInt(5, user_id);
            pstm.setString(6, bl_creationdate);
            pstm.setString(7, bl_type_name);
            pstm.setString(8, bl_tag_tagname);
            pstm.setInt(9, bl_view);
            pstm.setString(10, bl_status);
            
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
     //get blog tag
     public ArrayList<Blog_tag> getBlogTag() {
        try {
            ArrayList<Blog_tag> getBlogTag = new ArrayList<>();
            String sql = "select * from Blog_tag";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bl_id = rs.getInt(1);
                String bl_tag_tagname = rs.getString(2);
                getBlogTag.add(new Blog_tag(bl_id, bl_tag_tagname));
            }
            return getBlogTag;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     //get blog type
    public ArrayList<Blog_type> getBlogType() {
        try {
            ArrayList<Blog_type> getBlogType = new ArrayList<>();
            String sql = "select * from Blog_type";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bl_id = rs.getInt(1);
                String bl_tag_tagname = rs.getString(2);
                getBlogType.add(new Blog_type(bl_id, bl_tag_tagname));
            }
            return getBlogType;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();
        Blogs bgg = new Blogs();

//        ArrayList<Blogs> bg = dao.getAllBlogs();
//        
//        for (Blogs blogs : bg) {
//            System.out.println(blogs.toString());
//        }

//        dao.createBlog("a", "b", 0, "bl_img", 1, "2023-09-01", 1, 1, 0, "Active");
        
        ArrayList<Blog_tag> bggg = dao.getBlogTag();
        for (Blog_tag blogs : bggg) {
            System.out.println(blogs.toString());
        }

    }

}
