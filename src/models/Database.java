package models;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    private Connection connection = null;
    private String databaseFile;
    private String databaseUrl;
    private Model model;

    public Database(String databaseFile, Model model) {
        this.databaseFile = databaseFile;
        this.model = model;
        this.databaseUrl = "jdbc:sqlite:" + this.databaseFile;
        checkDatabase();
        select(); // võib lisada ka "this."

    }

    private void checkDatabase() {
        File f = new File(databaseFile);
        if(!f.exists()) { // databasefile not exists
            createTable();
        } else { // database exists
            if(!tableExists()) { // of table does not exist
                createTable();
            }
        }
    }

    private boolean tableExists() {
        try {
            Connection conn = this.dbConnection();
            DatabaseMetaData dmb = conn.getMetaData();
            ResultSet rs= dmb.getTables(null,null,"words",null);
            rs.next(); // Move the reading order forward
            return rs.getRow() > 0; // if > 0 table exists, null, or 0 doesn't exist(false)
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            return false; // no table
        }
    }

    private void createTable() {
        try {
            Connection conn = this.dbConnection();
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE \"words\" (\n" +
                    "    \"id\"    INTEGER NOT NULL UNIQUE,\n" +
                    "    \"word\"    TEXT NOT NULL,\n" +
                    "    \"category\"    TEXT NOT NULL,\n" +
                    "    PRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                    ");";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection dbConnection() throws SQLException {
        if(connection != null) { // connection exists
            connection.close();
        }
        connection = DriverManager.getConnection(databaseUrl);
        return connection;
    }
    public void select() {
        String sql = "SELECT * FROM words Order by category, word";
        List<String> categories = new ArrayList<>();
        List<DatabaseData> databaseData = new ArrayList<>();
        try {
            Connection conn = this.dbConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("word");
                String category = rs.getString("category");
                databaseData.add(new DatabaseData(id, word, category));
                categories.add(category); // Add category to array lst
            }

            List<String> unique = categories.stream().distinct().collect(Collectors.toList());
            // System.out.println(unique.size()); // test
            model.setDatabaseData(databaseData);
            model.setCorrectComboNames(unique);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectByCategory(String cate) {
        String sql = "SELECT * FROM words WHERE category = ? ORDER BY category, word";
        List<String> categories = new ArrayList<>();
        List<DatabaseData> databaseData = new ArrayList<>();
        try {
            Connection conn = this.dbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("word");
                String category = rs.getString("category");
                databaseData.add(new DatabaseData(id, word, category));
                categories.add(category); // Add category to array lst
            }
            // Removes duplicates https://howtodoinjava.com/java8/stream-find-remove-duplicates/
            List<String> unique = categories.stream().distinct().collect(Collectors.toList());
            model.setDatabaseData(databaseData);
            model.setCorrectComboNames(unique);
            // System.out.println(model.getDatabaseData().size()); // test
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insert(String newWord, String categoryName) {
        String sql = "INSERT INTO words (word, category) VALUES (?, ?)";
        try {
            Connection conn = this.dbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newWord);
            ps.setString(2, categoryName);
            ps.executeUpdate();
            this.select(); // Update data in table
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
