package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    private JButton buttonmsg;
    private JPanel mainPanel;
    private JTextField serverName;
    private JTextField projectRoute;
    private JCheckBox win;
    private JCheckBox linux;
    private String vhostRoute;
    private String hosts;

    private App() {
        buttonmsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                os();
                JOptionPane.showMessageDialog(null, serverName.getText());
                 writeVirtualHost(projectRoute.getText(), serverName.getText());
                 writeHost(serverName.getText());
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
        if (this.linux.isSelected()) {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(vhostRoute, true))) {

                bw.write("\n");
                bw.write("<VirtualHost *:80>\n");
                bw.write( "  DocumentRoot \""+ projectRoute + "\"\n");
                bw.write("  ServerName " + serverName + "\n");
                bw.write("  <Directory \"" + projectRoute + "\"> \n");
                bw.write( "  Require all Granted\n");
                bw.write("  </Directory>\n");
                bw.write("</VirtualHost>\n");
                // no need to close it.
                //bw.close();

                System.out.println("Done");

            } catch (IOException e) {

                e.printStackTrace();

            }


        } else if(this.win.isSelected()) {
            System.out.println("Windows");
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Must choose operating system", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void os()
    {
        if (linux.isSelected()) {
            vhostRoute = "/home/peter/Projects/ApacheVHostInject/vhosts";
            hosts = "/home/peter/Projects/ApacheVHostInject/host";
        }

        if (win.isSelected()) {
            vhostRoute = "C:/xampp/apache/conf/extra/httpd-vhosts.conf";
            hosts = "C:/Windows/System32/drivers/etc/hosts";
        }
    }
}
