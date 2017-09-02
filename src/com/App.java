package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.settings;

public class App {

    private JButton submit;
    private JPanel mainPanel;
    private JTextField serverName;
    private JTextField projectRoute;
    private JButton settings;
    private String vhostRoute;
    private String hosts;

    private App() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routes();
                JOptionPane.showMessageDialog(null, serverName.getText());
                 writeVirtualHost(projectRoute.getText(), serverName.getText());
                 writeHost(serverName.getText());
            }
        });
        settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame settings = new JFrame("settings");
                settings.setContentPane(new settings().settingsPanel);
                settings.pack();
                settings.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void writeVirtualHost(String projectRoute, String serverName)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(vhostRoute, true))) {

            bw.write("\n");
            bw.write("<VirtualHost *:80>\n");
            bw.write( "  DocumentRoot \""+ projectRoute + "\"\n");
            bw.write("  ServerName " + serverName + "\n");
            bw.write("  <Directory \"" + projectRoute + "\"> \n");
            bw.write( "  Require all Granted\n");
            bw.write("  </Directory>\n");
            bw.write("</VirtualHost>\n");

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private void writeHost(String serverName)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(hosts, true))) {

            bw.write("\n");
            bw.write("127.0.0.1       "+serverName);
            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private void routes()
    {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/config.cfg"));
            vhostRoute = properties.getProperty("vhostRoute");
            hosts = properties.getProperty("hostRoute");

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
