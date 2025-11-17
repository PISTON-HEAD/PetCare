package com.example.pet_care;

import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.stereotype.Component;


@Component
public class InMemory implements ClinicService{
  ArrayList<Pet> petList;
  ArrayList<Appointment> appointments;

  public InMemory() {
    this.petList = new ArrayList<>();
    this.appointments = new ArrayList<>();
  }


  @Override
  public Pet verifyPetWithId(int id)
  {
    for(Pet p: petList)
    {
      if(p.petId == id)
        return p;
    }
    return null;
  }

  @Override
  public ArrayList<Pet> verifyPetWithName(String name)
  {
    ArrayList<Pet> plist = new ArrayList<>();
    for(Pet p: petList)
    {
      if(p.getName().equals(name))
        plist.add(p);
    }
    return plist;
  }


  @Override
  public String registerPet(Pet pet) {
    if(petList.isEmpty())
    {
      petList.add(pet);
      return pet.name+" has been Registered Successfully with ID "+pet.petId;
    }
    Pet p = verifyPetWithId(pet.petId);
    if(p == null)
    {
      petList.add(pet);
      return pet.name+" has been Registered Successfully with ID "+pet.petId;
    }else{
      return "Pet already registered with ID: "+pet.petId;
    }
  }

  @Override
  public ArrayList<Pet> getAllPets() {
   
    
    return petList.isEmpty()?null:petList;
  }

  @Override
  public String bookAppointment(int petId, String serviceType, String date) {
    Pet checkPet = verifyPetWithId(petId);
    if(checkPet == null)
    {
      return "No Pet registered with Id: "+petId;
    }else{
      Appointment appointment = new Appointment("App-"+petId, checkPet, date, serviceType, "Scheduled");
      appointments.add(appointment);
      return String.format("Appointment has been scheduled successfully! %n %s on %s by %s", checkPet.name, date, checkPet.ownerName);
    }
  }

  protected Appointment checkAppointmentWithID(String id)
  {
    for(Appointment app: appointments)
    {
      if(app.getAppointmentId().equals(id))
        return app;
    }
    return null;
  }

  @Override
  public String completeAppointment(String appointmentId) {
    Appointment app = checkAppointmentWithID(appointmentId);
    if(app == null)
    {
      return "Please create an Appointment";
    }
    else{
      app.setStatus("Completed");
      return String.format("AppointmentId: %s%nStatus: %s%nTotal Cost: %.2f", appointmentId,app.getStatus(),app.calculateBill());
    }
  }

  @Override
  public ArrayList<Pet>  sortAccordingToNames()
  {
    if(petList.isEmpty())
    {
      return null;
    }petList.sort(Comparator.comparing(Pet::getName));
      
    return petList;
  }

  @Override
  public ArrayList<Appointment> sortAccordingtoBill()
  {
    if(appointments.isEmpty())
    {
      return null;
    }
    appointments.sort(Comparator.comparing(Appointment::calculateBill));
    
    return appointments;
  }
}


