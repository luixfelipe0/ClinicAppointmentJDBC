package model.entities;

public class Doctor {

    private Integer id;
    private String name;
    private Integer crm;
    private String specialty;
    private Integer phone;
    private String email;

    public Doctor() {
    }

    public Doctor(Integer id, String name, Integer crm, String specialty, Integer phone, String email) {
        this.id = id;
        this.name = name;
        this.crm = crm;
        this.specialty = specialty;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
