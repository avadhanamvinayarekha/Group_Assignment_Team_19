/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.StudentWorkArea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Hp
 */
public class PayTuitionJPanel extends javax.swing.JPanel {
    
    private double currentBalance = 0.0;
    private double tuitionPerCredit = 1250.0; // $1,250 per credit hour
    private List<PaymentRecord> paymentHistory;
    private List<TuitionCharge> charges;
    private boolean tuitionPaid = false;

    /**
     * Creates new form PayTuitionJPanel
     */
    public PayTuitionJPanel() {
          paymentHistory = new ArrayList<>();
        charges = new ArrayList<>();
        
        initComponents();
        
        // Safety check
        if (paymentHistory == null) {
            paymentHistory = new ArrayList<>();
        }
        if (charges == null) {
            charges = new ArrayList<>();
        }
        
        setupTable();
        loadStudentAccount();
        calculateBalance();
        updateDisplay();
        setupButtons();
    }
    private void loadStudentAccount() {
    paymentHistory = new ArrayList<>();
    charges = new ArrayList<>();
    
    // Load enrolled courses and calculate tuition
    // Sample: Student enrolled in 2 courses (8 credits total)
    charges.add(new TuitionCharge("Fall 2025", "INFO 5100", 
        "Application Engineering", 4, tuitionPerCredit));
    charges.add(new TuitionCharge("Fall 2025", "INFO 6150", 
        "Web Design", 4, tuitionPerCredit));
    
    // Sample payment history (if any)
    // paymentHistory.add(new PaymentRecord("10/15/2024", "Partial Payment", 2500.0, "Paid half"));
}
    // PUT THESE METHODS - Calculate what you owe
private void calculateBalance() {
    // Start with 0
    currentBalance = 0.0;
    
    // Add all charges (courses you're taking)
    for (TuitionCharge charge : charges) {
        currentBalance += charge.getAmount();
    }
    
    // Subtract all payments made
    for (PaymentRecord payment : paymentHistory) {
        if (payment.getType().contains("Payment") || payment.getType().equals("Financial Aid")) {
            currentBalance -= payment.getAmount();
        } else if (payment.getType().equals("Refund")) {
            currentBalance -= payment.getAmount(); // Refunds reduce balance
        }
    }
    
    // Check if tuition is fully paid
    tuitionPaid = (currentBalance <= 0);
}

private double getTotalCharges() {
    double total = 0.0;
    for (TuitionCharge charge : charges) {
        total += charge.getAmount();
    }
    return total;
}

private double getTotalPaid() {
    double total = 0.0;
    for (PaymentRecord payment : paymentHistory) {
        if (payment.getType().contains("Payment") || payment.getType().equals("Financial Aid")) {
            total += payment.getAmount();
        }
    }
    return total;
}

public boolean isTuitionPaid() {
    return tuitionPaid;
}
// PUT THESE METHODS - Show payment history in table
private void setupTable() {
    tableHistory.setDefaultEditor(Object.class, null); // Make read-only
    tableHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
}

private void populatePaymentHistoryTable() {
    DefaultTableModel model = (DefaultTableModel) tableHistory.getModel();
    model.setRowCount(0); // Clear existing rows
    
    // Show all payment history
    for (PaymentRecord record : paymentHistory) {
        model.addRow(new Object[]{
            record.getDate(),
            record.getType(),
            String.format("$%.2f", record.getAmount()),
            record.getDescription()
        });
    }
    
    // If no history, show a message
    if (paymentHistory.isEmpty()) {
        model.addRow(new Object[]{
            "-", "No payments yet", "$0.00", "Make your first payment above"
        });
    }
}
// PUT THIS METHOD - Updates everything on screen
private void updateDisplay() {
    // Update balance label with color coding
    lblBalance.setText(String.format("Current Balance: $%.2f", currentBalance));
    
    // Color code based on balance
    if (currentBalance <= 0) {
        lblBalance.setForeground(new java.awt.Color(0, 150, 0)); // Green - Paid!
        lblBalance.setText("Current Balance: $0.00 - PAID IN FULL! ✓");
    } else if (currentBalance > 0) {
        lblBalance.setForeground(new java.awt.Color(200, 0, 0)); // Red - Owe money
    }
    
    // Update title with payment status
    if (tuitionPaid) {
        lblTitle.setText("Pay Tuition - Account Paid ✓");
    } else {
        lblTitle.setText("Pay Tuition - Balance Due");
    }
    
    // Update table
    populatePaymentHistoryTable();
}
// PUT THIS METHOD - Updates everything on screen
 
// PUT THIS METHOD - Pay everything you owe
private void payFullBalance() {
    // CHECK: Is balance 0 or negative?
    if (currentBalance <= 0) {
        JOptionPane.showMessageDialog(this,
            "No balance to pay!\n\n" +
            "Your account is paid in full.",
            "No Balance Due",
            JOptionPane.INFORMATION_MESSAGE);
        return; // DO NOT add to history!
    }
    
    // Show confirmation
    int confirm = JOptionPane.showConfirmDialog(this,
        "Pay full balance?\n\n" +
        "Amount to pay: $" + String.format("%.2f", currentBalance) + "\n\n" +
        "This will clear your entire balance.",
        "Confirm Payment",
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        double amountPaid = currentBalance;
        
        // Create payment record
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        PaymentRecord payment = new PaymentRecord(
            date,
            "Full Payment",
            amountPaid,
            "Paid full tuition balance"
        );
        
        paymentHistory.add(payment);
        
        // Recalculate balance
        calculateBalance();
        updateDisplay();
        
        // Success message
        JOptionPane.showMessageDialog(this,
            "Payment successful!\n\n" +
            "Amount paid: $" + String.format("%.2f", amountPaid) + "\n" +
            "New balance: $0.00\n\n" +
            "Your transcript is now available!",
            "Payment Successful",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Clear amount field
        txtAmount.setText("");
    }
}
// PUT THIS METHOD - Pay a specific amount
private void payCustomAmount() {
    // CHECK: Is balance 0 or negative?
    if (currentBalance <= 0) {
        JOptionPane.showMessageDialog(this,
            "No balance to pay!\n\n" +
            "Your account is paid in full.",
            "No Balance Due",
            JOptionPane.INFORMATION_MESSAGE);
        return; // DO NOT add to history!
    }
    
    // Get amount from text field
    String amountText = txtAmount.getText().trim();
    
    if (amountText.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Please enter a payment amount.",
            "Amount Required",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Parse amount
    double amount;
    try {
        amount = Double.parseDouble(amountText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
            "Invalid amount! Please enter a valid number.\n\n" +
            "Example: 1000.00",
            "Invalid Amount",
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Validate amount
    if (amount <= 0) {
        JOptionPane.showMessageDialog(this,
            "Please enter a positive amount.",
            "Invalid Amount",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (amount > currentBalance) {
        JOptionPane.showMessageDialog(this,
            "Payment amount exceeds balance!\n\n" +
            "Your balance: $" + String.format("%.2f", currentBalance) + "\n" +
            "Amount entered: $" + String.format("%.2f", amount) + "\n\n" +
            "Do you want to pay the full balance instead?",
            "Amount Too High",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Confirm payment
    int confirm = JOptionPane.showConfirmDialog(this,
        "Make payment?\n\n" +
        "Amount: $" + String.format("%.2f", amount) + "\n" +
        "Current balance: $" + String.format("%.2f", currentBalance) + "\n" +
        "Remaining after payment: $" + String.format("%.2f", currentBalance - amount),
        "Confirm Payment",
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        // Create payment record
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        PaymentRecord payment = new PaymentRecord(
            date,
            "Partial Payment",
            amount,
            "Partial tuition payment"
        );
        
        paymentHistory.add(payment);
        
        // Recalculate balance
        calculateBalance();
        updateDisplay();
        
        // Success message
        String message = "Payment successful!\n\n" +
                        "Amount paid: $" + String.format("%.2f", amount) + "\n" +
                        "Remaining balance: $" + String.format("%.2f", currentBalance);
        
        if (currentBalance <= 0) {
            message += "\n\nAccount paid in full! Your transcript is now available!";
        }
        
        JOptionPane.showMessageDialog(this,
            message,
            "Payment Successful",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Clear amount field
        txtAmount.setText("");
    }
}
// PUT THIS METHOD - Refunds when student drops a course
public void processCourseDropRefund(String courseId, String courseName, int credits) {
    // Calculate refund amount
    double refundAmount = credits * tuitionPerCredit;
    
    // Create refund record
    String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    PaymentRecord refund = new PaymentRecord(
        date,
        "Refund",
        refundAmount,
        "Course dropped: " + courseId + " - " + courseName
    );
    
    paymentHistory.add(refund);
    
    // Remove charge from charges list
    charges.removeIf(charge -> charge.getCourseId().equals(courseId));
    
    // Recalculate balance
    calculateBalance();
    updateDisplay();
    
    // Show refund message
    JOptionPane.showMessageDialog(this,
        "Course Dropped - Refund Processed\n\n" +
        "Course: " + courseId + " - " + courseName + "\n" +
        "Credits: " + credits + "\n" +
        "Refund amount: $" + String.format("%.2f", refundAmount) + "\n\n" +
        "New balance: $" + String.format("%.2f", currentBalance),
        "Refund Issued",
        JOptionPane.INFORMATION_MESSAGE);
}

// Method to add a new course charge
public void addCourseCharge(String semester, String courseId, String courseName, int credits) {
    TuitionCharge charge = new TuitionCharge(semester, courseId, courseName, credits, tuitionPerCredit);
    charges.add(charge);
    
    calculateBalance();
    updateDisplay();
    
    JOptionPane.showMessageDialog(this,
        "Course Added - Tuition Charged\n\n" +
        "Course: " + courseId + " - " + courseName + "\n" +
        "Credits: " + credits + "\n" +
        "Charge: $" + String.format("%.2f", credits * tuitionPerCredit) + "\n\n" +
        "New balance: $" + String.format("%.2f", currentBalance),
        "Tuition Billed",
        JOptionPane.INFORMATION_MESSAGE);
}
// PUT THIS METHOD - Makes all buttons work!
private void setupButtons() {
    // Pay Full Balance Button
    btnPayFull.addActionListener(e -> payFullBalance());
    
    // Pay Custom Amount Button
    btnPayAmount.addActionListener(e -> payCustomAmount());
    
    // Refresh Button
    btnRefresh.addActionListener(e -> {
        calculateBalance();
        updateDisplay();
        
        JOptionPane.showMessageDialog(this,
            "Account refreshed!\n\n" +
            "Current balance: $" + String.format("%.2f", currentBalance) + "\n" +
            "Total charges: $" + String.format("%.2f", getTotalCharges()) + "\n" +
            "Total paid: $" + String.format("%.2f", getTotalPaid()),
            "Account Refreshed",
            JOptionPane.INFORMATION_MESSAGE);
    });
    
    // Clear amount field on focus
    txtAmount.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            txtAmount.selectAll();
        }
    });
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        lblAmountLabel = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnPayFull = new javax.swing.JButton();
        btnPayAmount = new javax.swing.JButton();
        lblHistory = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitle.setText("Pay Tuition");

        lblBalance.setText("Current Balance: $0.00");

        lblAmountLabel.setText("Payment Amount:");

        txtAmount.setText(" ");

        btnPayFull.setText("Pay Full Balance");

        btnPayAmount.setText("Pay Entered Amount");

        lblHistory.setText("Payment History");

        btnRefresh.setText("Refresh");

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Type", "Amount", "Description"
            }
        ));
        jScrollPane1.setViewportView(tableHistory);

        scrollPane.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAmountLabel)
                                .addGap(27, 27, 27)
                                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblBalance)
                            .addComponent(lblHistory)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnPayFull)
                        .addGap(210, 210, 210)
                        .addComponent(btnPayAmount))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(btnRefresh))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTitle)
                .addGap(38, 38, 38)
                .addComponent(lblBalance)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmountLabel)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPayFull)
                    .addComponent(btnPayAmount))
                .addGap(43, 43, 43)
                .addComponent(lblHistory)
                .addGap(29, 29, 29)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPayAmount;
    private javax.swing.JButton btnPayFull;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAmountLabel;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblHistory;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tableHistory;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
// End of variables declaration                   
    
    // PUT THESE CLASSES HERE - At the very bottom!
    
    // Class 1: Payment Record (stores payment info)
    class PaymentRecord {
        private String date;
        private String type; // "Full Payment", "Partial Payment", "Refund", "Financial Aid"
        private double amount;
        private String description;
        
        public PaymentRecord(String date, String type, double amount, String description) {
            this.date = date;
            this.type = type;
            this.amount = amount;
            this.description = description;
        }
        
        public String getDate() { return date; }
        public String getType() { return type; }
        public double getAmount() { return amount; }
        public String getDescription() { return description; }
    }
    
    // Class 2: Tuition Charge (stores course charges)
    class TuitionCharge {
        private String semester;
        private String courseId;
        private String courseName;
        private int credits;
        private double amountPerCredit;
        
        public TuitionCharge(String semester, String courseId, String courseName, 
                            int credits, double amountPerCredit) {
            this.semester = semester;
            this.courseId = courseId;
            this.courseName = courseName;
            this.credits = credits;
            this.amountPerCredit = amountPerCredit;
        }
        
        public double getAmount() {
            return credits * amountPerCredit;
        }
        
        public String getSemester() { return semester; }
        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public int getCredits() { return credits; }
    }

}
