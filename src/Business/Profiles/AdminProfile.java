/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Profiles;
import info5100.university.example.Persona.Person;
/**
 *
 * @author Hp
 */
public class AdminProfile {
        Person person;
    String adminId;
    
    public AdminProfile(Person p) {
        this.person = p;
        this.adminId = "ADMIN" + System.currentTimeMillis();
    }
    
    public Person getPerson() {
        return person;
    }
    
    public String getAdminId() {
        return adminId;
    }
    
    @Override
    public String toString() {
        return person.getPersonId() + " (Admin)";
    }

}
