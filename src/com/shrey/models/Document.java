package com.shrey.models;

import com.shrey.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Document {
    int id;
    int projectId;
    String path;
    String title;

    private static List<Document> resultSetToDocuments(ResultSet resultSet) throws SQLException {
        List<Document> documents = new ArrayList<>();
        if (!resultSet.next()){
            return documents;
        }else{
            do {
            Document document = new Document();
            document.setId(resultSet.getInt("id"));
            document.setPath(resultSet.getString("path"));
            document.setProjectId(resultSet.getInt("projectId"));
            document.setTitle(resultSet.getString("title"));
            documents.add(document);
            }while(resultSet.next());
        }
        return documents;
    }

    public  static List<Document> getDocumentsByProject(int projectId ){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * from document where projectId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,projectId);
            ResultSet resultSet = statement.executeQuery();
            return  resultSetToDocuments(resultSet);
        }catch (SQLException e){
            System.out.print(e);
        }
        return  new ArrayList<>();
    }
    public boolean insert(){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO document (title,path,projectId) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,path);
            statement.setInt(3,projectId);
            System.out.println(this.toString());
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.print(e);
        }
        return false;
    }
    public boolean update(){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE document  SET title = ? , path = ? ,projectId = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,path);
            statement.setInt(3,projectId);
            statement.setInt(4,id);
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.print(e);
        }
        return false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
