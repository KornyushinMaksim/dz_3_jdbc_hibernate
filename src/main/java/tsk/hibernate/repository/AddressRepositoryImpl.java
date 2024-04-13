package tsk.hibernate.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tsk.hibernate.config.DBConfigHibernate;
import tsk.hibernate.model.Address;

import java.util.List;

public class AddressRepositoryImpl implements AddressRepository {

    @Override
    public void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS address (
                id BIGSERIAL PRIMARY KEY,
                postal_code INTEGER,
                city VARCHAR(50),
                name_street VARCHAR(50),
                house INTEGER
                )
                """;

        try (SessionFactory sessionFactory = DBConfigHibernate.getSession()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {

        String sql = """
                DELETE TABLE IF EXISTS address
                """;

        try (SessionFactory sessionFactory = DBConfigHibernate.getSession()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.createQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAddress(Address address) {

        try (SessionFactory sessionFactory = DBConfigHibernate.getSession()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            System.out.println("присвоен id " + address.getId());

            session.saveOrUpdate(address);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAddressById(Long id) {

        String sql = """
                DELETE FROM address a
                WHERE a.id = :id
                """;

        try (SessionFactory sessionFactory = DBConfigHibernate.getSession()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);

            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAddress(Address address) {

        try (SessionFactory sessionFactory = DBConfigHibernate.getSession()) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.update(address);

            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address getAddressById(Long id) {
        String sql = """
                SELECT c FROM Address c
                WHERE c.id = :id
                """;

        SessionFactory sessionFactory = DBConfigHibernate.getSession();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Address address = (Address) session.createQuery(sql)
                .setParameter("id", id)
                .getSingleResult();
        tx.commit();

        session.close();

        return address;
    }

    @Override
    public List<Address> getAllAddresses() {

        String sql = """
                FROM Address
                """;

        SessionFactory sessionFactory = DBConfigHibernate.getSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(sql);
        List<Address> addresses = query.getResultList();

        transaction.commit();
        session.close();

        return addresses;
    }
}
