package com.shrey.models;

import com.shrey.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {
    int id;
    String title;
    List<Task> tasks;
    List<Document> documents;

    private static List<Project> resultSetToProject(ResultSet resultSet) throws SQLException {
        List<Project> projects = new ArrayList<>();
        if (!resultSet.next()){
            return projects;
        }else{
            do {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setTitle(resultSet.getString("title"));
                project.setTasks(Task.getTasksByProject(project.getId()));
                project.setDocuments(Document.getDocumentsByProject(project.getId()));
                projects.add(project);
            }while (resultSet.next());
            return projects;
        }
    }
    public  static List<Project> getProjects(int projectId){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * from project where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,projectId);
            ResultSet resultSet = statement.executeQuery();
            return resultSetToProject(resultSet);
        }catch (SQLException e){
            System.out.print(e);
        }
        return  new ArrayList<>();
    }

    public static List<Project> getProjects() {
        try{
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from project");
            return  resultSetToProject(resultSet);
        }catch (SQLException e){
            System.out.print(e);
        }
        return  new ArrayList<>();
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                '}';
    }
}
