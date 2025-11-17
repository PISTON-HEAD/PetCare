package com.example.pet_care;

import java.util.ArrayList;

public interface ClinicService {
  String registerPet(Pet pet);
  ArrayList<Pet> getAllPets();
  ArrayList<Pet> verifyPetWithName(String name);
  Pet verifyPetWithId(int id);
  String bookAppointment(int petId, String serviceType, String date);
  String completeAppointment(String appointmentId);
  ArrayList<Appointment> sortAccordingtoBill();
  ArrayList<Pet> sortAccordingToNames();

}
