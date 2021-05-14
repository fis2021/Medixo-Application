package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Specialization {

    @Id
    private String specialization;

    public Specialization(String specialization)
    {
        this.specialization=specialization;
    }

    public Specialization(){}

    public String getSpecialization(){return this.specialization;}

    public void setSpecialization(String specialization){this.specialization=specialization;}

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Specialization s = (Specialization) object;
        return specialization.equals(s.specialization);
    }
}
