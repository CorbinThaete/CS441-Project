import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class ItemInformation {
    JFrame f;
    JComboBox cb;
    ItemInformation() throws FileNotFoundException{
        f=new JFrame("Champions List");
        f.setUndecorated(true);  
        JLabel label = new JLabel();
        JPanel J1 = new JPanel();
        J1.setLocation(60,40);
        J1.setSize(200,200);
        J1.setOpaque(false);
        label.setHorizontalAlignment(JLabel.CENTER);  
        label.setSize(400,100);
        JButton b=new JButton("Select");
        b.setBounds(200,100,75,20);
        b.setLocation(175, 20);  

        String champions[]={"Alistar", "Ekko", "Lulu", "Bard", "Jayce", "Vex"};      

        cb=new JComboBox(champions); 
        //cb.setForeground(Color.red);
        //cb.setBackground(new Color(1,1,1,1)); 
        cb.setFont(new Font("Georgia", Font.BOLD, 11));
        cb.setBounds(50, 100,90,20); 
        cb.setLocation(50,20);   
        f.add(cb); f.add(label); f.add(b); f.add(J1); 
        f.setLayout(null);    
        f.setSize(350,350);    
        f.setVisible(true);
        f.setLayout(null);    
        f.setSize(350,350);
        f.setBackground(new Color(0, 0, 0, 0));    
        f.setVisible(true);   
        f.setAlwaysOnTop(true);
        f.setLocation(2300, 300);

        b.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                String data = "Champion selected: " + cb.getItemAt(cb.getSelectedIndex());
                label.setText(data);
                label.setVisible(false);

                String filename = "src/main/resources/" + cb.getItemAt(cb.getSelectedIndex()) + ".txt";
                J1.removeAll();

                try
                {
                    File myObj = new File(filename);
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()){
                        String input_data = myReader.nextLine();
                        JLabel J2 = new JLabel(input_data + ", ");
                        J2.setForeground(Color.red);
                        J2.setFont(new Font("Georgia", Font.BOLD, 13));
                        J1.add(J2);
                    }
                    myReader.close();
                }
                catch(FileNotFoundException f)
                {
                    System.out.println("An error occurred.");
                    f.printStackTrace();
                }

            }
        });
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

