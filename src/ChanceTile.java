import javax.swing.*;
import java.awt.*;

/**
 * Created by Artemy on 08.01.2018.
 */
public class ChanceTile extends Tile {

    public ChanceTile(String title, String image, TileType tileType, int xPosition, int yPosition) {
        super(title, image, tileType, xPosition, yPosition);
        this.initTile();
        this.initTileContent(image);
    }


    @Override
    public void initTileContent(String image) {
        backgroundLabel.setBounds(1, 1, 68, 88);
        //JLabel label = new JLabel(title);
        descriptionLayer.setLayout(new FlowLayout());
        descriptionLayer.setBounds(1, 1, 68, 34);
        descriptionLayer.setOpaque(false);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 12));
        titleLabel.setBorder(BorderFactory.createEmptyBorder( -3 /*top*/, 0, 0, 0 ));
        if (tileType == TileType.PRIZE) {
            titleLabel.setForeground(Color.YELLOW);
        } else {
            titleLabel.setForeground(Color.BLUE);
        }
        descriptionLayer.add(titleLabel);
        this.add(descriptionLayer, new Integer(1));
    }



    @Override
    public String useAbility(Player player, MainPanel mainPanel, Monitor monitor) {
        mainPanel.getResultLabel().setText("Выпал щанс. Вы готовы испытать свою удачу?");
        mainPanel.setChoicePanel("Испытать удачу", "В другой раз");
        synchronized (monitor) {
            System.out.println("monitor startet");
            try {
                monitor.wait();
            }catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        System.out.println("monitor ended");
        if (monitor.getResult()) {
            System.out.println("YYYYYYYYYYYYYYYYYYYYYEEEEEEEEEEESSSSS");

            player.getScoreInfo().setText(String.valueOf(player.getScore()));
            player.refreshPropertyInfo();
        }

        return "Выпал щанс. Вы готовы испытать свою удачу?";
    }

    public void tryLuck() {

    }

}
