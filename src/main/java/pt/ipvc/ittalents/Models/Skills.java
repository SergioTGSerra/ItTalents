package pt.ipvc.ittalents.Models;

import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Backend.Skill;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Skills {
    public static Map<Skill, Person> data = new HashMap<>();
    public static void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream("/home/tiago/IdeaProjects/ItTalents/src/main/java/pt/ipvc/ittalents/SavedData/skills.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("/home/tiago/IdeaProjects/ItTalents/src/main/java/pt/ipvc/ittalents/SavedData/skills.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (Map<Skill, Person>) ois.readObject();
        ois.close();
    }
}
