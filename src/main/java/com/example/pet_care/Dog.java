package com.example.pet_care;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Dog")
public class Dog extends Pet {

    private String breed;
    private static final double baseCost = 240.0;

    public Dog(int petId, String name, String ownerName, int age, String breed) {
        super(petId, name, ownerName, age, "Dog");
        this.breed = breed;
    }

    public Dog() {
    }


    

    @Override
    public double calculateCareCost() {
        return baseCost + (age * 60);
    }

    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
        System.out.println("Care Cost: $" + calculateCareCost());
    }
}
