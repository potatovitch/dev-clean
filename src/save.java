import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Platform;

public class save {
    
    Plateform platform;
    
    ///// sauvegarde et chargement de la plateforme (à revoir j'ai repris sans checker) /////

    public void saveToFile(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public static Platform loadFromFile(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Platform platform = (Platform) ois.readObject();
            return platform;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
            return null;
        }
    }
}
