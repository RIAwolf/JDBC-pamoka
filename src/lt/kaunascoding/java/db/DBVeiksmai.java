package lt.kaunascoding.java.db;

import java.sql.*;

public class DBVeiksmai {
    private static final String HOST = "jdbc:mysql://localhost:3306/kcs";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    Connection _connection;

    public DBVeiksmai() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        prisijunkPrieDB();

    }

    // gauti connection
    private void prisijunkPrieDB() {
        try {
            _connection = DriverManager.getConnection(HOST, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // uzdaryti connection
    public void uzdarykDuombazesKanala() {
        try {
            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atvaizduok uzklausos turini
    public void parodykUzklausosTurini(String sql) {
        try {
            Statement statement = _connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(resultSet.getString(i) + "\t");

                }
                System.out.print("\n");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // grazink resultSet
    public ResultSet ivykdykUzklausa(String sql) {
        ResultSet resultSet = null;
        try {
            Statement statement = _connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void paruoskUzklausa(String sql, Object[] parameters) {
        try {
            PreparedStatement svariUzklausa = _connection.prepareStatement(sql);
            for (int i = 1; i <= parameters.length; i++) {
                switch (parameters[i - 1].getClass().toString()) {
                    case "class java.lang.Integer":
                        svariUzklausa.setInt(i, (Integer) parameters[i - 1]);
                        break;
                    case "class java.lang.String":
                        svariUzklausa.setString(i, (String) parameters[i - 1]);
                        break;
                    case "class java.lang.Float":
                        svariUzklausa.setFloat(i, (Float) parameters[i - 1]);
                        break;
                    case "class java.lang.Double":
                        svariUzklausa.setDouble(i, (Double) parameters[i - 1]);
                        break;
                }

            }
            svariUzklausa.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int duokStudentoID(String name, String surname){
        int rezultatas=-1;
        try {
            PreparedStatement statement = _connection.prepareStatement(
                    "SELECT * FROM `students` WHERE `name` = ? AND `surname` = ?"
            );
            statement.setString(1,name);
            statement.setString(2,surname);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                rezultatas = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rezultatas;
    }
}
