import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSVHandler {
    /**
     * sauvegarde les personnes de la plateforme dans un fichier CSV et les paires
     */
    public void saveToCSV(String filePath, Plateform platform) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nom, Prénom, Date de Naissance, Pays\n");
            for (Person p : platform.getListPersonnes()) {
                writer.write(p.getNom() + ", " + p.getPrenom() + ", " + p.getdNaiss() + ", " + p.getPays() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Pair p : platform.getListPairs()) {
                writer.write(p.getPerson1().getNom() + ", " + p.getPerson1().getNom() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ArrayList<Person> loadPersonsFromCSV(String filePath) {
        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(", ");
                if (cells.length == 4) {    // quatre colonnes attendues
                    String nom = cells[0];  // premier élément : nom
                    String prenom = cells[1]; // deuxième élément : prénom
                    LocalDate dNaiss = LocalDate.parse(cells[2]);
                    Pays pays = Pays.valueOf(cells[3]);
                    persons.add(new Person(nom, prenom, dNaiss, pays));
                }
            }
            reader.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return persons;
    }

}
