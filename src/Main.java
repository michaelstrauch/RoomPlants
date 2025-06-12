import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        PlantManager list1 = new PlantManager();

        try {
            list1.importFromFile("kvetiny.txt");
        } catch (PlantException e) {
            System.err.println("Chyba při čtení ze souboru > " + e.getMessage());
        }

        for (Plant plant : list1.getCopyOfList()) {
            System.out.println(plant.getWateringInfo());
        }
        System.out.println();

        try {
            list1.addToList(new Plant("Lilie", "bez poznámky", LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 1), 3));
        } catch (PlantException e) {
            System.err.println("Chyba při vytváření květiny > " + e.getMessage());
        }

        for (int i = 1; i < 11; i++) {
            try {
                list1.addToList(new Plant(("Tulipán na prodej " + i), "", LocalDate.now(), LocalDate.now(), 14));
            } catch (PlantException e) {
                System.err.println("Chyba při vytváření květiny > " + e.getMessage());
            }
        }

        try {
            list1.removeFromList(list1.getPlantOnIndex(2));
        } catch (PlantException e) {
            System.err.println("Chyba > " + e.getMessage());
        }

        try {
            list1.exportToFile("kvetiny-upraveno.txt");
        } catch (PlantException e) {
            System.err.println("Chyba při zápisu do souboru > " + e.getMessage());
        }

        list1.listClear();

        try {
            list1.importFromFile("kvetiny-upraveno.txt");
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru > " + e.getMessage());
        }

        list1.sortByNameAndWatering();
        for (Plant plant : list1.getCopyOfList()) {
            System.out.println(plant);
        }
        System.out.println();

        list1.sortByHasNote();
        for (Plant plant : list1.getCopyOfList()) {
            System.out.println(plant);
        }
        System.out.println();

        list1.reverseSortByPlanted();
        for (Plant plant : list1.getCopyOfList()) {
            System.out.println(plant);
        }
    }
}
