package com.example.pet_care;

public class Appointment {
  private String appointmentId;
  private Pet pet;              
  private String date;
  private String serviceType;
  private String status;

  public Appointment(String appointmentId, Pet pet, String date, String serviceType, String status) {
      this.appointmentId = appointmentId;
      this.pet = pet;
      this.date = date;
      this.serviceType = serviceType;
      this.status = status;
  }

  public double calculateBill() {
      double baseCost = pet.calculateCareCost();
      double serviceFee;

      serviceFee = switch (serviceType.toLowerCase()) {
          case "grooming" -> 270.0;
          case "checkup" -> 180.0;
          case "vaccination" -> 330.0;
          default -> 120.0;
      };

      return baseCost + serviceFee;
  }

  public void displayAppointmentSummary() {
      System.out.println("=== Appointment Summary ===");
      System.out.println("Appointment ID: " + appointmentId);
      System.out.println("Pet Name: " + pet.name);
      System.out.println("Pet Type: " + pet.type);
      System.out.println("Date: " + date);
      System.out.println("Service Type: " + serviceType);
      System.out.println("Status: " + status);
      System.out.println("Estimated Bill: $" + calculateBill());
      System.out.println("============================");
  }

  public String getAppointmentId() {
      return appointmentId;
  }

  public Pet getPet() {
      return pet;
  }

  public String getDate() {
      return date;
  }

  public String getServiceType() {
      return serviceType;
  }

  public String getStatus() {
      return status;
  }

  public void setStatus(String status) {
      this.status = status;
  }
}
