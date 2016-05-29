package ru.shal1928.emercardi.app.models;

import ru.shal1928.emercardi.app.models.blood.BloodModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Data about health.
 */
public class HealthModel {

    private int age;
    private int weight;
    private BloodModel blood;
    private List<String> diseases;
    private List<String> allergies;
    private List<String> medicines;



    public HealthModel() {
        this.diseases = new LinkedList<String>();
        this.allergies = new LinkedList<String>();
        this.medicines = new LinkedList<String>();
    }

    public HealthModel(int age, int weight, BloodModel blood, List<String> diseases, List<String> allergies,
        List<String> medicines) {
        this.age = age;
        this.weight = weight;
        this.blood = blood;
        this.diseases = diseases;
        this.allergies = allergies;
        this.medicines = medicines;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    public void addMedicine(String  medicine) {
        this.medicines.add(medicine);
    }
}
