# Planting-Trees
This is an app to help track reforestation activities in cities and surroundings.



## Day 1 AC - 03/02/2026
- App doesnt start.
- Errors on exiting code solved.
- Changing properties with info -> it was completely empty LOL
- Initial DB created
- debugging completed, app compiles!

## Day 2 - 04/02/2026
- rethinking the app design: creating a realistic MVP.
- polishing the uml.

## Day 3 - 05/02/2026
- working on the services and services impl.

## Day 4 - 09/02/2026
- implemented lombok.
- switched to intellij.
- controllers corrected.
- project up to date following new UML.
- App running!

## Day 5 - 10/02/2026
- seeder created.
- postman tested (works!).
- test with swagger (doesnt work).

## Day 6 - 11/02/2026
- swagger created!.
- el swagger peta más que una escopeta de valines!
- Security configuration in place and working!

## Day 7 - 12/02/2026
- Implemented Spring Security with JWT authentication and role-based authorization.
- Added Service layers for User, AlertType, Note, Photo, Plant, and Specie.
- Created Controllers for Specie, Note, Alert, AlertType, HealthStatus, and Photo.
- Added comprehensive bash scripts for end-to-end API testing.
- Configuration updates and database properties adjustments.

## Day 8 - 15/02/2026
- Refactored Photo System: Removed `Photo` entity.
- Implemented direct BLOB storage for `User` profile picture and `Plant` photo.
- Updated `UserService`, `PlantService`, and DTOs to handle Base64 image data.
- Enhanced `DataSeeder` and `test_api.sh` to reflect architecture changes.
- Validated build and consistency with Java 21.

## Swagger (Does not work 🥲 )
http://localhost:8080/swagger-ui/index.html


## Next Steps
- Time to start with front and React.
  - first step would be a login form.
- Swagger not really working... moving on for now.
------
- Revisar validaciones de los services
- Tests automáticos
- ...

## In Next Releases...
- Exports
- Logs
- Zones
- Add complexity to coordinates (maybe)


## Project Specification

This application is designed to help organizations and individuals manage and monitor their reforestation projects. 
It centralizes relevant information, facilitates collaboration among participants, and ensures proper tracking of plants, species, alerts, and overall project health. 
This means the app has ROLES:
- ADMIN: The Admin is responsible for the overall administration and governance of the application. This role does not directly participate in reforestation activities but ensures the proper functioning and management of the platform.
- BOTANIST: Has the Know-how! This role represents the plant expert and has advanced permissions related to biological and project data.
- GUARDIAN: The Guardian supports field activities such as planting, monitoring, and responding to issues.


## CLASS DEFINITION - WHAT MEANS WHAT

- Plant: Is the main class, the core of the project. It represents each real-world plant that is being tracked in the system.
- Species: Every plant has a species from which it gets its name and general description. This also serves educational purposes. In the field, it is not always easy to recognize all plants, so this helps with identification.
- Health Status: This helps track the evolution and condition of the plant. For example: "Growing well but slowly", "Not growing but still alive", etc. This should be defined by the Botanist.
- Plant Verification Status: This simple enum helps verify that the information introduced into the system about a plant is correct. It allows the Botanist to oversee and control the process of inserting new plant data.
- Notes: These are comments created by users to help track the plant. They can serve many purposes, such as noting that the plant has fruits, that it seems a bit dry but nothing serious, or helping to locate the plant by describing its surroundings.
- Alert Type: This defines the type of alerts related to a plant. They are usually standard (e.g., needs watering, protector is broken), but they can become more complex. Therefore, it is up to the Botanist to define them in the system.
- Alert: Similar to a ticket system, alerts have a type and are assigned to plants so that Guardians can resolve them.


### ROLES requirements

- ADMIN:
  - Full access to all system data.
  - Manage users and assign roles.
  - View, edit, or delete any record.

- BOTANIST:
  - Create and manage plant records.
  - Verify plants registered in the system.
  - Create and manage species.
  - Define and manage plant health statuses.
  - Create alert types.
  - Create alerts.
  - Add notes.

- GUARDIAN:
  - Register new plants.
  - Add notes and observations.
  - Create alerts (if enabled by the project).
  - Attend and resolve alerts.


### ISSUES THAT I SEE...

  1. TIMESTAMP
There is a timeStamp option with annotations:

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

Maybe we could use this in Plants, Notes and Alerts!
I have forgotten to implement date_atended in the Alert :)

  2. PHOTO
Maybe this should just be a string pointing at a repository.
Right now it doesn't make much sense to be honest and byte type makes everything more complicated than necessary.

  3. CONTROLLERS
Welp, whole world to be built! xD


...

### BRAINSTORMING IDEAS FOR FUTURE PLANS

  1. Base user with limited access to the app for uni/curious ppl 

  2. AREAS Class zona
    - Be able to store in db the coordinates of at least 3 points then triangulate those on a map create a inside of that "area" and then be able to check if users are in that area 
    
  3. Security and JTF tokens (import from XPlore)