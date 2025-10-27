
# Digital University System - Student Use Case

## 1. Project Title

**Access-Controlled Digital University System - Student Portal**

## 2. Team Information

* Team Member: **Tasmiya Parveen [002509350]**

  * Role: Student Use Case Implementation
  * Responsibilities: All student-facing features

## 3. Project Overview

This project implements a comprehensive student portal for a digital university system with role-based access control. Students can register for courses, manage enrollments, submit assignments, track graduation progress, pay tuition, and manage their profiles.

**Key Features:**

* Secure authentication and authorization
* Course registration with search (3 methods)
* Assignment submission and tracking
* Transcript with GPA calculation
* Graduation audit system
* Tuition payment system
* Profile management

## 4. Installation & Setup Instructions

**Prerequisites:**

* Java JDK 8 or higher
* NetBeans IDE 12.0 or higher
* No external libraries required

**Setup Steps:**

1. Extract the zip file
2. Open NetBeans IDE
3. File → Open Project → Select the extracted folder
4. Right-click project → Clean and Build
5. Right-click project → Run (or press F6)

## 5. Authentication & Access Control

**Login Process:**

* Users enter username and password
* System authenticates against UserAccountDirectory
* On success, routes to appropriate dashboard based on role
* Maximum 3 failed attempts allowed

**Test Credentials:**

* Student: `student1` / `pass123`
* Admin: `admin` / `admin123`
* Faculty: `faculty1` / `pass123`

**Authorization Rules:**

* Students can only access their own data
* Students cannot modify grades or system settings
* Students cannot view other students' information

## 6. Features Implemented

### **Student Dashboard**

* Implemented by: **Tasmiya Parveen**
* Features:

  * Home menu with 6 options
  * CardLayout navigation
  * Logout functionality

### **Course Registration**

* 3 search methods: Course ID, Faculty Name, Course Name
* View courses by semester
* Enroll with 8-credit limit validation
* Drop courses
* Real-time credit load tracking

### **My Enrollments**

* View enrolled courses
* Submit assignments with file attachment
* View assignment history
* Drop courses with confirmation

### **Transcript**

* View academic history
* Filter by semester
* Automatic GPA calculation (Term & Overall)
* Academic standing determination
* Printable transcript

### **Graduation Audit**

* Track 32 credit requirement
* Monitor INFO 5100 core course
* Visual progress bar
* Eligibility determination

### **Pay Tuition**

* $1,250 per credit billing
* Pay full or partial amounts
* Payment history tracking
* Automatic refunds on course drop
* Transcript access control

### **Profile Management**

* View/edit personal information
* Input validation
* Emergency contact management
* Profile picture customization

## 7. Usage Instructions

**Step 1: Login**

* Launch application
* Enter: `student1` / `pass123`
* Click Login

**Step 2: Navigate**

* Click any menu option from dashboard
* Or use original NetBeans buttons

**Step 3: Use Features**

* **Register for Course:** Select semester → Select course → Click Enroll
* **Submit Assignment:** My Enrollments → Double-click course → Submit Assignment
* **View Transcript:** Transcript → Select semester filter
* **Pay Tuition:** Pay Tuition → Enter amount → Pay
* **Edit Profile:** My Profile → Double-click field → Edit → Save

## 8. Testing Guide

**Test Case 1: Course Registration**

1. Login as student1
2. Go to Course Registration
3. Select "Fall 2024"
4. Enroll in CSE101 (3 credits) - Should succeed
5. Enroll in CSE201 (4 credits) - Should succeed (7 total)
6. Try to enroll in MAT101 (4 credits) - Should fail (would exceed 8)

**Test Case 2: Assignment Submission**

1. Go to My Enrollments
2. Double-click INFO 5100
3. Choose "Submit Assignment"
4. Fill form and submit
5. Double-click again → View Assignments
6. Verify assignment appears

**Test Case 3: Tuition Payment**

1. Go to Pay Tuition
2. Balance should show $10,000 (8 credits × $1,250)
3. Pay $5,000
4. Balance updates to $5,000
5. Drop a course → Automatic refund
6. Transcript blocked until paid

## 9. Challenges & Solutions

**Challenge 1:** NullPointerException when opening panels

* **Solution:** Initialize all ArrayList objects before `initComponents()`

**Challenge 2:** Lambda variable conflicts in button listeners

* **Solution:** Use different variable names (e1, e2, e3) for each listener

**Challenge 3:** Duplicate UserAccount classes in different packages

* **Solution:** Created separate constructors for each package's needs

**Challenge 4:** Password field security

* **Solution:** Changed JTextField to JPasswordField for password input

## 10. Future Enhancements

* Real database integration instead of in-memory data
* Email notifications for enrollment/payments
* File upload for actual assignment submissions
* PDF generation for transcripts
* Integration with external payment gateways
* Mobile responsive design
* Real-time chat with advisors
* Course recommendation system based on completed courses

## 11. Contribution Breakdown

**Tasmiya Parveen — 100% of Student Use Case**

* Designed and implemented all 6 student panels
* Integrated authentication and authorization
* Implemented all validation and null checks
* Created comprehensive feature set
* Documented all code
* Testing and debugging

**Code Files Created:**

* CourseRegistrationJPanel.java (450+ lines)
* MyEnrollmentsJPanel.java (600+ lines)
* TranscriptJPanel.java (550+ lines)
* GraduationAuditJPanel.java (350+ lines)
* PayTuitionJPanel.java (500+ lines)
* ProfileManagementJPanel.java (550+ lines)
* StudentDashboard.java (300+ lines)
* MainLoginFrame.java (200+ lines)
* ConfigureSystem.java (150+ lines)
* UserAccount.java (80+ lines)
* UserAccountDirectory.java (100+ lines)

**Total Lines of Code:** 3,500+

---

# Digital University System - Faculty Use Case

## 1. Project Title

**Access-Controlled Digital University System - Faculty Portal**

## 2. Team Information

* Team Member: **Anusha Kashipura Putugnal [003154069]**

  * Role: Faculty Use Case Implementation
  * Responsibilities: All faculty-facing features including course management, student grading, performance reporting, and tuition insights

## 3. Project Overview

This project implements a comprehensive faculty portal for a digital university system with role-based access control. Faculty members can manage their assigned courses, grade students, generate performance reports, view tuition insights, and manage their profiles.

**Key Features:**

* Secure authentication and authorization for faculty
* Course management (view/edit details, open/close enrollment)
* Student grading system with automatic GPA calculation
* Student ranking by performance
* Course performance reports with grade distribution
* Tuition revenue insights per course
* Profile management

## 4. Installation & Setup Instructions

**Prerequisites:**

* Java JDK 8 or higher
* NetBeans IDE 12.0 or higher
* No external libraries required

**Setup Steps:**

1. Extract the zip file
2. Open NetBeans IDE
3. File → Open Project → Select the extracted folder
4. Right-click project → Clean and Build
5. Right-click project → Run (or press F6)

## 5. Authentication & Access Control

**Login Process:**

* Faculty members enter username and password
* System authenticates against UserAccountDirectory
* On success, routes to Faculty Dashboard
* Maximum 3 failed attempts allowed

**Test Credentials:**

* Faculty: `faculty1` / `pass123` through `faculty10` / `pass123`
* Admin: `admin` / `admin123`
* Student: `student1` / `pass123`

**Authorization Rules:**

* Faculty can only access courses assigned to them
* Faculty can grade students enrolled in their courses
* Faculty can view student rosters and performance data
* Faculty cannot access other faculty members' courses
* Faculty cannot modify system settings or user accounts

## 6. Features Implemented

### **Faculty Dashboard**

* Implemented by: **Anusha Kashipura Putugnal**
* Features:

  * Home menu with 5 main options
  * Course Management
  * Grade Students
  * Performance Reports
  * Tuition Insight
  * My Profile
  * Logout functionality

### **Course Management (CourseManagementJPanel)**

* View all assigned courses
* Display course details:

  * Course ID and Name
  * Schedule (Mon/Wed 9:00-10:30)
  * Current enrollment vs capacity
  * Enrollment status (Open/Closed)
* Edit course details:

  * Title, Description, Schedule, Capacity, Syllabus
* Open/Close enrollment before semester starts
* Refresh course list

### **Grade Students (GradeStudentsJPanel)**

* Select from assigned courses
* View enrolled students per course:

  * Student ID, Student Name, Email, Current Grade, Overall GPA
* Assign letter grades (A, A-, B+, B, B-, C+, C, C-, F)
* Automatic GPA point calculation:

  * A = 4.0, A- = 3.7, B+ = 3.3, B = 3.0, B- = 2.7, C+ = 2.3, C = 2.0, C- = 1.7, F = 0.0
* Rank students by performance (highest to lowest GPA)
* Calculate and display class GPA
* Real-time updates after grading

### **Performance Reports (PerformanceReportsJPanel)**

* Filter reports by semester
* Select course from assigned courses
* Generate reports including:

  * **Average Grade**, **Enrollment Count**, **Grade Distribution** (count & % by letter grade)
* Visual table with Grade / Count / Percentage
* Export to CSV (placeholder)
* Refresh data

### **Tuition Insight (TuitionInsightJPanel)**

* View tuition collected from enrolled students
* Display per course:

  * Course ID, Course Name, Enrolled count, Tuition per student ($1,500 per credit × credit hours), Total revenue
* Calculate total revenue across assigned courses
* Tuition rate: $1,500 per credit hour
* Refresh functionality

### **My Profile** (Future Enhancement)

* View and edit profile details, office hours/location

## 7. Usage Instructions

### Getting Started

**Step 1: Login** → `faculty1` / `pass123` → Login → Faculty Dashboard opens
**Step 2: Navigate** → Click any dashboard button

### Feature-Specific Usage

* **Managing Courses:** Manage details, toggle enrollment, refresh
* **Grading Students:** Load students → Assign grade → Rank → View Class GPA
* **Performance Reports:** Select semester & course → Generate → (optional) Export
* **Tuition Insights:** Review per-course and total revenue → Refresh

## 8. Testing Guide

Covers Course Management, Student Grading, Performance Reports, Tuition Insights, and Authorization as outlined (steps unchanged from original text).

## 9. Challenges & Solutions

* Multiple UserAccount classes → separate classes/constructors
* Accurate class GPA → iterator with null checks
* Grade distribution totals → HashMap counts with percentage calc
* Tuition per course → credits × $1,500 × enrolled
* Authorization filtering → `FacultyProfile.getFacultyAssignments()`

## 10. Future Enhancements

(As listed in original: real-time grading, attendance, exports, email integration, analytics charts, forums, office hours scheduling, syllabus upload, assignment/peer grading, curves, historical reports.)

## 11. Contribution Breakdown

**Anusha Kashipura Putugnal — 100% of Faculty Use Case**

**Responsibilities:**
Designed and implemented 5 faculty panels, integrated with model, grading algorithms, reporting, tuition analytics, validation, documentation, testing.

**Code Files Created/Modified:**

* FacultyDashboard.java (250+ lines)
* CourseManagementJPanel.java (300+ lines)
* GradeStudentsJPanel.java (400+ lines)
* PerformanceReportsJPanel.java (350+ lines)
* TuitionInsightJPanel.java (250+ lines)
* AssignGradeDialog.java (150+ lines)
* CourseDetailsJPanel.java (200+ lines)

**Total Lines of Code:** 1,900+

**Key Implementations:**
GPA mapping, student ranking, revenue calculation, grade distribution, semester filtering, iterators, null safety.

**Testing Performed:**
10 faculty accounts, grading accuracy, authorization, reports, tuition sums, access control.

**Documentation:**
JavaDoc and inline comments, method signatures, naming conventions, README.

**Integration:**
ConfigureSystem test data; UniversitySystem; FacultyProfile/Assignment; CourseOffer/Seat; StudentProfile.

**Validation & Error Handling:**
Null checks, input validation, confirmation dialogs, try-catch, safe navigation.

**Additional Notes:**
Grade point mapping, tuition calc, performance metrics, security constraints remain as specified.

**Last Updated:** October 26, 2025
**Version:** 1.0
**Status:** Complete and Ready for Submission

---

# Digital University System - Admin Use Case

## 1. Project Title

**Access-Controlled Digital University System - Administrator Portal**

## 2. Team Information

* Team Member: **Avadhanam Vinaya Rekha [002341102]**

  * Role: Administrator Use Case Implementation
  * Responsibilities: User account management, person registration, student and faculty records management, and university-level analytics

## 3. Project Overview

This project implements a comprehensive administrator portal for a digital university system with role-based access control. Administrators can manage all aspects of the university including user accounts, student and faculty records, and view university-wide analytics.

**Key Features:**

* Secure authentication and authorization for administrators
* User account management (create, modify, delete)
* Person registration with role assignment
* Student records management with search functionality
* Faculty records management and course assignment
* University-level analytics dashboard
* Profile management
* Duplicate prevention and auto-generated IDs

## 4. Installation & Setup Instructions

**Prerequisites:**

* Java JDK 8 or higher
* NetBeans IDE 12.0 or higher
* No external libraries required

**Setup Steps:**

1. Extract the zip file
2. Open NetBeans IDE
3. File → Open Project → Select the extracted folder
4. Right-click project → Clean and Build
5. Right-click project → Run (or press F6)

## 5. Authentication & Access Control

**Login Process:**

* Administrators enter username and password
* System authenticates against UserAccountDirectory
* On success, routes to Admin Dashboard
* Maximum 3 failed attempts allowed

**Test Credentials:**

* Admin: `admin` / `admin123`
* Faculty: `faculty1` / `pass123` through `faculty10` / `pass123`
* Student: `student1` / `pass123` through `student10` / `pass123`

**Authorization Rules:**

* Administrators have full system access
* Can view and modify all user accounts
* Can register new users (students, faculty, admin)
* Can access all student and faculty records
* Can view university-wide analytics and reports
* Cannot be deleted by other admins (safety feature)
* Proper logout returns to login screen

## 6. Features Implemented

### **Admin Dashboard**

* Implemented by: **Avadhanam Vinaya Rekha**
* Features:

  * Home menu with 5 main options
  * Register Persons
  * Manage Students
  * Manage Faculty
  * Analytics Dashboard
  * My Profile
  * Logout functionality
  * Color-coded UI (green theme)

### **Register Persons (RegisterPersonsJPanel)**

* Register new individuals with role assignment
* Roles: Student, Faculty, Admin
* Features:

  * Required fields: Full Name, Email (validated), Contact, Role
  * **Duplicate Prevention:** email uniqueness check
  * **Auto-Generated IDs:** S001/F001/A001 style
  * Default password: `pass123` for new users
  * Real-time table and refresh
  * Input validation and clear error messages

### **Manage Students (ManageStudentJPanel)**

* View students with table: ID, Name, Department, Email, GPA, Credits
* **3 Search Methods:** Name, Student ID, Department
* Edit student (email/contact), Delete (with cascade to UserAccount), View Transcript, Refresh

### **Manage Faculty (ManageFacultyJPanel)**

* View faculty with table: Faculty ID, Name, Dept, Email, Office, #Courses
* **3 Search Methods:** Name, Faculty ID, Department
* Edit (office location/hours), Delete (cascade), Assign to Course (placeholder), Refresh

### **Analytics Dashboard (AnalyticsDashboardJPanel)**

* **Active Users by Role:** counts and total
* **Courses Per Semester:** semester vs total courses
* **Enrollment Per Course:** course, enrolled, capacity, % full
* **Tuition Revenue Summary:** per-semester revenue and grand total
* Refresh updates all sections in real-time

### **My Profile**

* View admin profile (ID, Name, Email)
* Edit placeholder

## 7. Usage Instructions

* **Login:** `admin` / `admin123` → Admin Dashboard
* **Register Persons:** Fill, validate, register, view in table
* **Manage Students/Faculty:** Search (3 ways), edit, delete (with confirmation), view transcript/assignments as applicable, refresh
* **Analytics:** Open dashboard → review 4 sections → Refresh Data

## 8. Testing Guide

Covers: Register New Student, Duplicate Email Prevention, Search Students (3 methods), Edit Student, Delete Student, View Student Transcript, Faculty Management, Analytics Dashboard, Authorization Check (steps unchanged from original text).

## 9. Challenges & Solutions

* Duplicate email registration → pre-check over person list
* Auto-generated IDs per role → directory-based sequential IDs
* Safe deletion across directories/accounts → two-step cascade
* Tuition revenue aggregation → iterate CourseSchedules/CourseOffers and sum
* Flexible 3-way search → case-insensitive name/department, exact ID

## 10. Future Enhancements

(User management, record management, analytics visuals/trends, course assignment tooling, communications — as listed in original.)

## 11. Contribution Breakdown

**Avadhanam Vinaya Rekha — 100% of Admin Use Case**

### Responsibilities

Admin panels design/implementation, user management, person registration, student/faculty management, analytics dashboard, validation & error handling, documentation, testing.

### Code Files Created/Modified

* AdminDashboard.java (200+)
* RegisterPersonsJPanel.java (350+)
* ManageStudentJPanel.java (450+)
* ManageFacultyJPanel.java (400+)
* AnalyticsDashboardJPanel.java (400+)

**Total Lines of Code:** ~1,800

### Key Algorithms Implemented

* Duplicate email detection
* Revenue calculation
* Search (Name/ID/Department)
* Cascading deletion

### Testing Performed

30-person dataset, searches, duplicate prevention, ID generation, cascades, analytics, authorization, validations.

### Documentation

JavaDoc, inline comments, method docs, README, examples.

### Integration

ConfigureSystem data; UniversitySystem; Person/Student/Faculty directories; UserAccountDirectory; CourseSchedule/Transcript.

### Validation & Error Handling

Null checks, regex email validation, required fields, confirmations, try-catch, clear messages, safe table ops.

---

## Additional Implementation Details

### Data Pre-Population (ConfigureSystem)

* 1 Department (Information Systems)
* 30 Persons
* 10 Students (with seats)
* 10 Faculty (assigned to courses)
* 1 Admin
* 1 Semester (Fall2025)
* 5 Course Offers with faculty assigned
* Students with seat assignments

### Search Implementation Details

* **By Name:** case-insensitive `.contains()`
* **By ID:** exact `.equals()`
* **By Department:** case-insensitive `.contains()`

### Analytics Calculation Details

* User counts via UserAccountDirectory
* Course counts via CourseSchedule
* Enrollment via Seat occupancy
* Revenue via `courseSchedule.calculateTotalRevenues()` (credits × tuition × enrolled)

---

## Security Features

Authentication, role-based authorization, session logout, input validation, duplicate prevention, cascading operations, confirmation dialogs, exception handling, null safety.

---

## System Integration

**Depends On:** UniversitySystem; UserAccount(UserAccounts); AdminProfile; Person/PersonDirectory; StudentProfile/StudentDirectory; FacultyProfile/FacultyDirectory; CourseSchedule/CourseOffer/Seat; Transcript.
**Provides To:** Account creation; registration; CRUD; analytics; validation services.

---

## Known Limitations

Course-to-faculty assignment UI placeholder; Admin profile editing placeholder; no bulk ops/import/export; limited search fields; analytics lack historical trends; no email notifications; no admin audit log.

---

## Code Quality Standards

* Methods documented
* Consistent camelCase naming
* Null safety & validation
* UI vs business separation
* Single responsibility
* No hard-coded secrets
* No debug prints in production
* Proper exception handling
* Clean, readable structure
* Meaningful names

---

## Contact

**Student Use Case:** Tasmiya Parveen — NUID: 002509350
**Faculty Use Case:** Anusha Kashipura Putugnal — NUID: 003154069
**Admin Use Case:** Avadhanam Vinaya Rekha — NUID: 002341102

---

**Last Updated:** October 26, 2025
**Version:** 1.0
**Status:** Complete and Ready for Submission
