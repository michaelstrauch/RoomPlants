import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PlantManager list1 = new PlantManager();

        try {
            list1.importFromFile();
        } catch (PlantException e) {
            System.err.println("Chyba při čtení ze souboru > "+ e.getMessage());
        }

        for (Plant plant : list1.getCopyOfList()) {
            System.out.println(plant.getWateringInfo());

        }

        try {
            list1.addToList(new Plant("Lilie","bez poznámky",LocalDate.of(2021,7,1),LocalDate.of(2021,7,1),3));
        } catch (PlantException e) {
            System.err.println("Chyba při vytváření květiny > "+ e.getMessage());
        }

        for (int i = 1; i < 11; i++) {
            try {
                list1.addToList(new Plant(("Tulipán na prodej "+ i),"",LocalDate.now(),LocalDate.now(),14));
            } catch (PlantException e) {
                System.err.println("Chyba při vytváření květiny > "+ e.getMessage());
            }

        }

        list1.removeFromList(list1.getPlantOnIndex(2));

        try {
            list1.exportToFile();
        } catch (PlantException e) {
            System.err.println("Chyba při zápisu do souboru > "+ e.getMessage());
        }



    }
}
