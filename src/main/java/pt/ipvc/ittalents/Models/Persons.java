package pt.ipvc.ittalents.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import pt.ipvc.ittalents.Backend.Person;

public class Persons {
    public static Person logedPerson;
    public static List<Person> data = new ArrayList<>();
    public static void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\sergi\\IdeaProjects\\ItTalents\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Persons.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\sergi\\IdeaProjects\\ItTalents\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Persons.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (List<Person>) ois.readObject();
        ois.close();
    }

    public static void updatePersons(){
        Persons.data.set(Persons.data.indexOf(Persons.logedPerson), Persons.logedPerson);
        try {
            Persons.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
