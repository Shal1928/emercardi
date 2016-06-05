package ru.shal1928.emercardi.app.models;

import ru.shal1928.emercardi.app.models.blood.BloodModel;
import ru.shal1928.emercardi.app.models.medicine.Medicine;

import java.util.LinkedList;
import java.util.List;

/**
 * Data about health.
 */
public class HealthModel {

    private int height;
    private int weight;
    private BloodModel blood;
    private List<String> diseases;
    private List<String> allergies;
    private List<Medicine> medicines;



    public HealthModel() {
        this.diseases = new LinkedList<String>();
        this.allergies = new LinkedList<String>();
        this.medicines = new LinkedList<Medicine>();
    }

    public HealthModel(int height, int weight, BloodModel blood, List<String> diseases, List<String> allergies,
        List<Medicine> medicines) {
        this.height = height;
        this.weight = weight;
        this.blood = blood;
        this.diseases = diseases;
        this.allergies = allergies;
        this.medicines = medicines;
    }



    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public BloodModel getBlood() {
        return blood;
    }

    public void setBlood(BloodModel blood) {
        this.blood = blood;
    }

    public List<String> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<String> diseases) {
        this.diseases = diseases;
    }

    public void addDisease(String disease) {
        this.diseases.add(disease);
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public void addAllergies(String allergy) {
        this.allergies.add(allergy);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public void addMedicine(Medicine  medicine) {
        this.medicines.add(medicine);
    }
}
