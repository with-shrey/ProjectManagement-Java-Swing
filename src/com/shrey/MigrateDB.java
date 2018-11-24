package com.shrey;

import com.shrey.utilities.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MigrateDB {
    public static  void  main(String... args) throws SQLException {
        Connection connection =DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("create table user ( " +
                "id integer primary key autoincrement,enrollment text,password text," +
                "type integer,projectId integer)");
        statement.execute("create table project(" +
                "id integer primary key autoincrement," +
                "title text)");
        statement.execute("create table task(id integer primary key autoincrement,title text,projectId tinteger, description text,status text)");
        statement.execute("create table document(id integer primary key  autoincrement,projectId integer, path text,title text)");
        statement.execute("insert into user values(1,'admin','admin',0,0);");
    }
}
