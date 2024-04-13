package tsk.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import tsk.hibernate.model.Address;

public class DBConfigHibernate {

    private static String DB_DRIVER = "org.postgresql.Driver";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String DB_USERNAME = "postgres";
    private static String DB_PASSWORD = "12345678";

    public static SessionFactory getSession() {
        return new Configuration()
                .setProperty("hibernate.connection.url", DB_URL)
                .setProperty("hibernate.connection.driver_class", DB_DRIVER)
                .setProperty("hibernate.connection.username", DB_USERNAME)
                .setProperty("hibernate.connection.password", DB_PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();
    }
}
