package tsk.jdbc.repository;

import tsk.jdbc.config.DBConfigJDBC;
import tsk.jdbc.model.Dessert;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DessertRepositoryImpl implements DessertRepository {
    @Override
    public void createTable() {
        Connection connection = DBConfigJDBC.getConnection();


        String sql = """
                CREATE TABLE IF NOT EXISTS dessert (
                id BIGSERIAL PRIMARY KEY,
                name_dessert VARCHAR (50),
                weight INTEGER,
                cost INTEGER,
                description VARCHAR (255)
                )
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropTable() {
        Connection connection = DBConfigJDBC.getConnection();

        String sql = """
                DROP TABLE IF EXISTS dessert
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDessert(Dessert dessert) {
        Connection connection = DBConfigJDBC.getConnection();

        String sql = """
        INSERT INTO dessert (name_dessert, weight, cost, description) 
        VALUES(?, ?, ?, ?)
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, dessert.getName());
            preparedStatement.setInt(2, dessert.getWeight().intValue());
            preparedStatement.setInt(3, dessert.getCost().intValue());
            preparedStatement.setString(4, dessert.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeDessertById(Long id) {
        Connection connection = DBConfigJDBC.getConnection();

        String sql = String.format("DELETE FROM dessert WHERE id = %d", id);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDesert(Long id, Dessert dessert) {
        Connection connection = DBConfigJDBC.getConnection();

        String sql = String.format("UPDATE dessert" +
                " SET name_dessert = \'%s\'" +
                ",weight = %d" +
                ",cost = %d" +
                ",description = \'%s\'" +
                        " WHERE id = %d",
                dessert.getName(),
                dessert.getWeight(),
                dessert.getCost(),
                dessert.getDescription(),
                id);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dessert getDessertById(Long id) {

        List<Dessert> desserts = getAllDesserts();

        return desserts.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<Dessert> getAllDesserts() {
        Connection connection = DBConfigJDBC.getConnection();
        List<Dessert> desserts = new ArrayList<>();

        String sql = """
                SELECT * FROM dessert
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Dessert dessert = new Dessert();
                dessert.setId(resultSet.getLong("id"));
                dessert.setName(resultSet.getString("name_dessert"));
                dessert.setWeight(Integer.valueOf(resultSet.getString("weight")));
                dessert.setCost(Integer.valueOf(resultSet.getString("cost")));
                dessert.setDescription(resultSet.getString("description"));
                desserts.add(dessert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return desserts.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
