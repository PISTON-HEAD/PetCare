package com.example.pet_care;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="Pet")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pet_type")
public abstract class Pet {

    // Fields
    @Id
    @GeneratedValue
    protected int petId;
    protected String name;
    protected String ownerName;
    protected int age;
    protected String type;

    public Pet()
    {

    }

    // Constructor
    public Pet(int petId, String name, String ownerName, int age, String type) {
        this.petId = petId;
        this.name = name;
        this.ownerName = ownerName;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPetId() {
        return petId;
    }

    public String getType() {
        return type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setAge(int age) {
        this.age = age;
    }




    // Non-abstract method to display pet info
    public void displayInfo() {
        System.out.println("Pet ID: " + petId);
        System.out.println("Name: " + name);
        System.out.println("Owner: " + ownerName);
        System.out.println("Age: " + age);
        System.out.println("Type: " + type);
    }

    public abstract double calculateCareCost();
}
