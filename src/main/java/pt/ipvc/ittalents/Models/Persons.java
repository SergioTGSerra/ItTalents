package pt.ipvc.ittalents.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import pt.ipvc.ittalents.Backend.Person;

public abstract class Persons {
    public static int index;
    public static Person loged;
    public static List<Person> data = new ArrayList<>();
    public static void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Persons.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();

        PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\PersonsI.data");
        writer.print(index);
        writer.close();
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Persons.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (List<Person>) ois.readObject();
        ois.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\PersonsI.data"));
        index = Integer.parseInt(bufferedReader.readLine());
    }

    public static void updatePersons(){
        Persons.data.set(Persons.data.indexOf(Persons.loged), Persons.loged);
        try {
            Persons.saveData();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
