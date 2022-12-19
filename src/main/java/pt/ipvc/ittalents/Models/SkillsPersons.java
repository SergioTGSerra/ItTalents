package pt.ipvc.ittalents.Models;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillsPersons {
    public static Map<Integer, List<Integer>> data = new HashMap<>();

    public static void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\sergi\\IdeaProjects\\ItTalents\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\SkillsPersons.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\sergi\\IdeaProjects\\ItTalents\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\SkillsPersons.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (Map<Integer, List<Integer>>) ois.readObject();
        ois.close();
    }
}
