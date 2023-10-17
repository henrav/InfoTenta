package com.example.tentafragor;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TentaController extends Application implements Initializable {
    @FXML
    public Button KnappSvar1;
    @FXML
    public Button KnappSvar4;
    @FXML
    public Button KnappSvar3;
    @FXML
    public Button KnappSvar2;
    @FXML
    public TextArea SvarAlternativText3;
    @FXML
    public TextArea SvarAlternativText2;
    @FXML
    public TextArea SvarAlternativText1;
    @FXML
    public TextArea SvarAlternativText4;
    @FXML
    public TextArea FrågaText;
    @FXML
    public TextField FrågaNR;
    @FXML
    public Button Tillbaka;
    @FXML
    public Button NästaFråga;

    private Frågor frågor;

    private Frågor currentFråga;

    private int currentFrågaIndex;

    private Frågor[] frågorLista = new Frågor[150];


    public void setFrågaText(String text) {
        FrågaText.setText(text);
    }

    public void setSvarAlternativText1(String text) {
        SvarAlternativText1.setText(text);
    }

    public void setSvarAlternativText2(String text) {
        SvarAlternativText2.setText(text);
    }

    public void setSvarAlternativText3(String text) {
        SvarAlternativText3.setText(text);
    }

    public void setSvarAlternativText4(String text) {
        SvarAlternativText4.setText(text);
    }

    public void setFrågaNR(String text) {
        FrågaNR.setText(text);
    }

    public void setKnappSvar1(String text) {
        KnappSvar1.setText(text);
    }

    public void setKnappSvar2(String text) {
        KnappSvar2.setText(text);
    }

    public void setKnappSvar3(String text) {
        KnappSvar3.setText(text);
    }

    public void setKnappSvar4(String text) {
        KnappSvar4.setText(text);
    }

    public void setNästaFråga(String text) {
        NästaFråga.setText(text);
    }

    public void setTillbaka(String text) {
        Tillbaka.setText(text);
    }
    public void setKnappar(){
        KnappSvar1.setOnAction(actionEvent -> {
            if (currentFråga.getCorrectAnswer() == 1) {
                System.out.println("Rätt svar");
                nästaFråga();
            } else {
                System.out.println("Fel svar");
                showWrongAnswer();
            }
        });
        KnappSvar2.setOnAction(actionEvent -> {
            if (currentFråga.getCorrectAnswer() == 2) {
                System.out.println("Rätt svar");
                nästaFråga();
            } else {
                System.out.println("Fel svar");
                showWrongAnswer();
            }
        });
        KnappSvar3.setOnAction(actionEvent -> {
            if (currentFråga.getCorrectAnswer() == 3) {
                System.out.println("Rätt svar");
                nästaFråga();
            } else {
                System.out.println("Fel svar");
                showWrongAnswer();
            }
        });
        KnappSvar4.setOnAction(actionEvent -> {
            if (currentFråga.getCorrectAnswer() == 4) {
                System.out.println("Rätt svar");
                nästaFråga();
            } else {
                System.out.println("Fel svar");
                showWrongAnswer();
            }
        });
    }
    public void tillBaka(){
        if (currentFrågaIndex > 0) {
            currentFrågaIndex--;
            setText();
        }
    }
    public void setNästaFråga(){
        if (currentFrågaIndex < frågorLista.length - 1) {
            currentFrågaIndex++;
            setText();
        }
    }
    public void showWrongAnswer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fel svar");
        alert.setHeaderText("Du svarade fel");
        alert.setContentText("rätt svar är: " + currentFråga.getCorrectAnswer() + ": " + currentFråga.getExplanation());
        alert.setOnCloseRequest(event -> {
            nästaFråga();
        });
        alert.showAndWait();
    }
    public void seeAnswer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Förklaring");
        alert.setHeaderText("Rätta Svaret är: " + convertnrIntoABC(currentFråga));
        alert.setContentText(currentFråga.getExplanation());
        alert.showAndWait();
    }
    public String convertnrIntoABC(Frågor fråga){
        int a = currentFråga.getCorrectAnswer();
        if (a == 1){
            setKnappSvar1("A");
            return "A";
        }
        if (a == 2){
            setKnappSvar2("B");
            return "B";
        }
        if (a == 3){
            setKnappSvar3("C");
            return "C";
        }
        if (a == 4){
            setKnappSvar4("D");
            return "D";
        }
        return null;
    }


    public void nästaFråga(){
        if (currentFrågaIndex < frågorLista.length - 1) {
            currentFrågaIndex++;
            setText();
        }
    }


    public void setText(){
        currentFråga = frågorLista[currentFrågaIndex];
        setFrågaText(currentFråga.getQuestionText());
        setSvarAlternativText1(currentFråga.getAlternativText()[0]);
        setSvarAlternativText2(currentFråga.getAlternativText()[1]);
        setSvarAlternativText3(currentFråga.getAlternativText()[2]);
        setSvarAlternativText4(currentFråga.getAlternativText()[3]);
        setFrågaNR("Fråga " + (currentFrågaIndex + 1) + " av " + frågorLista.length);
        setKnappSvar1("A");
        setKnappSvar2("B");
        setKnappSvar3("C");
        setKnappSvar4("D");

    }


    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        frågorLista[0] = new Frågor("1. What is the definition of the principle of least privilege?",
                new String[]{
                        "A. Allowing all users full control over a network to keep administrative responsibilities to a minimum",
                        "B. Keeping the number of system users with access to a minimum",
                        "C. Granting users only the minimum privileges needed to accomplish assigned work tasks",
                        "D. Designing applications that do not have high levels of privilege"},
                3,
                "The definition of the principle of least privilege is granting users only the minimum privileges needed to accomplish assigned work tasks.");
        frågorLista[1] = new Frågor("2. What is the process of assigning groups of tasks to different users to prevent collusion and avoid conflicts of interest?",
                new String[]{
                        "A. Principle of least privilege",
                        "B. Separation of duties",
                        "C. Mandatory access control",
                        "D. Integrity assurance"},
                2,
                "Separation of duties is the process of assigning groups of tasks to different users to prevent collusion and to avoid conflicts of interest. The principle of least privilege is assigning users the minimal amount of access required to accomplish their work tasks.");
        frågorLista[2] = new Frågor("3. To prevent any one person from having too much control or power, or performing fraudulent acts, which of the following solutions should not be implemented?",
                new String[]{
                        "A. M of N control",
                        "B. Job rotation",
                        "C. Multiple key pairs",
                        "D. Separation of duties"},
                2,
                "Job rotation isn't appropriate because one person is still in charge of a particular position. M of N control, multiple key pairs, and separation of duties should be used to prevent a single person from compromising an entire system.");

        frågorLista[3] = new Frågor("4. What is the primary goal of risk management?",
                new String[]{
                        "A. Reduce risk to an acceptable level",
                        "B. Remove all risks from an environment",
                        "C. Minimize security cost expenditures",
                        "D. Assign responsibilities to job roles"},
                1,
                "The correct answer is to reduce or mitigate risk to an acceptable level. It's virtually impossible to remove all risks from an environment. It may be a goal of upper management in general to minimize security cost. Assigning responsibilities to job roles might be accomplished by the department heads.");

        frågorLista[4] = new Frågor("5. Which of the following best describes the use of a PIN number?",
                new String[]{
                        "A. Authentication",
                        "B. Authorization",
                        "C. Auditing",
                        "D. Access control"},
                1,
                "A PIN provides authentication. It is something you know.");

        frågorLista[5] = new Frågor("6. Nonrepudiation ensures which of the following?",
                new String[]{
                        "A. That strong passwords are always used",
                        "B. The accounting of the user actions",
                        "C. That the sender cannot deny their actions",
                        "D. The confidentiality of the database"},
                3,
                "When nonrepudiation is used as a security technique, a sender cannot deny sending a message.");
        frågorLista[6] = new Frågor("7. Which item is not part of the primary security categories?",
                new String[]{
                        "A. Prevention",
                        "B. Encryption",
                        "C. Detection",
                        "D. Recovery"},
                2,
                "Although encryption is a security technique, it falls under the prevention security category.");

        frågorLista[7] = new Frågor("8. Which of the following is a nontechnical means of enforcing security?",
                new String[]{
                        "A. Development of a disaster response plan",
                        "B. Separation of duties",
                        "C. User training",
                        "D. Safe testing"},
                3,
                "User training is the best way to use nontechnical means to enforce security. The more the users know, the more secure the system will be.");

        frågorLista[8] = new Frågor("9. Which option is not part of the prevention primary security category?",
                new String[]{
                        "A. Placing a padlock on a fence",
                        "B. Using guard dogs instead of security guards",
                        "C. Using virus protection software on all users’ machines",
                        "D. Using an alternate site after a disaster"},
                3,
                "Use of an alternate site after a disaster falls under the recovery primary security category.");

        frågorLista[9] = new Frågor("10. What is the most important step the IT department should take when an employee is fired?",
                new String[]{
                        "A. Search their desk for USB drives",
                        "B. Erase all data on their laptop",
                        "C. Review the rights and privileges assigned to the user",
                        "D. Deactivate the user’s account to prohibit access"},
                4,
                "Upon termination of any user, network access should immediately be prohibited by deactivating the user account.");

        frågorLista[10] = new Frågor("11. What is the foundational premise of risk management?",
                new String[]{
                        "A. There is always some level of risk.",
                        "B. Computers can be completely secured.",
                        "C. As security increases, costs decrease.",
                        "D. Security and performance are cooperative measurements."},
                1,
                "No matter how hard you try, there is always some level of risk on everything that you attempt.");

        frågorLista[11] = new Frågor("12. What are weaknesses within a network?",
                new String[]{
                        "A. Vulnerabilities",
                        "B. Mitigation",
                        "C. Risks",
                        "D. Controls"},
                1,
                "Vulnerabilities are weaknesses within a network. Mitigation reduces vulnerabilities and therefore risks. Risk is the probability that a threat will exploit vulnerability. Controls are tools and techniques for mitigating vulnerabilities.");
        frågorLista[12] = new Frågor("13. Which of the following options is a part of the CIA triad?",
                new String[]{
                        "A. Admission",
                        "B. Availability",
                        "C. Auditing",
                        "D. Administration"},
                2, // Correct answer is B (Availability)
                "Availability is the correct answer. Admission, auditing, and administration are all distractors.");

        frågorLista[13] = new Frågor("14. Which of the following is not a security category?",
                new String[]{
                        "A. Prevention",
                        "B. Remuneration",
                        "C. Detection",
                        "D. Recovery"},
                2, // Correct answer is B (Remuneration)
                "Remuneration is not one of the security categories.");

        frågorLista[14] = new Frågor("15. Which of the following types of controls restricts access based upon time?",
                new String[]{
                        "A. Temporal time restriction",
                        "B. Date restriction",
                        "C. Time of day restriction",
                        "D. Authorized access hours"},
                3, // Correct answer is C (Time of day restriction)
                "The correct answer is C, time of day restriction. The other answers are similar but not correct.");

        frågorLista[15] = new Frågor("16. Which of the following provides a catchall and prevents an action from being taken after everything else has allowed through on a network?",
                new String[]{
                        "A. Explicit deny",
                        "B. Deny any",
                        "C. Implicit deny",
                        "D. Global deny"},
                3, // Correct answer is C (Implicit deny)
                "Implicit deny is built into most routers and is the catchall that prohibits the passage of anything that has not been ethically or explicitly authorized. Explicit deny may be any one of dozens of router rules that the administrator creates to allow specific traffic. Deny any might be part of an explicit rule. Global deny is a distractor.");

        frågorLista[16] = new Frågor("17. Which of the following is a security program used in many banks to verify the ethics and job performance of a bank manager?",
                new String[]{
                        "A. Ethical investigation",
                        "B. Mandatory vacation",
                        "C. Mandatory cruise",
                        "D. M of N"},
                2, // Correct answer is B (Mandatory vacation)
                "The correct answer is mandatory vacation, which in many security policies is stated as a mandatory one-week vacation once a year during which an investigation into ethics and job performance might occur. Ethical investigation is not the answer. Although we might like our company to send us on a mandatory cruise, that is not the correct answer. M of N is a scheme requiring M number of people to agree to take action out of a possible universe of N number of people.");

        frågorLista[17] = new Frågor("18. When it comes to network security, the acronym AAA stands for which of the following?",
                new String[]{
                        "A. Authorization, authentication, and accounting",
                        "B. Admission, authorization, and accounting",
                        "C. Authentication, authorization, and accounting",
                        "D. Administration, authorization, and auditing"},
                3, // Correct answer is C (Authentication, authorization, and accounting)
                "The correct answer is authentication, authorization, and accounting. It might be easy to picture this as a chain of access control steps. The first step is identification and then the authentication. It's important to get the words in the correct order. Option D is incorrect because the words administration and auditing are not part of the term. Option B is wrong because admission is not part of the term. Option A includes the correct words, but they are in the wrong order. Read the questions and options carefully.");
        frågorLista[18] = new Frågor("19. What is a restriction placed on users that denies them access to resources on the weekends?",
                new String[]{
                        "A. Temporal differential",
                        "B. Time of week restriction",
                        "C. Time of day restriction",
                        "D. Time-based accounting"},
                3, // Correct answer is C (Time of day restriction)
                "The correct answer is time of day restriction. Time of week and time-based restrictions are similar, but they are distractors. Option A might be something out of a sci-fi movie.");

        frågorLista[19] = new Frågor("20. During an access system audit, a number of active accounts were discovered from employees who had left the company over the past two years. What are these accounts called?",
                new String[]{
                        "A. Long-term accounts",
                        "B. Orphaned accounts",
                        "C. Pseudo-active accounts",
                        "D. Ghost accounts"},
                2, // Correct answer is B (Orphaned accounts)
                "Accounts that no longer belong to any active employees were called orphan accounts. Answer A, long-term accounts, and answer C, pseudo-active accounts, are obvious distractors. Answer D sounds plausible but is not the correct answer.");
        frågorLista[20] = new Frågor("21. When a user is asked for a password by a system, what process is the system performing?",
                new String[]{
                        "A. Evaluation",
                        "B. Identification",
                        "C. Authentication",
                        "D. Authorization"},
                3, // Correct answer is C (Authentication)
                "C. The system is performing authentication. Option A is a distractor. Option B is incorrect because, in this case, the password is not used as identification. Option D is incorrect because the password does not provide authorization to the user.");

        frågorLista[21] = new Frågor("22. Authorization for multiple applications using one set of credentials is best described by which of the following?",
                new String[]{
                        "A. Authorization",
                        "B. Single Sign-on",
                        "C. Multi-factor",
                        "D. Enrollment"},
                2, // Correct answer is B (Single Sign-on)
                "B. The other answers are distractors.");

        frågorLista[22] = new Frågor("23. If information being protected is critical, which is the best course of action?",
                new String[]{
                        "A. The encryption password should be changed more frequently",
                        "B. The data should be used less frequently",
                        "C. The data should be hidden from other processes",
                        "D. Users should be provided public encryption keys"},
                1, // Correct answer is A (The encryption password should be changed more frequently)
                "A. Options B, C, and D are distractors.");

        frågorLista[23] = new Frågor("24. What are the three categories of controls?",
                new String[]{
                        "A. Physical, detective, and logical (technical)",
                        "B. Administrative, physical, and preventative",
                        "C. Administrative, logical (technical), and physical",
                        "D. Physical, logical (technical), and administrative"},
                4, // Correct answer is D (Physical, logical (technical), and administrative)
                "D. Options A, B, and C are distractors.");

        frågorLista[24] = new Frågor("25. Which of the following best describes the use of the password generated by a synchronous token device?",
                new String[]{
                        "A. The password must be used within a variable time interval",
                        "B. The password must be used within a fixed time interval",
                        "C. The password is not dependent upon time",
                        "D. The password is of variable length"},
                2, // Correct answer is B (The password must be used within a fixed time interval)
                "B. Options A, C, and D are distractors.");

        frågorLista[25] = new Frågor("26. Access control is best described as which of the following?",
                new String[]{
                        "A. Reduction a social networking",
                        "B. The elimination of risk when allowing users on a network",
                        "C. The use of identification and authorization techniques",
                        "D. The use of federated identities"},
                3, // Correct answer is C (The use of identification and authorization techniques)
                "C. The use of identification and authorization techniques best describes access control. Options A, B, and D are distractors.");
        frågorLista[26] = new Frågor("27. What is the type of access control in the default access control method found in Microsoft Windows which allows users to share files?",
                new String[]{
                        "A. Mandatory access control",
                        "B. Rule-based access control",
                        "C. Sensitivity-based access control",
                        "D. Discretionary access control"},
                4, // Correct answer is D (Discretionary access control)
                "D. Options A, B, and C are access controls, but they do not allow users to share files at their discretion.");

        frågorLista[27] = new Frågor("28. Which of the following types of access control is preferred for its ease of administration when there are a large number of personnel with the same job in an organization?",
                new String[]{
                        "A. Mandatory Access Control",
                        "B. Role-based Access Control",
                        "C. Rule-Based Access Control",
                        "D. Label-based Access Control"},
                2, // Correct answer is B (Role-based Access Control)
                "B. Role-based access control (RBAC) permits authorization to be assigned according to an individual’s role or title in the organization. Options A, C, and D are distractors.");

        frågorLista[28] = new Frågor("29. Which of the following best describes the time that it takes to register with a biometric system, by providing samples of a personal characteristic?",
                new String[]{
                        "A. Setup time",
                        "B. Login time",
                        "C. Enrollment time",
                        "D. Throughput time"},
                3, // Correct answer is C (Enrollment time)
                "C. Options A, B, and D are distractors.");

        frågorLista[29] = new Frågor("30. Which technique best describes a one-to-one search to verify an individual’s claim of identity?",
                new String[]{
                        "A. Authentication",
                        "B. Accounting review",
                        "C. Authorization",
                        "D. Availability"},
                1, // Correct answer is A (Authentication)
                "A. Authentication is the method of verifying an individual’s claim of identity. Options B, C, and D are distractors.");

        frågorLista[30] = new Frågor("31. Which of the following is a goal of integrity?",
                new String[]{
                        "A. All systems and data should be available",
                        "B. Any changes to applications for equipment must be approved",
                        "C. All data should be encrypted in transit",
                        "D. Data should not change between sender and receiver"},
                4, // Correct answer is D (Data should not change between sender and receiver)
                "D. Options A, B, and C are distractors.");

        frågorLista[31] = new Frågor("32. View-based access control is best described as which of the following?",
                new String[]{
                        "A. The concept of hiding data from view while in storage",
                        "B. Limiting the data the user may observe on a computer screen produced from a database",
                        "C. Allowing a user to only view unencrypted data",
                        "D. A rule-based control of a database"},
                2, // Correct answer is B (Limiting the data the user may observe on a computer screen produced from a database)
                "B. Options A, C, and D are distractors.");

        frågorLista[32] = new Frågor("33. Which of the following best describes privileged users?",
                new String[]{
                        "A. They are anonymous users",
                        "B. They are super-users or administrators",
                        "C. They all must work in the IT department",
                        "D. By default have access to everything on the network"},
                2, // Correct answer is B (They are super-users or administrators)
                "B. Privileged users are also known as super-users or administrators. Options A, C, and D are distractors.");

        frågorLista[33] = new Frågor("34. Which of the following is true about biometric scan technology?",
                new String[]{
                        "A. The full palm print is stored in memory.",
                        "B. A number of points extracted from the item scanned are stored.",
                        "C. Scan data is always stored in the cloud for rapid retrieval.",
                        "D. It is always used with a second method of authentication."},
                2, // Correct answer is B (A number of points extracted from the item scanned are stored)
                "B. Options A, C, and D are distractors.");

        frågorLista[34] = new Frågor("35. Mandatory access control uses which of the following to authorize access to information?",
                new String[]{
                        "A. Identity and voice prints",
                        "B. Roles and rules",
                        "C. Subject and object labels",
                        "D. Identity and several factor authentication"},
                3, // Correct answer is C (Subject and object labels)
                "C. Options A, B, and D are distractors.");

        frågorLista[35] = new Frågor("36. Which of the following is an example of two-factor authentication?",
                new String[]{
                        "A. A password and user name",
                        "B. An user ID and an account number",
                        "C. A PIN and an RFID card",
                        "D. A fingerprint and signature"},
                3, // Correct answer is C (A PIN and an RFID card)
                "C. Options A, B, and D refer to the same factor categories thus providing only one factor authentication.");

        frågorLista[36] = new Frågor("37. Crossover error rate (CER) refers to which of the following graphical intersections?",
                new String[]{
                        "A. Database usage rate",
                        "B. Employee opt-out rate",
                        "C. Symmetric and asymmetric rate",
                        "D. False rejection rate and false acceptance rate"},
                4, // Correct answer is D (False rejection rate and false acceptance rate)
                "D. The CER is where the false rejection rate (FRR) and false acceptance rate (FAR) crossover.");

        frågorLista[37] = new Frågor("38. The sensitivity adjustment on a biometric authentication device affects which of the following?",
                new String[]{
                        "A. Cost of the device",
                        "B. False acceptance rate and false rejection rate",
                        "C. Limitation of the enrollment database",
                        "D. Requirement for continuous adjustment"},
                2, // Correct answer is B (False acceptance rate and false rejection rate)
                "B. Options A, B, and D are distractors.");

        frågorLista[38] = new Frågor("39. Which of the following best describes session level controls?",
                new String[]{
                        "A. Role-based logon controls",
                        "B. Identification and integrity control",
                        "C. Mandatory access controls",
                        "D. Log-off due to user inactivity"},
                4, // Correct answer is D (Log-off due to user inactivity)
                "D. Options A, B, and C are distractors.");

        frågorLista[39] = new Frågor("40. Which of the following best describes a password that changes on each logon?",
                new String[]{
                        "A. Session level password",
                        "B. Self-assigned password",
                        "C. Dynamic password",
                        "D. Variable password"},
                3, // Correct answer is C (Dynamic password)
                "C. Option A, B, and D are distractors.");
        frågorLista[40] = new Frågor("41. Proper security administration policies, controls, and procedures enforce which of the following?",
                new String[]{
                        "A. The elimination of risk",
                        "B. The total reduction of malware",
                        "C. The AIC objectives",
                        "D. Separation of duties"},
                3, // Correct answer is C (The AIC objectives)
                "C. Proper security administration policies, controls, and procedures enforce the AIC triad objectives, which are availability, integrity, and confidentiality.");

        frågorLista[41] = new Frågor("42. Which of the following best describes a threat exploiting a vulnerability?",
                new String[]{
                        "A. DDOS",
                        "B. Risk",
                        "C. A hurricane",
                        "D. Power supply brownout"},
                2, // Correct answer is B (Risk)
                "B. Risk is the probability for likelihood that a threat will exploit the vulnerability. Options A, C, and D are distractors.");

        frågorLista[42] = new Frågor("43. Which of the following best describes a security policy?",
                new String[]{
                        "A. It describes the requirement for shareholder satisfaction",
                        "B. Lists potential risk targets within the organization",
                        "C. Makes extensive use of baselines and guidelines",
                        "D. Completely aligns with the mission, objectives, culture, and nature of the business"},
                3, // Correct answer is D (Completely aligns with the mission, objectives, culture, and nature of the business)
                "D. A security policy must be in alignment with the mission, objectives, nature, and culture of a business. Organizational policies are not based on best practices.");

        frågorLista[43] = new Frågor("44. Which of the following best describes a federated relationship?",
                new String[]{
                        "A. Numerous franchises in a geographical area",
                        "B. The airline industry",
                        "C. HIPAA patient privacy requirements for healthcare providers",
                        "D. Third-party companies and their networks share customer data based upon a single sign-on to a primary organization"},
                4, // Correct answer is D (Third-party companies and their networks share customer data based upon a single sign-on to a primary organization)
                "D. The Federation consists of third-party companies that share data based upon a one-time authentication of an individual.");

        frågorLista[44] = new Frågor("45. Which of the following is an example of compensating control?",
                new String[]{
                        "A. A padlock on a gate",
                        "B. A chain on the hotel room door",
                        "C. A red bucket of sand with the word, “Fire”",
                        "D. An insurance policy"},
                2, // Correct answer is B (A chain on the hotel room door)
                "B. A compensating control is a secondary control placed into use if the first or primary control is disabled or no longer usable. In this case, a hotel room door has a lock; the chain is a secondary or compensating control.");

        frågorLista[45] = new Frågor("46. What must every policy possess in order to be successfully implemented?",
                new String[]{
                        "A. An enforcement provision",
                        "B. Scope and statements from stakeholders",
                        "C. Senior executive endorsement",
                        "D. Controls and procedures statement"},
                3, // Correct answer is C (Senior executive endorsement)
                "C. The policy will be doomed to failure if it does not have senior executive endorsement or a mandate from senior management. Options A, B, and D are distractors.");
        frågorLista[46] = new Frågor("47. What does an acceptable use policy (AUP) state?",
                new String[]{
                        "A. That the organization assets may not be used on weekends",
                        "B. That USB drives may not be used",
                        "C. The acceptable and unacceptable uses for organizational resources",
                        "D. That users may not visit shopping sites during work"},
                3, // Correct answer is C (The acceptable and unacceptable uses for organizational resources)
                "C. An acceptable use policy (AUP) sets forth the acceptable and unacceptable uses for organizational resources.");

        frågorLista[47] = new Frågor("48. An Acceptable Use Policy (AUP) is what type of control?",
                new String[]{
                        "A. Administrative",
                        "B. Corrective",
                        "C. Detective",
                        "D. Compensating"},
                1, // Correct answer is A (Administrative)
                "A. Acceptable behavior of individuals within any organization is put forth in the acceptable use policy. This includes the use of facilities and equipment as well as a large number of other behavioral considerations. The acceptable use policy is an administrative control.");

        frågorLista[48] = new Frågor("49. Which of the following should be included in every policy that states possible penalties or restrictions for individuals?",
                new String[]{
                        "A. A copyright notice",
                        "B. An enforcement statement",
                        "C. A statement from the author",
                        "D. A preamble of rights"},
                2, // Correct answer is B (An enforcement statement)
                "B. An enforcement statement informs individuals of the potential penalties, fines, sanctions, or repercussions, which may result from the failure to abide by the policy.");

        frågorLista[49] = new Frågor("50. Organization policies are generally created in response to the requirement to meet certain criteria. Which of the following best details these requirements?",
                new String[]{
                        "A. Baselines",
                        "B. Standards",
                        "C. Procedures",
                        "D. Policy Requirements Document (PRD)"},
                2, // Correct answer is B (Standards)
                "B. Standards are the part of a policy that lists the criteria that must be met by the organization.");

        frågorLista[50] = new Frågor("51. Which of the following is a typical method of communicating a policy or policy change?",
                new String[]{
                        "A. Intranet announcement",
                        "B. Handouts",
                        "C. Instagram announcement",
                        "D. Phone e-mailed blast"},
                1, // Correct answer is A (Intranet announcement)
                "A. The organization’s intranet is often the preferred method of communicating policy or policy changes. Social media and informal methods of communication such as including handouts and telephone calls should not be used to announce policy directives or policy changes.");

        frågorLista[51] = new Frågor("52. Which of the following is the third canon of the (ISC)2 Code of Ethics?",
                new String[]{
                        "A. Act honorably",
                        "B. Ensure the safety of society",
                        "C. Meet all CEU requirements for this certification",
                        "D. Provide competent and diligent service"},
                3, // Correct answer is D (Provide competent and diligent service)
                "D. Provide competent and diligent service to principles is the third canon of the (ISC)2 Code of Ethics.");

        frågorLista[52] = new Frågor("53. What is a service pack?",
                new String[]{
                        "A. A piece of software intended to update an application",
                        "B. A piece of software written by user group intended to fix a problem",
                        "C. A piece of software intended to inform users of a software vulnerability",
                        "D. An executable program that loads a number of fixes and system upgrades"},
                4, // Correct answer is D (An executable program that loads a number of fixes and system upgrades)
                "D. Service packs are issued by a manufacturer to correct many software or hardware deficiencies and to upgrade the product. They may combine a large number of patches.");

        frågorLista[53] = new Frågor("54. Which of the following best describes an environment to test a patch or a service pack?",
                new String[]{
                        "A. As they are received from the manufacturer",
                        "B. In a sandbox",
                        "C. In a production environment",
                        "D. In a simulator"},
                2, // Correct answer is B (In a sandbox)
                "B. A sandbox environment, which resembles a production environment, is a location that patches and service packs should be tested prior to distribution to a production network.");
        frågorLista[56] = new Frågor("57. What is a typical commercial or business information classification scheme?",
                new String[]{
                        "A. Unclassified, sensitive but unclassified, secret, and top secret",
                        "B. Unclassified, business casual, confidential",
                        "C. Public, company confidential, company secret",
                        "D. Public, sensitive but unclassified, confidential, secret, and top secret"},
                1, // Correct answer is A (Unclassified, sensitive but unclassified, secret, and top secret)
                "A. A typical commercial or business information classification scheme includes classifications such as unclassified, sensitive but unclassified, secret, and top secret.");

        frågorLista[57] = new Frågor("58. If subjects receive a clearance, what do objects receive?",
                new String[]{
                        "A. Data Tag",
                        "B. Mandatory Access Control label",
                        "C. Access point",
                        "D. Classification"},
                2, // Correct answer is B (Mandatory Access Control label)
                "B. In mandatory access control (MAC) systems, objects are labeled with Mandatory Access Control labels, and subjects are assigned clearances.");

        frågorLista[58] = new Frågor("59. Which of the following best describes an endpoint device?",
                new String[]{
                        "A. Router",
                        "B. Computer printer",
                        "C. Switch",
                        "D. HIDS"},
                2, // Correct answer is B (Computer printer)
                "B. Any device that terminates a network connection may be classified as an endpoint device. In this case, a computer printer is an endpoint device because nothing follows it on the network.");

        frågorLista[59] = new Frågor("60. Which of the following is part of the business continuity plan?",
                new String[]{
                        "A. The recovery downtime objective",
                        "B. The restoration of accounting data into databases",
                        "C. Recovery point objective",
                        "D. The maximum tolerable off-line time"},
                3, // Correct answer is C (Recovery point objective)
                "C. The recovery point objective (RPO) is part of a business continuity plan.");

        frågorLista[60] = new Frågor("61. Which of the following best describes a disaster recovery plan?",
                new String[]{
                        "A. Makes use of probability analysis",
                        "B. Uses the Business Information Plan to determine procedures",
                        "C. Documents procedures to restore equipment and facilities to the condition they were in prior to the disaster",
                        "D. Specifies time required to restore data with different backup schemes"},
                3, // Correct answer is C (Documents procedures to restore equipment and facilities to the condition they were in prior to the disaster)
                "C. A disaster recovery plan documents the procedures required to restore equipment and facilities back to the condition they were in prior to the disaster.");

        frågorLista[61] = new Frågor("62. Which of the following best describes maximum tolerable downtime?",
                new String[]{
                        "A. The amount of time a business process may be off-line before the viability of the organization is in severe jeopardy",
                        "B. The point at which data recovery should begin",
                        "C. The amount of time between RPO and RTO",
                        "D. The time required to restore data from a backup"},
                1, // Correct answer is A (The amount of time a business process may be off-line before the viability of the organization is in severe jeopardy)
                "A. Maximum tolerable downtime is the amount of time a business process may be off-line before the viability of the organization is in severe jeopardy.");
        frågorLista[62] = new Frågor("63. What is a primary goal of security in an organization?",
                new String[]{
                        "A. Eliminate risk",
                        "B. Mitigate the possibility of the use of malware",
                        "C. Enforce and maintain the AIC objectives",
                        "D. Maintain the organization's network operations"},
                3, // Correct answer is C (Enforce and maintain the AIC objectives)
                "C. Enforcing and maintaining the AIC (Availability, Integrity, and Confidentiality) objectives is a primary goal of security. Options A, B, and D are not primary goals.");

        frågorLista[63] = new Frågor("64. Which of the following provides the best description of risk reduction?",
                new String[]{
                        "A. Altering elements of the enterprise in response to a risk analysis",
                        "B. Mitigating risk to the enterprise at any cost",
                        "C. Allowing a third party to assume all risk for the enterprise",
                        "D. Paying all costs associated with risks with internal budgets"},
                1, // Correct answer is A (Altering elements of the enterprise in response to a risk analysis)
                "A. Risk reduction involves altering elements of the enterprise in response to a risk analysis to minimize the ability of a threat to exploit a vulnerability. Options B, C, and D do not accurately describe risk reduction.");

        frågorLista[64] = new Frågor("65. Which group represents the most likely source of an asset being lost through inappropriate computer use?",
                new String[]{
                        "A. Crackers",
                        "B. Employees",
                        "C. Hackers",
                        "D. Flood"},
                2, // Correct answer is B (Employees)
                "B. The most likely source of an asset being lost through inappropriate computer use is employees. Options A, C, and D refer to external threats.");

        frågorLista[65] = new Frågor("66. Which of the following statements is not accurate?",
                new String[]{
                        "A. Risk is identified and measured by performing a risk analysis.",
                        "B. Risk is controlled through the application of safeguards and countermeasures.",
                        "C. Risk is managed by periodically reviewing the risk and taking responsible actions based on the risk.",
                        "D. All risks can be totally eliminated through risk management."},
                3, // Correct answer is D (All risks can be totally eliminated through risk management)
                "D. Not all risks can be totally eliminated through risk management. Options A, B, and C are accurate statements about risk.");

        frågorLista[66] = new Frågor("67. Which option most accurately defines a threat?",
                new String[]{
                        "A. Any vulnerability in an information technology system",
                        "B. Protective controls",
                        "C. Multilayered controls",
                        "D. Possibility for a source to exploit a specific vulnerability"},
                4, // Correct answer is D (Possibility for a source to exploit a specific vulnerability)
                "D. A threat is defined as the possibility for a source to exploit a specific vulnerability. Options A, B, and C do not accurately define a threat.");

        frågorLista[67] = new Frågor("68. Which most accurately describes a safeguard?",
                new String[]{
                        "A. Potential for a source to exploit a categorized vulnerability",
                        "B. Controls put in place to provide some amount of protection for an asset",
                        "C. Weakness in internal controls that could be exploited by a threat or a threat agent",
                        "D. A control designed to warn of an attack"},
                2, // Correct answer is B (Controls put in place to provide some amount of protection for an asset)
                "B. A safeguard is a control put in place to provide some amount of protection for an asset. Options A, C, and D do not accurately describe a safeguard.");

        frågorLista[68] = new Frågor("69. Which of the following choices is the most accurate description of a countermeasure?",
                new String[]{
                        "A. Any event with the potential to harm an information system through unauthorized access",
                        "B. Controls put in place as a result of a risk analysis",
                        "C. The annualized rate of occurrence multiplied by the single lost exposure",
                        "D. The company resource that could be lost due to an accident"},
                2, // Correct answer is B (Controls put in place as a result of a risk analysis)
                "B. A countermeasure is a control put in place as a result of a risk analysis to mitigate risk. Options A, C, and D do not accurately define a countermeasure.");
        frågorLista[69] = new Frågor("70. Which most closely depicts the difference between qualitative and quantitative risk analysis?",
                new String[]{
                        "A. A quantitative risk analysis does not use the hard cost of losses; a qualitative risk analysis does.",
                        "B. A quantitative risk analysis makes use of real numbers.",
                        "C. A quantitative risk analysis results in subjective high, medium, or low results.",
                        "D. A quantitative risk analysis cannot be automated."},
                2, // Correct answer is B (A quantitative risk analysis makes use of real numbers)
                "B. Quantitative risk analysis involves using real numbers, whereas qualitative risk analysis does not rely on hard cost losses. Option A is incorrect as it misrepresents the difference. Option C is incorrect as it confuses qualitative analysis results with quantitative analysis. Option D is incorrect, as quantitative risk analysis can be automated.");

        frågorLista[70] = new Frågor("71. Which choice is not a description of a control?",
                new String[]{
                        "A. Detective controls uncover attacks and prompt the action of preventative or corrective controls.",
                        "B. Controls perform as the countermeasures for threats.",
                        "C. Controls reduce the effect of an attack.",
                        "D. Corrective controls always reduce the likelihood of a premeditated attack."},
                4, // Correct answer is D (Corrective controls always reduce the likelihood of a premeditated attack)
                "D. Corrective controls are designed to stop an existing attack or correct an issue but do not necessarily reduce the likelihood of a premeditated attack. Options A, B, and C accurately describe controls.");

        frågorLista[71] = new Frågor("72. What is the main advantage of using a quantitative impact analysis over a qualitative impact analysis?",
                new String[]{
                        "A. A qualitative impact analysis identifies areas that require immediate improvement",
                        "B. A qualitative impact analysis provides a rationale for determining the effect of security controls",
                        "C. A quantitative impact analysis makes a cost-benefit analysis simple",
                        "D. A quantitative impact analysis provides specific measurements of attack impacts"},
                3, // Correct answer is C (A quantitative impact analysis makes a cost-benefit analysis simple)
                "C. The main advantage of using a quantitative impact analysis over a qualitative impact analysis is that it makes a cost-benefit analysis simple. Options A, B, and D do not represent the main advantage.");

        frågorLista[72] = new Frågor("73. Which choice is not a common means of gathering information when performing a risk analysis?",
                new String[]{
                        "A. Distributing a multi-page form",
                        "B. Utilizing automated risk polling tools",
                        "C. Interviewing fired employees",
                        "D. Reviewing existing policy documents"},
                3, // Correct answer is C (Interviewing fired employees)
                "C. Interviewing fired employees is not a common means of gathering information when performing a risk analysis. Options A, B, and D are common techniques.");

        frågorLista[73] = new Frågor("74. Which choice is usually the most-used criteria to determine the classification of an information object?",
                new String[]{
                        "A. Useful life",
                        "B. Value",
                        "C. Age",
                        "D. Most frequently used"},
                2, // Correct answer is B (Value)
                "B. The most commonly used criterion to determine the classification of an information object is its value. Options A, C, and D are less commonly used criteria.");

        frågorLista[74] = new Frågor("75. What is the prime objective of risk management?",
                new String[]{
                        "A. Reduce risk to a level tolerable by the organization",
                        "B. Reduce all risks without respect to cost to the organization",
                        "C. Transfer all risks to external third parties",
                        "D. Prosecute any employees that are violating published security policies"},
                1, // Correct answer is A (Reduce risk to a level tolerable by the organization)
                "A. The prime objective of risk management is to reduce risk to a level tolerable by the organization. Option B is incorrect as it is not practical to reduce all risks without regard to cost. Option C is incorrect as it represents one treatment option, not the prime objective. Option D is incorrect as it does not represent the prime objective of risk management.");

        frågorLista[75] = new Frågor("76. A business asset is best described by which of the following?",
                new String[]{
                        "A. An asset loss that could cause a financial or operational impact to the organization",
                        "B. Controls put in place that reduce the effects of threats",
                        "C. Competitive advantage, capability, credibility, or goodwill",
                        "D. Personnel, compensation, and retirement programs"},
                3, // Correct answer is C (Competitive advantage, capability, credibility, or goodwill)
                "C. A business asset is best described as something that contributes to competitive advantage, capability, credibility, or goodwill. Options A, B, and D do not accurately describe a business asset.");
        frågorLista[76] = new Frågor("77. Which is not accurate regarding the process of a risk assessment?",
                new String[]{
                        "A. The possibility that a threat exists must be determined as an element of the risk assessment.",
                        "B. The level of impact of a threat must be determined as an element of risk assessment.",
                        "C. A risk assessment is the last result of the risk management process.",
                        "D. Risk assessment is the first step in the risk management process."},
                3, // Correct answer is C (A risk assessment is the last result of the risk management process)
                "C. A risk assessment is not the last result of the risk management process but rather the first step in the process. Options A, B, and D are accurate regarding the process of a risk assessment.");

        frågorLista[77] = new Frågor("78. Which statement is not correct about safeguard selection in the risk analysis process?",
                new String[]{
                        "A. Total cost of ownership (TCO) needs to be included in determining the total cost of the safeguard.",
                        "B. It is most common to consider the cost effectiveness of the safeguard.",
                        "C. The most effective safeguard should always be implemented regardless of cost.",
                        "D. Several criteria should be considered when determining the total cost of the safeguard."},
                3, // Correct answer is C (The most effective safeguard should always be implemented regardless of cost)
                "C. The statement in Option C is not correct because, in practice, the most effective safeguard is not always implemented without consideration of cost. Options A, B, and D are correct regarding safeguard selection in the risk analysis process.");

        frågorLista[78] = new Frågor("79. Which option most accurately reflects the goals of risk mitigation?",
                new String[]{
                        "A. Determining the effects of a denial of service and preparing the company’s response",
                        "B. The removal of all exposure and threats to the organization",
                        "C. Defining the acceptable level of risk and assigning the responsibility of loss or disruption to a third-party, such as an insurance carrier",
                        "D. Defining the acceptable level of risk the organization can tolerate and reducing risk to that level"},
                4, // Correct answer is D (Defining the acceptable level of risk the organization can tolerate and reducing risk to that level)
                "D. The goal of risk mitigation is to define the acceptable level of risk that the organization can tolerate and take actions to reduce risk to that level. Option A refers to continuity management planning. Option B is generally impossible to achieve. Option C combines two different concepts, neither of which is a primary goal of risk mitigation.");

        frågorLista[79] = new Frågor("80. Of the following choices which is not a typical monitoring technique?",
                new String[]{
                        "A. Passive monitoring",
                        "B. Active monitoring",
                        "C. Subjective monitoring",
                        "D. Real-time monitoring"},
                3, // Correct answer is C (Subjective monitoring)
                "C. Subjective monitoring is not a typical monitoring technique. Options A, B, and D represent common monitoring techniques used within an organization.");

        frågorLista[80] = new Frågor("81. Which option is not a risk treatment technique?",
                new String[]{
                        "A. Risk acceptance",
                        "B. Ignoring risk",
                        "C. Risk transference",
                        "D. Risk reduction"},
                2, // Correct answer is B (Ignoring risk)
                "B. Ignoring risk is not a recognized risk treatment technique. If the risk is ignored, it is, by default, accepted. Options A, C, and D are typical risk treatment techniques.");

        frågorLista[81] = new Frågor("82. Which of the following is not a control category?",
                new String[]{
                        "A. Administrative",
                        "B. Physical",
                        "C. Preventative",
                        "D. Technical"},
                3, // Correct answer is C (Preventative)
                "C. While there are preventative controls, 'Preventative' is not one of the three major control categories. Options A, B, and D represent the three major control categories.");
        frågorLista[83] = new Frågor("84. Which is the most volatile memory?",
                new String[]{
                        "A. Hard disk",
                        "B. CPU cache",
                        "C. RAM",
                        "D. USB drive"},
                2, // Correct answer is B (CPU cache)
                "B. CPU cache is the most volatile memory as it is the closest memory to the CPU. Options A and D represent long-term storage and are not considered volatile memory. Option C, RAM, is volatile memory but not as volatile as CPU cache.");

        frågorLista[84] = new Frågor("85. Which option provides the best description of the first action to take during incident response?",
                new String[]{
                        "A. Determine the source and vector of the threat.",
                        "B. Follow the procedures in the incident response plan.",
                        "C. Disconnect the affected computers.",
                        "D. Alert the third-party incident response team."},
                2, // Correct answer is B (Follow the procedures in the incident response plan)
                "B. The best first action during incident response is to follow the procedures outlined in the incident response plan. Options A, C, and D may be steps within the incident response plan but are not the very first action.");

        frågorLista[85] = new Frågor("86. Which option most accurately describes continuity of operations after a disaster event?",
                new String[]{
                        "A. Controlling risk to the organization",
                        "B. Planned procedures that are performed when a security-related incident occurs",
                        "C. Planned activities that enable the organization's critical business functions to return to operations",
                        "D. Transferring risk to a third-party insurance carrier"},
                3, // Correct answer is C (Planned activities that enable the organization's critical business functions to return to operations)
                "C. Continuity of operations after a disaster event involves planned activities that enable the organization's critical business functions to return to normal operations. Option A is related to risk management, while Option B is part of incident response. Option D refers to risk transference, not continuity of operations.");

        frågorLista[86] = new Frågor("87. When considering a disaster, which of the following is not a commonly accepted definition?",
                new String[]{
                        "A. An occurrence that is outside the normal functional baselines",
                        "B. An occurrence or imminent threat to the enterprise of widespread or severe damage, injury, loss of life, or loss of property",
                        "C. An emergency that is beyond the normal response resources of the enterprise",
                        "D. A suddenly occurring event that has a long-term negative impact on major IT infrastructure"},
                1, // Correct answer is A (An occurrence that is outside the normal functional baselines)
                "A. Option A is not a commonly accepted definition of a disaster. Options B, C, and D are all commonly accepted definitions of disasters.");

        frågorLista[87] = new Frågor("88. Which of the following is not an accurate statement about an organization's incident response policy?",
                new String[]{
                        "A. It should require the ability to respond quickly and effectively to an incident.",
                        "B. It should require the prevention of future damage from an incident.",
                        "C. It should require the retaliation against repeat attackers.",
                        "D. It can require the repair of damage done from an incident."},
                3, // Correct answer is C (It should require the retaliation against repeat attackers)
                "C. An organization's incident response policy should not require retaliation against repeat attackers. Options A, B, and D are all accurate statements about an organization's incident response policy.");

        frågorLista[88] = new Frågor("89. Which option is not a responsibility of the person designated to manage the continuity planning process?",
                new String[]{
                        "A. Providing information and direction to senior management staff",
                        "B. Providing stress mitigation programs to employees after an asset loss event",
                        "C. Analyzing and identifying all critical business functions",
                        "D. Coordinating and planning integration among business units"},
                2, // Correct answer is B (Providing stress mitigation programs to employees after an asset loss event)
                "B. Providing stress mitigation programs to employees after an asset loss event is typically the responsibility of the human resources department, not the person managing the continuity planning process. Options A, C, and D are all responsibilities of the person designated to manage continuity planning.");

        frågorLista[89] = new Frågor("90. Which disaster recovery/emergency management plan testing type is considered the most cost-effective and efficient way to identify areas of overlap in the plan before conducting a more demanding training exercise?",
                new String[]{
                        "A. Full failover test",
                        "B. Structured walk-through test",
                        "C. Tabletop exercise",
                        "D. Bullet point test"},
                3, // Correct answer is C (Tabletop exercise)
                "C. A tabletop exercise is considered the most cost-effective and efficient way to identify areas of overlap in a disaster recovery/emergency management plan before conducting a more demanding training exercise. Options A, B, and D are other types of testing/exercise, but they are not as cost-effective for the purpose mentioned.");
        frågorLista[90] = new Frågor("91. Which of the following options best describes a cold site?",
                new String[]{
                        "A. An alternate processing facility with established electrical wiring and HVAC but no data processing hardware",
                        "B. An alternate processing facility with most data processing hardware and software installed, which can be operational within a matter of hours to a few days",
                        "C. An alternate processing facility that has all hardware and software installed and is mirrored with the original site and can be operational within a very short period of time",
                        "D. A mobile trailer with portable generators and air-conditioning"},
                0, // Correct answer is A (An alternate processing facility with established electrical wiring and HVAC but no data processing hardware)
                "A. A cold site is an alternate processing facility with established electrical wiring and HVAC but no data processing hardware. Options B, C, and D describe other types of alternate sites with varying degrees of readiness.");

        frågorLista[91] = new Frågor("92. Which of the following statements is an incorrect description of a control?",
                new String[]{
                        "A. Detective controls monitor for attacks and instigate preventative or corrective controls.",
                        "B. Controls reduce the possibility that vulnerabilities will be attacked.",
                        "C. The effect of an attack is reduced through the use of controls",
                        "D. Restorative controls reduce the likelihood of a deliberate attack."},
                3, // Correct answer is D (Restorative controls reduce the likelihood of a deliberate attack)
                "D. Restorative controls do not typically reduce the likelihood of a deliberate attack. Options A, B, and C are accurate descriptions of controls.");

        frågorLista[92] = new Frågor("93. Which of the five disaster recovery testing types creates the most risk for the enterprise?",
                new String[]{
                        "A. Simulation",
                        "B. Parallel",
                        "C. Structured walk-through",
                        "D. Full interruption"},
                3, // Correct answer is D (Full interruption)
                "D. A full interruption test creates the most risk for the enterprise as it involves a complete shutdown of the primary system. Options A, B, and C are other types of disaster recovery testing but involve varying degrees of risk.");

        frågorLista[93] = new Frågor("94. Which of the following options would require the longest setup time?",
                new String[]{
                        "A. Mobile or portable alternate IT computing service",
                        "B. Hot site",
                        "C. Cold site",
                        "D. Warm site"},
                1, // Correct answer is A (Mobile or portable alternate IT computing service)
                "A. A mobile or portable alternate IT computing service would typically require the longest setup time, as it involves mobilizing resources. Options B, C, and D represent different types of alternate recovery sites with varying levels of readiness.");

        frågorLista[94] = new Frågor("95. A backup site is best described by which of the following options?",
                new String[]{
                        "A. A computer facility with electrical power and HVAC but with no applications or installed data on the workstations or servers prior to the event",
                        "B. A computer facility with available electrical power and HVAC and some print/file servers. No equipment has been installed at the site.",
                        "C. An alternate computing location with little power and air-conditioning but no telecommunications capability",
                        "D. A computer facility with power and HVAC and all servers and communications. All applications are ready to be installed and configured, and recent data is available to be restored to the site."},
                3, // Correct answer is D (A computer facility with power and HVAC and all servers and communications. All applications are ready to be installed and configured, and recent data is available to be restored to the site.)
                "D. A backup site is best described as a computer facility with power and HVAC, all servers and communications, and all applications ready to be installed and configured. Recent data is also available to be restored to the site. Options A, B, and C describe other types of alternate recovery sites.");

        frågorLista[95] = new Frågor("96. What is the prime objective of the continuity plan?",
                new String[]{
                        "A. To make sure everyone understands their responsibility and the procedures they must follow",
                        "B. To inform everyone that a disaster or incident event has occurred",
                        "C. To maintain business operations, as best as possible, until all operations can be restored",
                        "D. To rebuild the facility after a disaster occurrence"},
                2, // Correct answer is C (To maintain business operations, as best as possible, until all operations can be restored)
                "C. The prime objective of the continuity plan is to maintain business operations as best as possible until all operations can be restored. Options A, B, and D are not the primary objectives of the continuity plan.");
        frågorLista[96] = new Frågor("97. Which option best describes an incremental backup?",
                new String[]{
                        "A. Daily backups are appended to previous backups.",
                        "B. Daily backups are maintained in separate files.",
                        "C. Daily backups are appended to the full backup.",
                        "D. Daily backups are mirrored to the cloud"},
                2, // Correct answer is B (Daily backups are maintained in separate files)
                "B. An incremental backup stores only the current day’s data in a file. Options A, C, and D are backup techniques but are not defined as incremental backups.");

        frågorLista[97] = new Frågor("98. Which option is most accurate regarding a recovery point objective?",
                new String[]{
                        "A. The time after which the viability of the enterprise is in question",
                        "B. The point at which the most accurate data is available for restoration",
                        "C. The point at which the least accurate data is available for restoration",
                        "D. The target time full operations should be restored after disaster"},
                2, // Correct answer is B (The point at which the most accurate data is available for restoration)
                "B. The RPO is the location of the most accurate backup data prior to a disaster event. Options A, C, and D are distractors.");

        frågorLista[98] = new Frågor("99. Which team is made up of members from across the enterprise?",
                new String[]{
                        "A. Dedicated full-time incident response team",
                        "B. Functional incident response team",
                        "C. Third-party incident response team",
                        "D. Expert incident response team"},
                2, // Correct answer is B (Functional incident response team)
                "B. The functional incident response team should consist of a broad range of talents from across the organization. Options A, C, and D, although types of incident response teams, usually featured experts, dedicated personnel, or third-party contractors.");

        frågorLista[99] = new Frågor("100. Evidence should be tracked utilizing which of the following methods?",
                new String[]{
                        "A. Record of evidence",
                        "B. Chain of custody",
                        "C. Evidence recovery tag",
                        "D. Investigators evidence notebook"},
                2, // Correct answer is B (Chain of custody)
                "B. The chain of custody involves logging the location and handling of evidence. Options A, C, and D are items that may be used during investigation.");

        frågorLista[100] = new Frågor("101. Prior to analysis, data should be copied from a hard disk utilizing which of the following?",
                new String[]{
                        "A. Write protect tool",
                        "B. Block data copy software",
                        "C. Bit data copy software",
                        "D. Memory dump tool"},
                3, // Correct answer is C (Bit data copy software)
                "C. Data should be copied from a hard disk using bit-by-bit copy software. Options A, B, and D are distractors.");

        frågorLista[101] = new Frågor("102. Upon arriving at an incident scene, the incident response team should do which of the following?",
                new String[]{
                        "A. Turn off the affected machine to stop the attack.",
                        "B. Follow the procedures specified in the incident response plan.",
                        "C. Quickly unplug the Ethernet plug.",
                        "D. Take photographs of the crime scene before it can be disturbed."},
                2, // Correct answer is B (Follow the procedures specified in the incident response plan)
                "B. First responders should always follow the procedures as specified in the incident response plan. Options A, C, and D may be procedures included in an incident response plan but are incorrect because they might not be the correct procedures in this organization’s incident response plan.");

        frågorLista[102] = new Frågor("103. A clipping level does which of the following?",
                new String[]{
                        "A. Reduces noise signals on the IT infrastructure",
                        "B. Removes unwanted packets",
                        "C. Defines a threshold of activity that, after crossed, sets off an operator alarm or alert",
                        "D. Provides real-time monitoring"},
                3, // Correct answer is C (Defines a threshold of activity that, after crossed, sets off an operator alarm or alert)
                "C. It is a level at which an operator is alerted. Options A, B, and D are distractors.");
        frågorLista[103] = new Frågor("104. Encapsulation provides what type of action?",
                new String[]{
                        "A. Ensures perfect forward secrecy with IPsec",
                        "B. Places one type of packet inside another",
                        "C. Provides for data integrity",
                        "D. Provides encryption and VPNs"},
                3, // Correct answer is C (Provides for data integrity)
                "C. The purpose of a hashing algorithm is to provide integrity. The message is hashed at each end of the transmission, and if the hash is equal, the message did not change. Options A, B, and C are incorrect because they have nothing to do with a hashing algorithm.");

        frågorLista[104] = new Frågor("105. The OSI model features how many layers?",
                new String[]{
                        "A. Six",
                        "B. Seven",
                        "C. Four",
                        "D. Five"},
                2, // Correct answer is B (Seven)
                "B. The OSI model consists of seven layers.");

        frågorLista[105] = new Frågor("106. Which of the following offers the highest bandwidth and fiber-optic transmissions?",
                new String[]{
                        "A. Single-mode",
                        "B. Dual-mode",
                        "C. Multimode",
                        "D. Plastic optical fiber"},
                1, // Correct answer is A (Single-mode)
                "A. Single-mode fiber-optic offers the highest bandwidth.");

        frågorLista[106] = new Frågor("107. The address space for IPv6 is how many bits?",
                new String[]{
                        "A. 144 bits",
                        "B. 132 bits",
                        "C. 32 bits",
                        "D. 128 bits"},
                4, // Correct answer is D (128 bits)
                "D. IPv6 uses a 128-bit address space.");

        frågorLista[107] = new Frågor("108. Switches operate at layer 2 on the OSI model and route what type of information?",
                new String[]{
                        "A. HMAC addresses",
                        "B. IP addresses",
                        "C. MAC addresses",
                        "D. Secure packets"},
                3, // Correct answer is C (MAC addresses)
                "C. Switches operate at layer 2 of the OSI model and route based on MAC addresses.");

        frågorLista[108] = new Frågor("109. Which of the following protocols is referred to as connection oriented?",
                new String[]{
                        "A. TCP",
                        "B. UDP",
                        "C. SYN",
                        "D. NAT"},
                1, // Correct answer is A (TCP)
                "A. TCP (Transmission Control Protocol) is a connection-oriented protocol.");

        frågorLista[109] = new Frågor("110. The most redundancy and connection speed is offered by which of the following network typology?",
                new String[]{
                        "A. Ring",
                        "B. Tree",
                        "C. Mesh",
                        "D. Star"},
                3, // Correct answer is C (Mesh)
                "C. A mesh network topology offers the most redundancy and connection speed.");
        frågorLista[110] = new Frågor("111. In a private enterprise environment, which of the following is most secure?",
                new String[]{
                        "A. Decentralized key management",
                        "B. Centralized key management",
                        "C. Individual key management",
                        "D. Distributed key management"},
                2, // Correct answer is B (Centralized key management)
                "B. In a private enterprise environment, centralized key management is often considered more secure as it allows for better control and management of keys.");

        frågorLista[111] = new Frågor("112. Which of the following media access methods features a node broadcasting a tone prior to transmitting?",
                new String[]{
                        "A. CSMA/CT",
                        "B. CSMA/CD",
                        "C. CSMA/CA",
                        "D. CSMA/CS"},
                3, // Correct answer is C (CSMA/CA)
                "C. CSMA/CA (Carrier Sense Multiple Access with Collision Avoidance) features a node broadcasting a tone prior to transmitting.");

        frågorLista[112] = new Frågor("113. Which of the following best describes converged network communications?",
                new String[]{
                        "A. The combination of two types of media such as copper and fiber-optic",
                        "B. The use of Ethernet when communicating on a wireless network",
                        "C. Transmission of voice and media files over a network",
                        "D. The combination of SMS and chat capability on business networks"},
                3, // Correct answer is C (Transmission of voice and media files over a network)
                "C. Converged network communications refer to the transmission of voice and media files over a network.");

        frågorLista[113] = new Frågor("114. Continuous monitoring is best defined by which of the following?",
                new String[]{
                        "A. An automated system that regulates the flow of traffic on a network",
                        "B. An automated system used to detect humidity and condensation in a data center",
                        "C. A method of monitoring that is used to detect risk issues within an organization",
                        "D. A manual system for monitoring a hot site in the event of a requirement immediate use"},
                3, // Correct answer is C (A method of monitoring that is used to detect risk issues within an organization)
                "C. Continuous monitoring is a method used to detect risk issues within an organization.");

        frågorLista[114] = new Frågor("115. Which of the following best describes Kerberos?",
                new String[]{
                        "A. A federation of third-party suppliers that use a single sign-on",
                        "B. An authentication, single sign-on protocol",
                        "C. A method of maintaining network usage integrity",
                        "D. A method of sharing information between network resources"},
                2, // Correct answer is B (An authentication, single sign-on protocol)
                "B. Kerberos is an authentication and single sign-on protocol.");

        frågorLista[115] = new Frågor("116. Which type of network device is used to create a virtual local area network?",
                new String[]{
                        "A. A router",
                        "B. NIC cards in promiscuous mode",
                        "C. A switch",
                        "D. A network concentrator"},
                3, // Correct answer is C (A switch)
                "C. A switch is used to create a virtual local area network (VLAN).");

        frågorLista[116] = new Frågor("117. Which choice best describes a federation?",
                new String[]{
                        "A. Organizations that may rely on each other in the event of a disaster event",
                        "B. An association of nonrelated third-party organizations that share information based upon a single sign-on",
                        "C. Group organizations that share immediate information concerning zero day attacks",
                        "D. A single sign-on technique that allows nonrelated third-party organizations access to network resources"},
                2, // Correct answer is B (An association of nonrelated third-party organizations that share information based upon a single sign-on)
                "B. A federation is an association of nonrelated third-party organizations that share information based upon a single sign-on.");
        frågorLista[117] = new Frågor("118. Which answer is most accurate regarding firewalls?",
                new String[]{
                        "A. They route traffic based upon inspecting packets.",
                        "B. They filter traffic based upon inspecting packets.",
                        "C. They switch packets based upon inspecting packets.",
                        "D. They forward packets to the Internet based upon inspecting packets."},
                2, // Correct answer is B (They filter traffic based upon inspecting packets)
                "B. Firewalls filter traffic based upon inspecting packets.");

        frågorLista[118] = new Frågor("119. Which answer is most accurate regarding a wireless intrusion prevention system?",
                new String[]{
                        "A. It is used to fine-tune the traffic on a wireless network.",
                        "B. Rogue access points are detected.",
                        "C. It broadcasts a jamming tone at a potential intruder.",
                        "D. It monitors all traffic arriving at a wireless access point for proper ID fields."},
                4, // Correct answer is D (It monitors all traffic arriving at a wireless access point for proper ID fields)
                "D. A wireless intrusion prevention system (WIPS) monitors all traffic arriving at a wireless access point for proper ID fields.");

        frågorLista[119] = new Frågor("120. Which answer is most accurate regarding IEEE 802.11i?",
                new String[]{
                        "A. Provides 54 Mbit/s using the 2.4 GHz frequency spectrum",
                        "B. Provides security enhancements using WPA2",
                        "C. Provide security enhancements using WEP",
                        "D. Provides both 5 GHz and 2.4 GHz compatibility"},
                2, // Correct answer is B (Provides security enhancements using WPA2)
                "B. IEEE 802.11i provides security enhancements using WPA2.");

        frågorLista[120] = new Frågor("121. Which choice best describes Bluetooth?",
                new String[]{
                        "A. A secure transmission methodology",
                        "B. A transmission tool used to back up hard disks",
                        "C. A method of data synchronization between devices",
                        "D. A method of converting data from one type of media to another"},
                3, // Correct answer is C (A method of data synchronization between devices)
                "C. Bluetooth is a method of data synchronization between devices.");

        frågorLista[121] = new Frågor("122. Which of the following is a term used for a rogue Wi-Fi access point that appears to be legitimate but actually has been set up to intercept wireless communications?",
                new String[]{
                        "A. Captive access point",
                        "B. Evil twin",
                        "C. Deception twin",
                        "D. Hidden access point"},
                2, // Correct answer is B (Evil twin)
                "B. An 'Evil twin' is a term used for a rogue Wi-Fi access point that appears to be legitimate but is set up to intercept wireless communications.");

        frågorLista[122] = new Frågor("123. For optimal signal quality, which of the following is correct concerning wireless antenna placement?",
                new String[]{
                        "A. Always use a Yagi antenna for 360° broadcasts.",
                        "B. Place the antenna near a doorway facing into a room.",
                        "C. Place the antenna as high as possible in the center of the service area.",
                        "D. Wireless antennas must always be placed in the line of sight."},
                3, // Correct answer is C (Place the antenna as high as possible in the center of the service area)
                "C. For optimal signal quality, it's best to place the antenna as high as possible in the center of the service area.");
        frågorLista[123] = new Frågor("124. Which of the following is the most likely to attack using an advanced persistent threat?",
                new String[]{
                        "A. Hacker",
                        "B. Nation state",
                        "C. Cracker",
                        "D. Script kiddie"},
                2, // Correct answer is B (Nation state)
                "B. An advanced persistent threat is a type of cyber terrorism malware usually placed by a well-funded, country-sponsored cyber-attack group.");

        frågorLista[124] = new Frågor("125. Which of the following options best describes a hacker with an agenda?",
                new String[]{
                        "A. Cracker",
                        "B. Nation state",
                        "C. Anarchist",
                        "D. Hacktivist"},
                4, // Correct answer is D (Hacktivist)
                "D. A hacktivist has a political, social, or personal agenda.");

        frågorLista[125] = new Frågor("126. Which statement most accurately describes a virus?",
                new String[]{
                        "A. It divides itself into many small pieces inside a PC.",
                        "B. It always attacks an email contacts list.",
                        "C. It requires an outside action in order to replicate.",
                        "D. It replicates without assistance."},
                3, // Correct answer is C (It requires an outside action in order to replicate)
                "C. A virus always requires an outside action in order to replicate.");

        frågorLista[126] = new Frågor("127. Which option is not a commonly accepted definition for a script kiddie?",
                new String[]{
                        "A. A young unskilled hacker",
                        "B. A young inexperienced hacker",
                        "C. A hacker that uses scripts or tools to create a text",
                        "D. A highly skilled attacker"},
                4, // Correct answer is D (A highly skilled attacker)
                "D. All of the other options describe a typical script kiddie.");

        frågorLista[127] = new Frågor("128. Which choice describes the path of an attack?",
                new String[]{
                        "A. A threat vector",
                        "B. A threat source location",
                        "C. The threat action effect",
                        "D. A threat vehicle"},
                1, // Correct answer is A (A threat vector)
                "A. A threat vector describes the path of an attack.");

        frågorLista[128] = new Frågor("129. Which choice best describes a zombie?",
                new String[]{
                        "A. Malware that logs keystrokes",
                        "B. A member of a botnet",
                        "C. A tool used to achieve privilege escalation",
                        "D. A type of root kit"},
                2, // Correct answer is B (A member of a botnet)
                "B. A member of a botnet is referred to as a bot or a zombie computer.");

        frågorLista[129] = new Frågor("130. Which answer best describes an advanced persistent threat?",
                new String[]{
                        "A. Advanced malware attack by a persistent hacker",
                        "B. A malware attack by a nation state",
                        "C. An advanced threat that continuously causes havoc",
                        "D. Malware that persistently moves from one place to another"},
                2, // Correct answer is B (A malware attack by a nation state)
                "B. An APT is malware usually put in place by a nation state.");
        frågorLista[130] = new Frågor("131. Which choice is the most accurate description of a retrovirus?",
                new String[]{
                        "A. A virus designed several years ago",
                        "B. A virus that attacks anti-malware software",
                        "C. A virus that uses tried-and-true older techniques to achieve a purpose",
                        "D. A mobile virus that attacks older phones"},
                2, // Correct answer is B (A virus that attacks anti-malware software)
                "B. A retrovirus attacks anti-malware software and sometimes disables a signature library or simply turns off the detection mechanism.");

        frågorLista[131] = new Frågor("132. Which choice is an attack on a senior executive?",
                new String[]{
                        "A. Phishing attack",
                        "B. Whaling attack",
                        "C. Watercooler attack",
                        "D. Golf course attack"},
                2, // Correct answer is B (Whaling attack)
                "B. A whaling attack targets a senior executive to get them to click a link in an email in order to infect their computer.");

        frågorLista[132] = new Frågor("133. Which of the following is most often used as a term to describe an attack that makes use of a previously unknown vulnerability?",
                new String[]{
                        "A. Discovery attack",
                        "B. First use attack",
                        "C. Premier attack",
                        "D. Zero-day attack"},
                4, // Correct answer is D (Zero-day attack)
                "D. A zero-day attack refers to a type of attack in which the attacker uses a previously unknown attack technique or exploits a previously unknown vulnerability.");

        frågorLista[133] = new Frågor("134. Which type of client-side program always runs in a sandbox?",
                new String[]{
                        "A. HTML4 control",
                        "B. Visual Basic script",
                        "C. Java applet",
                        "D. Active X control"},
                3, // Correct answer is C (Java applet)
                "C. By design, Java always creates a sandbox in which to execute an applet on a client machine. This prohibits the applet from being able to attack either the host machine or an application.");

        frågorLista[134] = new Frågor("135. Which of the following would best describe the purpose of a trusted platform module?",
                new String[]{
                        "A. A module that verifies the authenticity of a guest host",
                        "B. The part of the operating system that must be invoked all the time and is referred to as a security kernel",
                        "C. A dedicated microprocessor that offloads cryptographic processing from the CPU while storing cryptographic keys",
                        "D. A computer facility with cryptographic processing power"},
                3, // Correct answer is C (A dedicated microprocessor that offloads cryptographic processing from the CPU while storing cryptographic keys)
                "C. The trusted platform module is a crypto processor that performs as a dedicated micro- processor of cryptographic algorithms.");

        frågorLista[135] = new Frågor("136. What is the prime objective of code signing?",
                new String[]{
                        "A. To verify the author and integrity of downloadable code that is signed using a private key",
                        "B. To verify the author and integrity of downloadable code that is signed using a public key",
                        "C. To verify the author and integrity of downloadable code that is signed using a symmetric key",
                        "D. To verify the author and integrity of downloadable code that is signed using a master key"},
                1, // Correct answer is A (To verify the author and integrity of downloadable code that is signed using a private key)
                "A. A private key is owned by the author and is used to encrypt the message digest or hash value of the code. The hash value provides the integrity, and the private key provides a digi- tal signature and nonrepudiation by the author. The author’s public key, usually provided in a digital certificate, is the only key that will decrypt the hash value.");

        frågorLista[136] = new Frågor("137. Which choice least describes a cloud implementation?",
                new String[]{
                        "A. Broadly assessable by numerous networking platforms",
                        "B. Rapid elasticity",
                        "C. On-demand self-service",
                        "D. Inexpensive"},
                4, // Correct answer is D (Inexpensive)
                "D. The cloud is a metering, measured service similar to a utility, sometimes referred to as a “pay as you go” model. At this point, cloud services are still fairly expensive compared to installing one more disk drive in an organization’s server.");
        frågorLista[137] = new Frågor("138. Which option is not a cloud deployment model?",
                new String[]{
                        "A. Private cloud",
                        "B. Corporate cloud",
                        "C. Community cloud restoration",
                        "D. Public cloud"},
                2, // Correct answer is B (Corporate cloud)
                "B. According to NIST Special Publication 800-145, “The NIST Definition of Cloud Com- puting,” a corporate cloud is not a cloud deployment model. Corporate cloud and private cloud refer to the same thing.");

        frågorLista[138] = new Frågor("139. Which of the following options is not a standard cloud service model?",
                new String[]{
                        "A. Help Desk as a Service",
                        "B. Software as a Service",
                        "C. Platform as a service",
                        "D. Infrastructure as a service"},
                1, // Correct answer is A (Help Desk as a Service)
                "A. Help Desk as a Service is not one of the NIST-listed cloud service models, although it is a service that might be offered over the cloud.");

        frågorLista[139] = new Frågor("140. Which of the following is the most accurate statement?",
                new String[]{
                        "A. Any corporation that has done business in the European Union in excess of five years may apply for the Safe Harbor amendment.",
                        "B. Argentina and Brazil are members of the Asia-Pacific Privacy Pact.",
                        "C. The United States leads the world in privacy legislation.",
                        "D. The European Union’s General Data Protection Regulation provides a single set of rules for all member states."},
                4, // Correct answer is D (The European Union’s General Data Protection Regulation provides a single set of rules for all member states)
                "D. The General Data Protection Regulation, which superseded Directive 95/46 BC, requires that all member states abide by the legal principles in the regulation and that these principles are not arbitrary.");

        frågorLista[140] = new Frågor("141. Which of the following most accurately describes eDiscovery:",
                new String[]{
                        "A. Any information put on legal hold",
                        "B. A legal tool used to request suspected evidentiary information that may be used in litigation",
                        "C. All information obtained through proper service of the search warrant",
                        "D. Any information owned by an organization with the exception of trade secrets."},
                2, // Correct answer is B (A legal tool used to request suspected evidentiary information that may be used in litigation)
                "B. eDiscovery is a legal tool used by opposing counsel to obtain requested information that may contain evidence or other useful information for a lawsuit. eDiscovery is not the infor- mation itself. It is the process of obtaining the information.");

        frågorLista[141] = new Frågor("142. Which of the following is most accurate concerning virtualization security?",
                new String[]{
                        "A. Only hypervisors can be secured, not the underlying virtual machine.",
                        "B. Virtual machines are only secured by securing the underlying hardware infrastructure.",
                        "C. Virtual machines can be secured as well as the hypervisor and underlying hardware infrastructure.",
                        "D. Virtual machines by nature are always insecure."},
                3, // Correct answer is C (Virtual machines can be secured as well as the hypervisor and underlying hardware infrastructure)
                "C. Security controls may be placed anywhere in a virtual environment. Security in depth is always the best practice when securing any environment.");

        frågorLista[142] = new Frågor("143. Which of the following is most accurate concerning data warehousing and big data architecture?",
                new String[]{
                        "A. Data warehouses are used for long-term storage of archive data.",
                        "B. Data is processed using auto-synthesis to enhance processing speed.",
                        "C. Big data is so large that standard relational database management systems do not work. Data must be processed by parallel processors.",
                        "D. Data can never be processed in real time."},
                3, // Correct answer is C (Big data is so large that standard relational database management systems do not work. Data must be processed by parallel processors)
                "C. Several big data processing models exist that illustrate how big data can be processed by parallel processors reaching into the thousands of servers.");
        //kapitel 3 Under




        currentFrågaIndex = 0;
        setText();
        setKnappar();

    }
}
