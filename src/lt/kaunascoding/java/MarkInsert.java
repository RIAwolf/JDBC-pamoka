package lt.kaunascoding.java;

import java.sql.*;
import java.util.Scanner;

public class MarkInsert {
    public MarkInsert() {
        // susikuriam kintamuosius laikyti informacijai
        String name;
        String surname;
        String title;
        int mark;

        // susikuriam skaitytuva
        Scanner skaitytuvas = new Scanner(System.in);
        // paprasom vartotojo ivesti informacija
        System.out.println("Iveskite studento vardą");
        name = skaitytuvas.nextLine();
        System.out.println("Iveskite studento pavardę");
        surname = skaitytuvas.nextLine();
        System.out.println("Iveskite dalyko pavadinima");
        title = skaitytuvas.nextLine();

        mark = gaukPazimi(skaitytuvas);

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

                statement.execute("INSERT INTO `student_marks` " +
                        "(`id`, `student_id`, `title`, `mark`, `time_stamp`) " +
                        "VALUES " +
                        "(NULL, "+studentID+", '"+title+"', "+mark+", NULL);"
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

    private int gaukPazimi(Scanner skaitytuvas){
        boolean yraSkaicius = false;
        String textMark="";
        int mark =-1;
        while (yraSkaicius == false) {
            System.out.println("Iveskite studento pazymi");
            textMark = skaitytuvas.nextLine();
            try {
                mark = Integer.parseInt(textMark);
                yraSkaicius = true;
            } catch (Exception e) {
                System.out.println("Ivestas ne skaicius");
            }
        }
        return mark;
    }
}
