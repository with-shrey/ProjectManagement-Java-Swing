package com.shrey.models;

import com.shrey.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Task {
    int id;
    String title;
    int projectId;
    String description;
    String status;

    private static List<Task> resultSetToTasks(ResultSet resultSet) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        if (!resultSet.next()){
            return tasks;
        }else{
            do {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setProjectId(resultSet.getInt("projectId"));
                task.setDescription(resultSet.getString("description"));
                task.setStatus(resultSet.getString("status"));
                tasks.add(task);
            }while(resultSet.next());
        }
        return tasks;
    }

    public  static List<Task> getTasksByProject(int projectId ){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * from task where projectId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,projectId);
            ResultSet resultSet = statement.executeQuery();
            return  resultSetToTasks(resultSet);
        }catch (SQLException e){
            System.out.print(e);
        }
        return  new ArrayList<>();
    }

    public boolean insert(){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO task (title,description,status,projectId) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setString(3,status);
            statement.setInt(4,projectId);
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
            String sql = "UPDATE task  SET title = ? , description = ? ,status = ? ,projectId = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setString(3,status);
            statement.setInt(4,projectId);
            statement.setInt(5,id);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", projectId=" + projectId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
