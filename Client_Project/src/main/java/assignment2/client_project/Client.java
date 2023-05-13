/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2.client_project;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author diamo
 */
public class Client extends JFrame {

    private JFrame frame;
    private JLabel headingText = new JLabel("NEMA Client Application");
    private JLabel buttonText = new JLabel("Receive Database Details");
    private JLabel outputText = new JLabel("Output");
    private JButton getDrones = new JButton("Get Drones");
    private JButton getFires = new JButton("Get Fires");
    private JButton getOldFires = new JButton("Get Old Fires");
    private JButton getTrucks = new JButton("Get Trucks");
    private static JTextArea outputTextArea = new JTextArea(100, 60);
    private JScrollPane scrollPane; // Scroll pane for the text area
    
    Client() {
        // Sets GUI settings and layout
        super("NEMA Client");
        
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
        buttonPanel.add(getDrones);
        buttonPanel.add(getFires);
        buttonPanel.add(getOldFires);
        buttonPanel.add(getTrucks);
        
        // Output Panel
        JPanel outputPanel = new JPanel();
        outputPanel.setPreferredSize(new Dimension(750, 300));
        outputPanel.add(outputTextArea);
        
        // Text Area Vertical ScrollBar
        scrollPane = new JScrollPane(outputText);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputPanel.add(scrollPane);
        
        add(headingPanel);
        add(buttonText);
        add(buttonPanel);
        add(outputText);
        add(outputPanel);
        
        this.setVisible(true);
        
    }
    
    public static void main(String[] args) {
        // Start GUI
        Client gui = new Client();
    }
    
}
