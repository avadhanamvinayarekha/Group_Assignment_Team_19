/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Financial;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;
/**
 *
 * @author Hp
 */
public class TuitionAccount {
        StudentProfile student;
    double balance;
    ArrayList<TuitionTransaction> transactions;
    
    public TuitionAccount(StudentProfile student) {
        this.student = student;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }
    
    public void chargeForCourse(int creditHours) {
        double amount = creditHours * 1500.0;  // $1500 per credit hour
        balance += amount;
        
        TuitionTransaction txn = new TuitionTransaction("CHARGE", amount, "Course enrollment");
        transactions.add(txn);
    }
    
    public void makePayment(double amount) {
        if (amount <= 0) return;
        
        balance -= amount;
        TuitionTransaction txn = new TuitionTransaction("PAYMENT", amount, "Tuition payment");
        transactions.add(txn);
    }
    
    public void refundForCourse(int creditHours) {
        double amount = creditHours * 1500.0;
        balance -= amount;
        
        TuitionTransaction txn = new TuitionTransaction("REFUND", amount, "Course drop refund");
        transactions.add(txn);
    }
    
    public boolean hasBalance() {
        return balance > 0;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public ArrayList<TuitionTransaction> getTransactions() {
        return transactions;
    }

}
