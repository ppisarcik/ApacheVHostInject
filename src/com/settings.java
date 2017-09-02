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

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("config/config.cfg"))) {

                    bw.write("vhostRoute=" + vhostRoute.getText() + "\n");
                    bw.write("hostRoute=" + hostRoute.getText());
                    System.out.println("Done");

                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        });
    }

}
