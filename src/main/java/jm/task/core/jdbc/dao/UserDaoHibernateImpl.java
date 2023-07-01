package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT,lastName TEXT,age DOUBLE);");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS USERS;");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            list = session.createQuery("select u from User u").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM USERS;");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
