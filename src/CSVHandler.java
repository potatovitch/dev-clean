import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler {
    public void saveToCSV(String filePath, Plateform platform) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nom, Prénom, Date de Naissance, Pays\n");
            for (Person p : platform.getListPersonnes()) {
                writer.write(p.getNom() + ", " + p.getPrenom() + ", " + p.getdNaiss() + ", " + p.getPays() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    

}
