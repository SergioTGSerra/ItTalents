package pt.ipvc.ittalents.Models;

import pt.ipvc.ittalents.Backend.Offer;
import pt.ipvc.ittalents.Backend.Person;
import pt.ipvc.ittalents.Backend.Professional;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Offers {
    public static int index;
    public static Map<Offer, Professional> data = new HashMap<>();

    public static void saveData() throws IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Offers.data");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();

        PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\OffersI.data");
        writer.print(index);
        writer.close();
    }
    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\Offers.data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (Map<Offer, Professional>) ois.readObject();
        ois.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\pt\\ipvc\\ittalents\\SavedData\\OffersI.data"));
        index = Integer.parseInt(bufferedReader.readLine());
    }
}
