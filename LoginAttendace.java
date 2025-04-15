import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoginAttendance {
    public static void main(String[] args){

        //JFrame to creat a window
        JFrame loginFrame=new JFrame();
        loginFrame.setTitle("Login Form");
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);

        //JPanel
        JPanel loginPanel=new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.lightGray);
        loginPanel.setBounds(20,20,350,200);

        //JLabel- text
        JLabel usernameLabel=new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setBounds(22, -90, 250, 250);

        //JLabel for Password
        JLabel passLabel=new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passLabel.setBounds(22, -30, 250, 250);

        //JTextField for username
        JTextField userInput=new JTextField();
        userInput.setBounds(110,25,230,35);

        //JPasswordField
        JPasswordField passwordInput=new JPasswordField();
        passwordInput.setBounds(110,80,230,35);

        //JButton
        JButton loginBtn=new JButton("Log in");
        loginBtn.setBounds(110,120,80,40);

        JButton cancelBtn=new JButton("Cancel");
        cancelBtn.setBounds(210,120,80,40);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username=userInput.getText();
                String password=String.valueOf(passwordInput.getPassword());
                if(username.equals("Teacher1") && password.equals("12345")){
                    JOptionPane.showMessageDialog(loginFrame,"Login Successfully!");
                    loginFrame.dispose();
                    showAttendance();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid Username/Password");
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                userInput.setText(null);
                passwordInput.setText(null);  
            }
        });

        loginFrame.add(cancelBtn);
        loginFrame.add(loginBtn);
        loginFrame.add(passwordInput);
        loginFrame.add(userInput);
        loginFrame.add(passLabel);
        loginFrame.add(usernameLabel);
        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }

    public static void showAttendance() {
        JFrame attendancFrame = new JFrame();
        attendancFrame.setTitle("Attendance Record System");
        attendancFrame.setSize(500, 450);
        attendancFrame.setLocationRelativeTo(null);
        attendancFrame.setLayout(null);
        attendancFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel attendancePanel = new JPanel();
        attendancePanel.setLayout(null);
        attendancePanel.setBackground(Color.lightGray);
        attendancePanel.setBounds(20, 20, 440, 370);

        JLabel nameLabel = new JLabel("Enter Name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBounds(20, 20, 150, 30);

        JTextField nameInput = new JTextField();
        nameInput.setBounds(140, 20, 260, 30);

        JTextArea recordArea = new JTextArea();
        recordArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(recordArea);
        scrollPane.setBounds(20, 120, 380, 200);

        JButton presentBtn = new JButton("Present");
        presentBtn.setBounds(140, 70, 100, 30);

        JButton absentBtn = new JButton("Absent");
        absentBtn.setBounds(260, 70, 100, 30);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(20, 330, 100, 30);

        ArrayList<String> records = new ArrayList<>();
        int[] count = {0};

        ActionListener markAttendance = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(attendancFrame, "Please enter a name.");
                    return;
                }

                String status = e.getActionCommand();
                count[0]++;
                String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String record = count[0] + ". " + name + " - " + status + " [" + dateTime + "]";
                records.add(record);
                recordArea.append(record + "\n");
                nameInput.setText("");
            }
        };

        presentBtn.addActionListener(markAttendance);
        absentBtn.addActionListener(markAttendance);

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (recordArea.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(attendancFrame, "No attendance record to save.");
                        return;
                    }

                    java.io.FileWriter writer = new java.io.FileWriter("attendance_records.txt");
                    writer.write(recordArea.getText());
                    writer.close();
                    JOptionPane.showMessageDialog(attendancFrame, "Attendance saved successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(attendancFrame, "Error saving file: " + ex.getMessage());
                }
            }
        });

        attendancePanel.add(nameLabel);
        attendancePanel.add(nameInput);
        attendancePanel.add(presentBtn);
        attendancePanel.add(absentBtn);
        attendancePanel.add(scrollPane);
        attendancePanel.add(saveBtn);

        attendancFrame.add(attendancePanel);
        attendancFrame.setVisible(true);
    }
}