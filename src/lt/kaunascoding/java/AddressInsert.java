package lt.kaunascoding.java;

import java.sql.*;
import java.util.Scanner;

public class AddressInsert {
    public AddressInsert() {
        // susikuriam kintamuosius laikyti informacijai
        String name;
        String surname;
        String country;
        String city;
        String street;
        String postcode;
        // susikuriam skaitytuva
        Scanner skaitytuvas = new Scanner(System.in);

        // paprasom vartotojo ivesti informacija
        System.out.println("Iveskite studento vardą");
        name = skaitytuvas.nextLine();
        System.out.println("Iveskite studento pavardę");
        surname = skaitytuvas.nextLine();
        System.out.println("Iveskite studento šalį");
        country = skaitytuvas.nextLine();
        System.out.println("Iveskite studento miestą");
        city = skaitytuvas.nextLine();
        System.out.println("Iveskite studento gatvę");
        street = skaitytuvas.nextLine();
        System.out.println("Iveskite studento pašto kodą");
        postcode = skaitytuvas.nextLine();

        // atidarom prisijungima i duombaze
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kcs", "root", "");
            Statement statement = connection.createStatement();
            // patikrinti ar toks studentas egzistuoja duombazeje
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `students` " +
                    "WHERE `name` = '" + name + "' AND `surname` = '" + surname + "'");
            if (resultSet.next() == true) {
                // jei egzistuoja, irasyti informacija
                int studentID = resultSet.getInt("id");

                statement.execute("INSERT INTO `student_address` " +
                        "(`id`, `student_id`, `country`, `city`, `street`, `postcode`)" +
                        " VALUES " +
                        "(NULL, '" + studentID + "', '" + country + "', '" + city + "', '" + street + "', '" + postcode + "')"
                );
            } else {
                // jei neegzistuoja parasyti kad toks studentas nerastas
                System.out.println("Studentas su tokiu vardu ir pavarde nerastas");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
