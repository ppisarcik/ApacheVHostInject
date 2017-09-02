package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

public class settings {
    public JPanel settingsPanel;
    private JButton saveSettings;
    private JTextField vhostRoute;
    private JTextField hostRoute;

    public settings() {
        saveSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "test");
            }
        });
    }

    public void main(String[] args) {

        Properties props = new Properties();
        InputStream is = null;

        // First try loading from the current directory
        try {
            File f = new File("../../config.properties");
            is = new FileInputStream( f );
        }
        catch ( Exception e ) { is = null; }

        try {
            if ( is == null ) {
                // Try loading from classpath
                is = getClass().getResourceAsStream("../../config.properties");
            }

            // Try loading properties from the file (if found)
            props.load( is );
        }
        catch (Exception e) { System.out.println("errror");}

        String virtualHostRoute = props.getProperty("vhostRoute", "/home/peter/Projects/ApacheVHostInject/vhosts");
        String hostRoute = props.getProperty("hostRoute", "/home/peter/Projects/ApacheVHostInject/host");
    }
}
