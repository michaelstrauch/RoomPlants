import java.time.LocalDate;

public class Plant implements Comparable<Plant> {
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
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
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
        if (watering.isBefore(planted)) {
            throw new PlantException("Zálivka nemůže být před datem zasazení(" + getPlanted() + ") " + "Zadáno: " + watering);
        }
        this.watering = watering;
    }

    public int getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency) throws PlantException {
        if (wateringFrequency <= 0) {
            throw new PlantException("Frekvence zalívání musí být minimálně 1 den. Zadáno: " + wateringFrequency);
        }
        this.wateringFrequency = wateringFrequency;
    }

    public String getWateringInfo() {
        return getName() + " | Datum poslední zálivky: " + getWatering() + " | Datum další zálivky: " + getWatering().plusDays(getWateringFrequency());
    }

    public void doWateringNow() throws PlantException {
        setWatering(LocalDate.now());
    }


    public String getCzechDeclension(int wateringFrequency) {
        String frequencyDays;
        if (wateringFrequency == 1) {
            frequencyDays = wateringFrequency + " den.";
        } else if (wateringFrequency <= 4) {
            frequencyDays = wateringFrequency + " dny.";
        } else {
            frequencyDays = wateringFrequency + " dní.";
        }
        return frequencyDays;
    }

    @Override
    public String toString() {
        return "Název: " + name + " | " +
                "Poznámky: " + notes + " | " +
                "Zasazeno: " + planted + " | " +
                "Poslední zálivka: " + watering + " | " +
                "Frekvence zálivky: " + getCzechDeclension(wateringFrequency);
    }

    @Override
    public int compareTo(Plant other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}
