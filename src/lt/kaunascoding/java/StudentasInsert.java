package lt.kaunascoding.java;

import lt.kaunascoding.java.db.DBVeiksmai;

import java.util.Scanner;
import java.sql.*;

public class StudentasInsert {
    public StudentasInsert() {
        DBVeiksmai veiksmai = new DBVeiksmai();
        // susikuriam kintamuosius laikyti informacijai
        String name;
        String surname;
        String phone;
        String email;
        // susikuriam skaitytuva
        Scanner skaitytuvas = new Scanner(System.in);

        // paprasom vartotojo ivesti informacija
        System.out.println("Iveskite studento vardą");
        name = skaitytuvas.nextLine();
        System.out.println("Iveskite studento pavardę");
        surname = skaitytuvas.nextLine();
        System.out.println("Iveskite studento telefono nr.");
        phone = skaitytuvas.nextLine();
        System.out.println("Iveskite studento el. paštą");
        email = skaitytuvas.nextLine();
        Object[] parametrai = {
                name,
                surname,
                phone,
                email
        };

        int id = veiksmai.duokStudentoID(name, surname);
        if (id != -1) {
            System.out.println("Toks studentas jau ivestas");
        } else {
            veiksmai.paruoskUzklausa("INSERT INTO `students` (`id`, `name`, `surname`, `phone`, `email`) VALUES (NULL, ?, ?, ?, ?)", parametrai);
        }


    }
}
