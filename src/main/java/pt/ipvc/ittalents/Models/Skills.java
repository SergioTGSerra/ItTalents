package pt.ipvc.ittalents.Models;

import pt.ipvc.ittalents.Backend.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Skills {
    public static List<Skill> data = new ArrayList<>();

    public static void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\sergi\\IdeaProjects\\ItTalents\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Skills.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\sergi\\IdeaProjects\\ItTalents\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Skills.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (List<Skill>) ois.readObject();
        ois.close();
    }
}
