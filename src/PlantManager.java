import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    List<Plant> listOfPlants = new ArrayList<>();

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
}



