package com.example.pet_care;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetReository extends JpaRepository<Pet, Integer>{

  
}
