/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2.client_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author diamo
 */
public class Client extends JFrame implements ActionListener {
    
    private JLabel headingText = new JLabel("NEMA Client Application");
    private JLabel buttonText = new JLabel("Receive Database Details");
    private JLabel outputText = new JLabel("Output");
    private JLabel addTruckText = new JLabel("Add New Fire Truck");
    private JButton getDronesButton = new JButton("Get Drones");
    private JButton getFiresButton = new JButton("Get Fires");
    private JButton getOldFiresButton = new JButton("Get Historical Fires");
    private JButton getTrucksButton = new JButton("Get Trucks");
    private JButton newTruckButton = new JButton("Add Fire Truck");
    private static JTextArea outputTextArea = new JTextArea(18, 55);
    private JScrollPane scrollPane; // Scroll pane for the text area
    
    Client() {
        // Sets GUI settings and layout
        super("NEMA Client");
        
        // Sets Application to close on x button clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Sets font for title
        headingText.setFont(new Font("Arial", Font.PLAIN, 30));
        
        // Other GUI settings
        setSize(750, 600);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        
        // Heading Panel
        JPanel headingPanel = new JPanel();
        headingPanel.setPreferredSize(new Dimension(750, 40));
        headingPanel.add(headingText);
        
        // Set Text Area Wrapping and read-only
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(750, 40));
        buttonPanel.add(getDronesButton);
        buttonPanel.add(getFiresButton);
        buttonPanel.add(getOldFiresButton);
        buttonPanel.add(getTrucksButton);
        
        // Output Panel
        JPanel outputPanel = new JPanel();
        outputPanel.setPreferredSize(new Dimension(750, 300));
        outputPanel.add(outputTextArea);
        
        // Text Area Vertical ScrollBar
        scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputPanel.add(scrollPane);
        
        // Output Panel
        JPanel addPanel = new JPanel();
        addPanel.setPreferredSize(new Dimension(750, 350));
        addPanel.add(newTruckButton);
        
        // Add stuff to frame
        add(headingPanel);
        add(buttonText);
        add(buttonPanel);
        add(outputText);
        add(outputPanel);
        add(addTruckText);
        add(addPanel);
        
        // Make GUI visible
        this.setVisible(true);
        
        // Action Listeners for Buttons
        getDronesButton.addActionListener(this);
        getFiresButton.addActionListener(this);
        getOldFiresButton.addActionListener(this);
        getTrucksButton.addActionListener(this);
        newTruckButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // This runs when an object action is clicked
        // Gets the name of the object clicked and finds the case
        // Runs the corresponding method and breaks the switch
        String actionString=e.getActionCommand();
        switch(actionString) {
            case "Get Drones":
                getDrones();
                break;
                
            case "Get Fires":
                getFires();
                break;
                
            case "Get Historical Fires":
                getOldFires();
                break;
                
            case "Get Trucks":
                getTrucks();
                break;
            
            case "Add Fire Truck":
                addFireTruck();
                break;
        }
    }
    
    public static void main(String[] args) {
        // Start GUI
        Client gui = new Client();
    }
    
    public void getDrones() {
        outputTextArea.setText("Drone Details:\n");
        getData("drones");
    }
    
    public void getFires() {
        outputTextArea.setText("Current Fire Details:\n");
        getData("fires");
    }
    
    public void getOldFires() {
        outputTextArea.setText("Historical Fire Details:\n");
        getData("oldfires");
    }
    
    public void getTrucks() {
        outputTextArea.setText("Fire Truck Details:\n");
        getData("firetrucks");
    }
    
    public void addFireTruck() {
        
        int intId = 0;
        int intFireId = 0;
        String enteredName = "";
        
        // Gets input of fire truck id, if non int or nothing entered, reprompted
        while (true) {
            String enteredId = JOptionPane.showInputDialog(null, "Enter ID of new fire truck.");
            if (enteredId == null) {
                return;
            }
            try {
                intId = Integer.parseInt(enteredId);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID must be numerical.");
            }
        }
        
        // Gets input for name of fire truck
        while (true) {
            enteredName = JOptionPane.showInputDialog(null, "Enter name of fire truck.");
            if (enteredName == null) {
                return;
            } else {
                break;
            }
        }
        
        // Gets input of fire id, if non int or nothing entered, reprompted
        while (true) {
            String enteredFireId = JOptionPane.showInputDialog(null, "Enter ID of fire for truck to be sent to.");
            if (enteredFireId == null) {
                return;
            }
            try {
                intFireId = Integer.parseInt(enteredFireId);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID must be numerical.");
            }
        }
        
        // Sending data to web service to be added to database
        try {
            // URL of web server
            String url = "http://localhost:8080/webserver/service/add";
            
            // Open URL connection
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            
            // Makes output with variables to be added seperated by commas
            String output = intId + "," + enteredName + "," + intFireId;
            
            // Outputs the string to the web server to insert into database
            // Flushes output for further use and closes outputstream
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes());
            outputStream.flush();
            outputStream.close();
            
            // Close connection
            connection.disconnect();
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, "Fire Truck Added\nID: " + intId + "\nName: " + enteredName + "\nFire ID: " + intFireId);
    }
    
    private void getData(String input) {
        try {
            // Gets data from web service, URL determined by button input
            // URL of web server
            String url = "http://localhost:8080/webserver/service/" + input;
            
            // Open URL connection
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            
            // Set request method
            connection.setRequestMethod("GET");
            
            // Opens Buffered reader and reads output from web server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            
            // Goes through lines added in case there's more than one
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            // Closes Reader
            reader.close();
            
            // Adds response to output text area
            outputTextArea.append(response.toString());
            
            // Close the connection
            connection.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
