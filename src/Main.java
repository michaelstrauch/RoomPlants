import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PlantManager list1 = new PlantManager();

        try {
            list1.importFromFile();
        } catch (PlantException e) {
            System.err.println("Soubor neexistuje");;
        }

    }
}
