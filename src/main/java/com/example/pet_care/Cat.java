package com.example.pet_care;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Cat")
public class Cat extends Pet {

    private boolean indoor;
    private static final double baseCost = 180.0;

    public Cat(int petId, String name, String ownerName, int age, boolean indoor) {
        super(petId, name, ownerName, age, "Cat");
        this.indoor = indoor;
    }

    public Cat() {
    }



    @Override
    public double calculateCareCost() {
        return baseCost + (indoor ? 120 : 150);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Indoor: " + (indoor ? "Yes" : "No"));
        System.out.println("Care Cost: $" + calculateCareCost());
    }
}
