package com.shrey.models;

import com.shrey.utilities.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class User {
    int id;
    String enrollment;
    String password;
    int type;
    int projectId;
    List<Project> project;

    private static User resultSetToUser(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()){
            return null;
        }else{

                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setEnrollment(resultSet.getString("enrollment"));
                    user.setPassword(resultSet.getString("password"));
                    user.setType(resultSet.getInt("type"));
                    user.setProjectId(resultSet.getInt("projectId"));
                    return user;
        }
    }

    public  static User getUserByEnrollemnt(String value ){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * from user where enrollment = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,value);
            ResultSet resultSet = statement.executeQuery();
            return  resultSetToUser(resultSet);
        }catch (SQLException e){
            System.out.println("Nodata");
            System.out.print(e);
        }
        return  null;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public List<Project> getProject() {
        return project;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", enrollment='" + enrollment + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", projectId=" + projectId +
                ", project=" + project +
                '}';
    }
}
