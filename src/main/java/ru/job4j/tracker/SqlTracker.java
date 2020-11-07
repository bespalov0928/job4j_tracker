package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    //private final Connection cn;
    //private static Connection cn;
    private Connection cn;


    public SqlTracker() {
        this.cn = cn;
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private int ids = 1;

    public void init() {
        cn = CreatConnection.init();
//        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
//            Properties config = new Properties();
//            config.load(in);
//            Class.forName(config.getProperty("driver-class-name"));
//            cn = DriverManager.getConnection(
//                    config.getProperty("url"),
//                    config.getProperty("username"),
//                    config.getProperty("password")
//            );
//
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
    }

//    private String generateId() {
//        return String.valueOf(ids++);
//    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        //item.setId(generateId());
        try (PreparedStatement ps = cn.prepareStatement("insert into items(name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            //ps.setInt(2, ps.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Integer id = rs.getInt(1); //вставленный ключ
                item.setId(String.valueOf(id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try (PreparedStatement st = cn.prepareStatement("select * from items where name = ?")){
//            st.setString(1, item.getName());
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getString(1));
//                item.setId(rs.getString(1));
//                break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("update items SET name = ? where id = ?")) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.valueOf(id));
            Integer a = st.executeUpdate();
            rsl = a == 1 ? true : false;
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
            Integer a = st.executeUpdate();
            rsl = a == 1 ? true : false;

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
                Item item = new Item(rs.getString(2));
                item.setId(rs.getString(1));
                list.add(item);
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
                Item item = new Item(rs.getString(2));
                item.setId(rs.getString(1));
                list.add(item);
//                list.add(new Item(rs.getString(2)));
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
                item.setId(rs.getString(1));

//                item = new Item(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
