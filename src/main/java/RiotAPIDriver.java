import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;

import com.merakianalytics.orianna.types.common.Region;

public class RiotAPIDriver {
    static JFrame frame = new JFrame("Riot API Tool: League");
    static JPanel panel = new JPanel();
    static GridBagConstraints c = new GridBagConstraints();
    static Region region;
    static String username;

    public static void main(String[] args) throws FileNotFoundException {
        panel.setLayout(new GridBagLayout());

        frame.setSize(500,500);
        frame.setLocation(1030, 290);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setUndecorated(true);
        regionSelector();
        nameSelector();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Helper method to select region
    private static void regionSelector(){
        JButton button = new JButton("Select");
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        JLabel instructions = new JLabel("Select region: ");
        instructions.setForeground(Color.white);
        JComboBox rsb;



        String regions[] = {"North America", "EU West", "EU East", "Brazil", "Oceania",
                "Russia", "Turkey", "Latin America North", "Latin America South",
                "Korea", "Japan"};

        rsb = new JComboBox(regions);
        rsb.setFont(new Font("Georgia", Font.BOLD, 12));
        rsb.setForeground(Color.white);
        rsb.setBackground(Color.darkGray);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(rsb, c);
        c.gridx = 2;
        panel.add(button, c);
        c.gridx = 0;
        panel.add(instructions,c);
        panel.setBackground(Color.black);
        frame.add(panel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch((String) rsb.getItemAt(rsb.getSelectedIndex())){
                    case "North America":
                        region =  Region.NORTH_AMERICA;
                        break;
                    case "EU West":
                        region = Region.EUROPE_WEST;
                        break;
                    case "EU East":
                        region = Region.EUROPE_NORTH_EAST;
                        break;
                    case "Brazil":
                        region = Region.BRAZIL;
                        break;
                    case "Oceania":
                        region = Region.OCEANIA;
                        break;
                    case "Russia":
                        region = Region.RUSSIA;
                        break;
                    case "Turkey":
                        region = Region.TURKEY;
                        break;
                    case "Latin America North":
                        region = Region.LATIN_AMERICA_NORTH;
                        break;
                    case "Latin America South":
                        region = Region.LATIN_AMERICA_SOUTH;
                        break;
                    case "Korea":
                        region = Region.KOREA;
                        break;
                    case "Japan":
                        region = Region.JAPAN;
                        break;
                }
                System.out.println(region);
                panel.remove(rsb);
                panel.remove(button);
                panel.remove(instructions);
                panel.repaint();
                if(username != null){
                    submit();
                }
            }
        });
    }
    //Helper method to grab username
    private static void nameSelector(){
        JButton button = new JButton("Submit");
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        JTextField field = new JTextField(14);
        field.setBackground(Color.DARK_GRAY);
        JLabel instructions = new JLabel("Enter username here: ");
        instructions.setForeground(Color.white);

        c.gridx = 2;
        c.gridy = 1;
        panel.add(button, c);
        c.gridx = 1;
        panel.add(field, c);
        c.gridx = 0;
        panel.add(instructions,c);
        panel.setBackground(Color.black);
        frame.add(panel);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = field.getText();
                field.setText(" ");
                System.out.println(username);
                panel.remove(button); panel.remove(field); panel.remove(instructions);
                panel.repaint();
                if(region!= null){
                    submit();
                }
            }
        });
    }
    private static void submit(){
        frame.dispose();
        JLabel label = new JLabel("Waiting for match to start.....");
        label.setFont(new Font("Georgia", Font.BOLD, 20));
        frame = new JFrame("Waiting for match...");
        frame.setUndecorated(true);
        frame.setSize(500,500);
        frame.setLocation(1030, 290);
        frame.repaint();
        panel.add(label);
        JButton button = new JButton("Start!");

        c.gridx = 2;
        c.gridy = 1;
        panel.add(button, c);


        frame.add(panel);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if(username != null && region != null){
                    SummonerInformation.summonerDisplay(username, region);
                    try {
                        ItemInformation itemInfo = new ItemInformation();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
