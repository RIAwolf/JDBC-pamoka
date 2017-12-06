package lt.kaunascoding.java;


import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Connection connection;
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kcs", "root", "");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM `students` ORDER BY `name` ASC;");
//            while (resultSet.next()) {
//                System.out.print(resultSet.getInt("id") + "\t");
//                System.out.print(resultSet.getString("name") + "\t");
//                System.out.print(resultSet.getString("surname") + "\t");
//                System.out.print(resultSet.getString("phone") + "\t");
//                System.out.print(resultSet.getString("email") + "\t");
//                System.out.print("\n");
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Main programa = new Main();
        Scanner skaitytuvas = new Scanner(System.in);
        while (true) {
            programa.rodykMeniu();
            int veiksmas = skaitytuvas.nextInt();
            switch (veiksmas) {
                case 0:
                    return;
                case 1:
                    new StudentasInsert();
                    break;
                case 2:
                    new AddressInsert();
                    break;
                case 3:
                    new MarkInsert();
                    break;
            }
        }
    }

    public void rodykMeniu() {
        System.out.println("|------------------------------------------------|");
        System.out.println("| Sveiki, pasirinkite kokį veiksmą norite atlikti|");
        System.out.println("| 0 - Baigti programos darbą                     |");
        System.out.println("| 1 - Pridėti naują studentą                     |");
        System.out.println("| 2 - Pridėti naują adresa                       |");
        System.out.println("| 3 - Pridėti naują pazymi                       |");
        System.out.println("|------------------------------------------------|");
    }
}
