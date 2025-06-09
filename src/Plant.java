import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plant {
   private String name;
   private String notes;
   private LocalDate planted;
   private LocalDate watering;
   private int wateringFrequency;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int wateringFrequency) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setWateringFrequency(wateringFrequency);
    }

    public Plant(String name, int wateringFrequency) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), wateringFrequency);
    }

    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(),LocalDate.now(),7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if(watering.isBefore(planted)) {
            throw new PlantException("Zálivka nemůže být před datem zasazení("+ getPlanted()+") "+"Zadáno: "+ watering);
        }
        this.watering = watering;
    }

    public int getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency) throws PlantException {
        if (wateringFrequency <= 0) {
            throw new PlantException("Frekvence zalívání musí být minimálně 1 den. Zadáno: "+ wateringFrequency);
        }
        this.wateringFrequency = wateringFrequency;
    }

    public String getWateringInfo() {
        return getName() +" "+ getWatering() +" "+ getWatering().plusDays(getWateringFrequency());
    }

    public void doWateringNow() throws PlantException {
        setWatering(LocalDate.now());
    }
}
