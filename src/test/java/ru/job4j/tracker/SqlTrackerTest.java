package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {

    //    public Connection init() {
//        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
//            Properties config = new Properties();
//            config.load(in);
//            Class.forName(config.getProperty("driver-class-name"));
//            return DriverManager.getConnection(
//                    config.getProperty("url"),
//                    config.getProperty("username"),
//                    config.getProperty("password")
//            );
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//    }
    Connection init = CreatConnection.init();

    @Test
    public void add() throws SQLException {
        //try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init))) {
            tracker.add(new Item("name"));
            List<Item> rslList = tracker.findByName("name");
            assertThat(rslList.size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void replace() throws SQLException {
        //try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init))) {
            tracker.add(new Item("name"));
            List<Item> list = tracker.findByName("name");
            Item item = list.get(0);
            tracker.replace(item.getId(), new Item("name1"));
            Item rsl = tracker.findById(item.getId());
            assertThat(rsl.getName(), is("name1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init))) {
            tracker.add(new Item("name"));
            List<Item> list = tracker.findByName("name");
            Item item = list.get(0);
            boolean rsl = tracker.delete(item.getId());
            list = tracker.findByName("name");
            assertThat(list.size(), is(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findAll() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init))) {
            //tracker.add(new Item("name"));
            tracker.add(new Item("name1"));
            tracker.add(new Item("name2"));
            tracker.add(new Item("name3"));
            List<Item> rslList = tracker.findAll();
            assertThat(rslList.size(), is(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByName() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init))) {
            tracker.add(new Item("name"));
            List<Item> list = tracker.findByName("name");
            assertThat(list.size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init))) {
            tracker.add(new Item("name"));
            List<Item> list = tracker.findByName("name");
            Item rsl = tracker.findById(list.get(0).getId());
            assertThat(rsl.getName(), is("name"));
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}