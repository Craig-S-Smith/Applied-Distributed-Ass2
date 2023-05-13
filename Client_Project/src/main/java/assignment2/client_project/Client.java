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
    private JButton getDrones = new JButton("Get Drones");
    private JButton getFires = new JButton("Get Fires");
    private JButton getOldFires = new JButton("Get Old Fires");
    private JButton getTrucks = new JButton("Get Trucks");
    private static JTextArea outputText = new JTextArea(100, 25);
    private JScrollPane scrollPane; // Scroll pane for the text area
    
    Client() {
        // Sets GUI settings and layout
        super("NEMA Client");
        
        // Sets font for title
        headingText.setFont(new Font("Arial", Font.PLAIN, 30));
    }
    
    public static void main(String[] args) {
        // Start GUI
        Client gui = new Client();
    }
    
}
