package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;
    private int ids = 1;

    public void init() {

        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private String generateId() {
        return String.valueOf(ids++);
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Item add(Item item) {
        item.setId(generateId());
        try (PreparedStatement st = cn.prepareStatement("insert into items(name) values (?)")) {
            st.setString(1, item.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("update items SET name = ? where id = ?")) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.valueOf(id));
            st.executeUpdate();
            rsl = true;
            //System.out.println(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("delete from items where id = ?")) {
            st.setInt(1, Integer.valueOf(id));
            st.executeUpdate();
            rsl = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList();
        try (PreparedStatement st = cn.prepareStatement("select * from items")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Item(rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select * from items where name = ?")) {
            st.setString(1, key);
            //st.executeUpdate();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Item(rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement st = cn.prepareStatement("select * from items where id = ?")) {
            //System.out.println(id);
            st.setInt(1, Integer.valueOf(id));
            //st.executeUpdate();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                item = new Item(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
