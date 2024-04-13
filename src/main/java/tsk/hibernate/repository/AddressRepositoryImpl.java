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

        List<Address> addresses = getAllAddresses();

        return addresses.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<Address> getAllAddresses() {

        String sql = """
                SELECT * FROM address
                """;

        SessionFactory sessionFactory = DBConfigHibernate.getSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(sql);

        List<Address> addresses = query.getResultList();

        transaction.commit();
        session.close();

        return addresses;
    }
}
