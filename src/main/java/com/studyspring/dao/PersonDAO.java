package com.studyspring.dao;

import com.studyspring.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:mysql://localhost:3306/spring_study_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static Connection connection;

    static {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            people.add(person);
        }

        return people;
    }

    public Person show(int id) {
        Person person = null;

        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM person WHERE id=?");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            person = new Person(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("email"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
        try {

            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO person VALUES(1, ?, ?, ?)");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
