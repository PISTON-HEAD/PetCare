### **PetCare+ — Veterinary Clinic Management System (Spring Boot + OOP Integration Challenge)**

---

## 🧭 **Overview**

You are building a REST-based **Veterinary Management System** for a clinic called **PetCare+**,
where pet owners can register their pets, schedule checkups, get pet details, and track vaccination and grooming records.

The project should:

* Demonstrate **OOP design** (inheritance, encapsulation, polymorphism, composition)
* Use **Spring Boot annotations and dependency injection**
* Follow **layered architecture** (Controller → Service → Configuration)
* Support **profiles**, **beans**, and **REST APIs**

---

## 🧱 **System Requirements**

The system manages **different types of pets**, allows **appointment booking**, and handles **different service types**.

---

## 🐶 **Core Classes (OOP Design Layer)**

### 1. **Pet (Abstract Class)**

Represents a pet in the system.

**Fields:**

* `petId` (String)
* `name` (String)
* `ownerName` (String)
* `age` (int)
* `type` (String)

**Methods:**

* `displayInfo()` (non-abstract)
* `abstract double calculateCareCost()` → implemented differently for each pet type.

---

### 2. **Subclasses**

Create 3 subclasses:

#### 🐕 `Dog`

* Field: `breed` (String)
* `calculateCareCost()` = `baseCost + (age * 50)`

#### 🐈 `Cat`

* Field: `indoor` (boolean)
* `calculateCareCost()` = `baseCost + (indoor ? 100 : 150)`

#### 🐇 `Rabbit`

* Field: `isVaccinated` (boolean)
* `calculateCareCost()` = `baseCost + (isVaccinated ? 0 : 200)`

---

### 3. **Appointment (Composition Example)**

Represents an appointment that “has a” Pet.

**Fields:**

* `appointmentId` (String)
* `pet` (Pet)
* `date` (String)
* `serviceType` (String)
* `status` (String) — e.g., `"Scheduled"`, `"Completed"`

**Methods:**

* `calculateBill()` — uses `pet.calculateCareCost()`
* `displayAppointmentSummary()`

---

## 🧩 **Service Layer (Spring Boot Business Logic)**

### 1. **ClinicService (Interface)**

Defines business operations:

```java
public interface ClinicService {
    Pet registerPet(Pet pet);
    List<Pet> getAllPets();
    Appointment bookAppointment(String petId, String serviceType, String date);
    Appointment completeAppointment(String appointmentId);
    double calculateBill(String appointmentId);
}
```

---

### 2. **Service Implementations**

Two implementations:

* **@Service @Profile("memory")** → uses in-memory lists to store pets and appointments.
* **@Service @Profile("mock")** → simulates mock data (for testing).

You’ll use:

```java
@Qualifier("memoryClinicService")
@Profile("memory")
```

and

```java
@Qualifier("mockClinicService")
@Profile("mock")
```

---

## ⚙️ **Configuration Layer**

Create a `@Configuration` class called `ClinicConfig`.

Define:

* A few **@Bean** pets (for initialization)
* A default **@Bean** `ClinicService` instance (if no profile active)
* Use **@Value** for base care cost injected via `application.properties`:

  ```
  petcare.baseCost=500
  ```

Example:

```java
@Configuration
public class ClinicConfig {

    @Bean
    public Dog sampleDog() {
        return new Dog("P001", "Buddy", "John", 3, "Dog", "Labrador");
    }

    @Bean
    public Cat sampleCat() {
        return new Cat("P002", "Mittens", "Alice", 2, "Cat", true);
    }
}
```

---

## 🧩 **Controller Layer**

Create `ClinicController` with endpoints mapped using:

* `@RestController`
* `@RequestMapping("/api/clinic")`

---

### **Endpoints**

| Method | Mapping                     | Description                                                    |
| ------ | --------------------------- | -------------------------------------------------------------- |
| `POST` | `/register`                 | Register a new pet (`@RequestBody`)                            |
| `GET`  | `/pets`                     | Get all registered pets                                        |
| `POST` | `/book`                     | Book an appointment (`@RequestParam petId, serviceType, date`) |
| `POST` | `/complete/{appointmentId}` | Mark appointment as complete                                   |
| `GET`  | `/bill/{appointmentId}`     | Calculate bill for an appointment                              |
| `GET`  | `/pets/{petId}`             | Fetch pet details using `@PathVariable`                        |

---

### Example JSON Interaction

#### 📤 Register Pet

**POST** `/api/clinic/register`

```json
{
  "petId": "P1001",
  "name": "Bella",
  "ownerName": "Sarah",
  "age": 4,
  "type": "Dog",
  "breed": "Beagle"
}
```

**Response:**

```json
{
  "message": "Pet registered successfully",
  "petId": "P1001"
}
```

---

#### 📤 Book Appointment

**POST** `/api/clinic/book?petId=P1001&serviceType=Vaccination&date=2025-11-12`

**Response:**

```json
{
  "appointmentId": "A001",
  "petId": "P1001",
  "serviceType": "Vaccination",
  "status": "Scheduled"
}
```

---

#### 📤 Complete Appointment

**POST** `/api/clinic/complete/A001`

**Response:**

```json
{
  "appointmentId": "A001",
  "status": "Completed",
  "bill": 750.0
}
```

---

#### 📤 Calculate Bill

**GET** `/api/clinic/bill/A001`

**Response:**

```json
{
  "appointmentId": "A001",
  "billAmount": 750.0
}
```

---

## 🧠 **Concepts Tested**

| Concept                  | Demonstrated In                                                                                       |
| ------------------------ | ----------------------------------------------------------------------------------------------------- |
| **Encapsulation**        | Private fields + getters/setters                                                                      |
| **Inheritance**          | Pet hierarchy                                                                                         |
| **Polymorphism**         | Different care cost logic for Dog/Cat/Rabbit                                                          |
| **Composition**          | Appointment “has-a” Pet                                                                               |
| **Dependency Injection** | `@Autowired`, `@Qualifier`                                                                            |
| **Profiles**             | Switch between memory and mock service                                                                |
| **Configuration**        | `@Bean`, `@Value`                                                                                     |
| **REST Mapping**         | `@RestController`, `@RequestMapping`, `@PostMapping`, `@GetMapping`, `@PathVariable`, `@RequestParam` |
| **Layered Architecture** | Controller → Service → Config                                                                         |

---

## 🧩 **Bonus Challenge (Optional for Level 4)**

Add a **Ranking Endpoint**:

* `/api/clinic/rankings` → Shows top pets based on total bills accumulated from appointments (shows OOP + logic thinking).

---

Would you like me to now create the **package structure + class skeletons** (like how to structure `model`, `service`, `controller`, `config`, `profile`) for you next — so you can start building the project cleanly from the foundation?
