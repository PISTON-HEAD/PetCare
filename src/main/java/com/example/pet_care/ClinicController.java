package com.example.pet_care;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/petcare")
public class ClinicController {
  
  public ClinicService clinicService;

  private final PetReository reository;

  @Autowired
  ClinicController(ClinicService service, PetReository reository)
  {
    this.reository = reository;
    this.clinicService = service;
  }

  @GetMapping("/pet")
  public ResponseEntity<?> getAllPets()
  {
    return ResponseEntity.ok(clinicService.getAllPets());
  }

  @GetMapping("/pet/{id}")
  public ResponseEntity<?> getPet(@PathVariable int id)
  {
    return ResponseEntity.ok(reository.findById(id));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerPets(@RequestBody petRecord record)
  {
    Pet pet;
    if (record.type.equals("Dog")) {
        pet = new Dog(record.petId,record.name,record.ownerName,record.age,record.breed);
    }else{
      pet = new Cat(record.petId,record.name,record.ownerName,record.age,true);
    }
    reository.save(pet);
    return ResponseEntity.ok(clinicService.registerPet(pet));

  }


  @PostMapping("/book")
  public ResponseEntity<?> bookAppointment(@RequestParam int petId, @RequestParam String serviceType, @RequestParam String date)
  {
    return ResponseEntity.ok(clinicService.bookAppointment(petId, serviceType, date) );
  }

  @GetMapping("/names-sort")
  public ResponseEntity<?> getSortedNames()
  {
    return ResponseEntity.ok(clinicService.sortAccordingToNames());
  }

  @GetMapping("/bills-sort")
  public ResponseEntity<?> getSortedBills()
  {
    return ResponseEntity.ok(clinicService.sortAccordingtoBill());
  }

  @GetMapping("/ping")
  public ResponseEntity<?> pingME()
  {
    return ResponseEntity.ok("I am PetCare+.....swimming in full power");
  }

  @GetMapping("/pets")
  public List<Pet> findAllPets()
  {
    return reository.findAll();
  }

  private static record  petRecord(int petId, String name, String ownerName, int age, String type, String breed) {

  }

}
/*
{
  "petId": "P1002",
  "name": "Rado",
  "ownerName": "Goku",
  "age": 3,
  "type": "Dog",
  "breed": "Alsation"
}



{
  "petId": "P1001",
  "name": "Bella",
  "ownerName": "Sarah",
  "age": 4,
  "type": "Dog",
  "breed": "Beagle"
}


{
  "petId": "P1003",
  "name": "Diamond",
  "ownerName": "Rushi",
  "age": 6,
  "type": "Dog",
  "breed": "Cross"
}



*/