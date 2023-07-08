import javax.swing.*;
import java.awt.*;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class SummonerInformation {
    public static void summonerDisplay(String inputName, Region region){
        Orianna.setRiotAPIKey("RGAPI-21a2c2ff-58d6-4ee6-bd56-ef609506e3e2");
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);
        final Summoner summoner = Summoner.named(inputName).withRegion(region).get();

        //Initial setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JFrame frame= new JFrame("Summoner overlay");
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);

        //JLabel setup
        JLabel sumName, mapName, gameParticipants, alignment, sumRank;

        sumName= new JLabel("Welcome, " + summoner.getName() + "!");
        sumName.setFont(new Font("Georgia", Font.BOLD, 20));
        sumName.setForeground(Color.WHITE);
        c.gridx = GridBagConstraints.CENTER;
        c.gridy = 0;
        panel.add(sumName, c);
        System.out.println(summoner.getId());

        final CurrentMatch currentGame = summoner.getCurrentMatch();
        mapName = getMap(currentGame);
        mapName.setFont(new Font("Georgia", Font.BOLD, 12));
        mapName.setForeground(Color.white);
        c.gridx = GridBagConstraints.CENTER;
        c.gridy = 1;
        panel.add(mapName, c);

        int counter = 0;
        boolean allyHit = false;
        boolean enemyHit = false;

        for(final Player player: currentGame.getParticipants()){
            if(counter == 0 && !allyHit){
                alignment = new JLabel("Blue Team: ");
                alignment.setForeground(Color.blue);
                alignment.setFont(new Font("Georgia", Font.BOLD, 15));
                c.anchor = GridBagConstraints.CENTER;
                c.gridy = 2;
                panel.add(alignment, c);
                allyHit = true;
                counter++;
            }
            else if(!enemyHit && counter > 5){
                alignment = new JLabel("Red Team: ");
                alignment.setForeground(Color.RED);
                alignment.setFont(new Font("Georgia", Font.BOLD, 15));
                c.anchor = GridBagConstraints.CENTER;
                c.gridy = 2+counter;
                panel.add(alignment, c);
                enemyHit = true;
                counter++;
            }

            gameParticipants = new JLabel(player.getSummoner().getName());
            sumRank = getRank(player);
            if(summoner.getName() == player.getSummoner().getName()){
                gameParticipants.setFont(new Font("Georgia", Font.ITALIC, 12));
                sumRank.setFont(new Font("Georgia", Font.ITALIC, 12));
            }
            else{
                gameParticipants.setFont(new Font("Georgia", Font.PLAIN, 12));
                sumRank.setFont(new Font("Georgia", Font.PLAIN, 12));
            }


            sumRank.setForeground(Color.CYAN);
            c.anchor = GridBagConstraints.LINE_START;
            c.gridy = 2+counter;
            panel.add(gameParticipants, c);
            c.anchor = GridBagConstraints.LINE_END;
            panel.add(sumRank, c);

            if(counter<6){
                gameParticipants.setForeground(Color.blue);
            }
            else{
                gameParticipants.setForeground(Color.red);
            }
            counter++;
        }

        panel.setBackground(new Color(0,0,0,0));

        //Frame adds
        frame.add(panel);


        //Frame settings
        frame.setSize(300,300);
        frame.setLocation(2250, 500);
        frame.setBackground(new Color(0,0,0,0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    //Helper method, formats map into a JLabel
    private static JLabel getMap(CurrentMatch game){
        JLabel mapName;
        //Format the API map name nicely
        if(game.getMap() == Map.SUMMONERS_RIFT){
            return mapName = new JLabel("Currently playing on: Summoner's Rift");
        }
        else if(game.getMap() == Map.HOWLING_ABYSS){
            return mapName = new JLabel("Currently playing on: Howling Abyss (ARAM)");
        }
        return mapName = new JLabel("Not a normal map, here's what the API says it is: " + game.getMap());
    }
    //Helper method, formats rank into a JLabel
    private static JLabel getRank(Player player){
        JLabel rank;
        try {
            switch (player.getSummoner().getLeague(Queue.RANKED_SOLO).getTier()) {
                case IRON:
                    return rank = new JLabel("Rank: Iron");
                case BRONZE:
                    return rank = new JLabel("Rank: Bronze");
                case SILVER:
                    return rank = new JLabel("Rank: Silver");
                case GOLD:
                    return rank = new JLabel("Rank: Gold");
                case PLATINUM:
                    return rank = new JLabel("Rank: Platinum");
                case DIAMOND:
                    return rank = new JLabel("Rank: Diamond");
                case GRANDMASTER:
                    return rank = new JLabel("Rank: Grandmaster");
                case CHALLENGER:
                    return rank = new JLabel("Rank: Challenger");
            }
        }
        catch(NullPointerException e){

        }
         return rank = new JLabel("Rank: Unranked");
    }
}
