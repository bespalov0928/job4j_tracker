package ru.job4j.tracker.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.attach.VirtualMachine.list;

public class HbmTracker implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Override
    public void init() {

    }

    @Override
    public Item add(Item item) {
        Item rsl = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            //session.close();
            rsl = item;
        } catch (Exception e) {
//            session.close();
            e.printStackTrace();
        }
        return rsl;
    }


    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;

        try(Session session = sf.openSession()) {
            session.beginTransaction();
            item.setId(id);
            session.update(item);
            session.getTransaction().commit();
            //session.close();
            rsl = true;
        } catch (Exception e) {
//            session.close();
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;

        try(Session session = sf.openSession()) {
            session.beginTransaction();
            Item item = new Item(null);
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
            //session.close();
            rsl = true;
        } catch (Exception e) {
            //session.close();
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();

        try(Session session = sf.openSession()) {
            session.beginTransaction();
            List result = session.createQuery("from ru.job4j.tracker.Item").list();
            session.getTransaction().commit();
            //session.close();
        } catch (Exception e) {
            //session.close();
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();

        try(Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("from ru.job4j.tracker.Item where name = :name"
            ).setParameter("name", key).list();
            session.getTransaction().commit();
            //session.close();
        } catch (Exception e) {
            //session.close();
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        Item rsl = null;

        try(Session session = sf.openSession()) {
            session.beginTransaction();
            Item result = session.get(Item.class, id);
            session.getTransaction().commit();
            //session.close();
        } catch (Exception e) {
            //session.close();
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
