package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static Connection connection;
    private static SessionFactory factory;

    public static void init() {

        Properties properties = new Properties();

        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "password");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.SHOW_SQL, "false");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "create-drop");

        factory = new Configuration().setProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();

    }

    public static Session getSession() {
        if (factory == null) {
            init();
        }
        return factory.getCurrentSession();
    }

    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    private static void connect() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

            if (!connection.isClosed()) {
                System.out.println("Соединение установлено");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Невозможно подключиться к БД");
        }

    }

}
