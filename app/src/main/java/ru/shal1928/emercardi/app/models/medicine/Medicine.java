package ru.shal1928.emercardi.app.models.medicine;

/**
 * Medicine model.
 */
public class Medicine {

    private String title;
    private Measuring measuring;

    private String form;
    private AdditionalMeasuring addMeasuring;

    private String comment;

    public Medicine(String title) {
        this.title = title;
        this.measuring = null;
        this.addMeasuring = null;
    }

    public Medicine(String title, Measuring measuring) {
        this.title = title;
        this.measuring = measuring;
        this.addMeasuring = null;
    }

    public Medicine(String title, String comment) {
        this.title = title;
        this.measuring = null;
        this.addMeasuring = null;
        this.comment = comment;
    }

    public Medicine(String title, Measuring measuring, String comment) {
        this.title = title;
        this.measuring = measuring;
        this.addMeasuring = null;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Measuring getMeasuring() {
        return measuring;
    }

    public void setMeasuring(Measuring measuring) {
        this.measuring = measuring;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public AdditionalMeasuring getAddMeasuring() {
        return addMeasuring;
    }

    public void setAddMeasuring(AdditionalMeasuring addMeasuring) {
        this.addMeasuring = addMeasuring;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
