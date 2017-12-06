package lt.kaunascoding.java;

import java.util.Scanner;
import java.sql.*;
public class StudentasInsert {
    public StudentasInsert(){
        // susikuriam kintamuosius laikyti informacijai
        String name;
        String surname;
        String phone;
        String email;
        // susikuriam skaitytuva
        Scanner skaitytuvas = new Scanner(System.in);

        // paprasom vartotojo ivesti informacija
        System.out.println("Iveskite studento vardą");
        name=skaitytuvas.nextLine();
        System.out.println("Iveskite studento pavardę");
        surname=skaitytuvas.nextLine();
        System.out.println("Iveskite studento telefono nr.");
        phone=skaitytuvas.nextLine();
        System.out.println("Iveskite studento el. paštą");
        email=skaitytuvas.nextLine();

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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `students` WHERE `name` = '"+name+"' AND `surname` = '"+surname+"'");
            if(resultSet.next()== true){
                // jei egzistuoja, rasyti pranesima
                System.out.println("Studentas su tokiu vardu ir pavarde jau įvestas");
            }else{
                // jei neegzistuoja tiesiog ikelti
                statement.execute("INSERT INTO `students` " +
                        "(`id`, `name`, `surname`, `phone`, `email`) " +
                        "VALUES " +
                        "(NULL, '"+name+"', '"+surname+"', '"+phone+"', '"+email+"')" +
                        ";");
            }





            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
