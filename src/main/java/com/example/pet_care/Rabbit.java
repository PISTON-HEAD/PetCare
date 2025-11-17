package com.example.pet_care;

public class Rabbit extends Pet {

    private boolean isVaccinated;
    private static final double baseCost = 150.0;

    public Rabbit(int petId, String name, String ownerName, int age, boolean isVaccinated) {
        super(petId, name, ownerName, age, "Rabbit");
        this.isVaccinated = isVaccinated;
    }

    @Override
    public double calculateCareCost() {
        return baseCost + (isVaccinated ? 0 : 200);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Vaccinated: " + (isVaccinated ? "Yes" : "No"));
        System.out.println("Care Cost: $" + calculateCareCost());
    }
}
