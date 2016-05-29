package ru.shal1928.emercardi.app.models.blood;

/**
 * Data about blood;
 */
public class BloodModel {

    public BloodModel I = new BloodModel(BloodType.O);
    public BloodModel II = new BloodModel(BloodType.A);
    public BloodModel III = new BloodModel(BloodType.B);
    public BloodModel IV = new BloodModel(BloodType.AB);

    private BloodType bloodType;
    private boolean rh;
    private boolean kell;



    public BloodModel() {
        //
    }

    public BloodModel(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public BloodModel(BloodType bloodType, boolean rh, boolean kell) {
        this.bloodType = bloodType;
        this.rh = rh;
        this.kell = kell;
    }



    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public boolean isRh() {
        return rh;
    }

    public void setRh(boolean rh) {
        this.rh = rh;
    }

    public boolean isKell() {
        return kell;
    }

    public void setKell(boolean kell) {
        this.kell = kell;
    }
}
