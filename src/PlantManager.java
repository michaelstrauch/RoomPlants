import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantManager {
   private List<Plant> listOfPlants = new ArrayList<>();
    String filepath = "kvetiny.txt";

    public void addToList(Plant plant) {
        listOfPlants.add(plant);
    }

    public Plant getPlantOnIndex(int index) {
        if (index < 0 || index >= listOfPlants.size()) {
            System.out.println("Na zadané pozici neexistuje květina");
            return null;
        }
        return listOfPlants.get(index);
    }

    public void removeFromList(Plant plant) {
        listOfPlants.remove(plant);
    }

    public List<Plant> getCopyOfList() {
        return new ArrayList<>(listOfPlants);
    }

    public List<Plant> wateringNeededList() throws PlantException {
        List<Plant> wateringNeededList = new ArrayList<>();
        for (Plant plant : listOfPlants) {
            if (plant.getWatering().isBefore(LocalDate.now().plusDays(plant.getWateringFrequency()))) {
                wateringNeededList.add(plant);
            }
        } return wateringNeededList;
    }

    public void sortByNameAndLastWatering() {
        listOfPlants.sort(Comparator.comparing(Plant::getName).thenComparing((Plant::getWatering)));
    }


    public void importFromFile() throws PlantException {
        try (Scanner reader = new Scanner(new BufferedReader(new FileReader(filepath)))) {
            while (reader.hasNextLine()) {
                try {
                    String line = reader.nextLine();
                    String[] parts = line.split("\t");
                    String nameFromFile = parts[0].trim();
                    String noteFromFile = parts[1].trim();
                    int wateringFrequencyFromFile = Integer.parseInt(parts[2].trim());
                    LocalDate lastWateredFromFile = LocalDate.parse(parts[3].trim());
                    LocalDate plantedFromFile = LocalDate.parse(parts[4].trim());
                    Plant plantFromFile = new Plant(nameFromFile,noteFromFile,plantedFromFile,lastWateredFromFile,wateringFrequencyFromFile);
                    listOfPlants.add(plantFromFile);

                } catch (PlantException e) {
                    System.err.println("Chyba: "+ e.getMessage());
                } catch (DateTimeParseException e) {
                    System.err.println("Datum musí být ve správném formátu(yyyy-mm-dd). "+ e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Frekvence musí být celé číslo. "+ e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Chyba: "+ e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při čtení souboru: "+ e.getMessage());
        }
    }

    public void exportToFile() throws PlantException {
        String delimiter = "\t";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, false))) {

            for (Plant plant : listOfPlants) {
                String line = String.join(delimiter,
                        plant.getName(),
                        plant.getNotes(),
                        String.valueOf(plant.getWateringFrequency()),
                        plant.getWatering().toString(),
                        plant.getPlanted().toString());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru:" + e.getMessage());
        }
    }



}



