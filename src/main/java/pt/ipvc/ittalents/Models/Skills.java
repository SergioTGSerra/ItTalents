package pt.ipvc.ittalents.Models;

import pt.ipvc.ittalents.Backend.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Skills {
    public static int index;
    public static List<Skill> data = new ArrayList<>();

    public static void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Skills.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();

        PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\SkillsI.data");
        writer.print(index);
        writer.close();
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Skills.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (List<Skill>) ois.readObject();
        ois.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\SkillsI.data"));
        index = Integer.parseInt(bufferedReader.readLine());
    }
}
