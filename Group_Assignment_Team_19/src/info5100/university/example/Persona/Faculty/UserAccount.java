/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info5100.university.example.Persona.Faculty;

import info5100.university.example.Persona.Person;

/**
 *
 * @author iram3
 */
public class UserAccount {
     private Person person;
    
    public UserAccount(Person p) {
        this.person = p;
    }
    
    public Person getPerson() {
        return person;
    }
    
    public boolean isMatch(String id) {
        if (person == null) return false;
        return person.getPersonId().equals(id);
    }
    
    public String getPersonId() {
        return person != null ? person.getPersonId() : null;
    }
    
    @Override
    public String toString() {
        return person != null ? person.toString() : "No person assigned";
    }
    
}
