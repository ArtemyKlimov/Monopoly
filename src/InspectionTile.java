import javax.swing.*;
import java.awt.*;

/**
 * Created by Artemy on 08.01.2018.
 */
public class InspectionTile extends Tile {
    public InspectionTile(String title, String image, TileType tileType, int xPosition, int yPosition) {
        super(title, image, tileType, xPosition, yPosition);
        this.initTile();
        this.initTileContent(image);
    }

    @Override
    public String useAbility(Player player, MainPanel mainPanel, Monitor monitor) {
        mainPanel.getResultLabel().setText("Налоговая инспекция. Заплатите по 200 с филиала");
        int sum =  -1 * 200 * player.getProperties().size();
        player.addScore(sum);
        player.getScoreInfo().setText(String.valueOf(player.getScore()));
        player.refreshPropertyInfo();
        return null;
    }


    @Override
    public void initTileContent(String image) {
        backgroundLabel.setBounds(1, 1, 69, 89);
        //JLabel label = new JLabel(title);
        descriptionLayer.setLayout(new FlowLayout());
        descriptionLayer.setBounds(1, 1, 69, 34);
        descriptionLayer.setOpaque(false);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 11));
        titleLabel.setBorder(BorderFactory.createEmptyBorder( -3 /*top*/, 0, 0, 0 ));
        titleLabel.setForeground(Color.WHITE);
        descriptionLayer.add(titleLabel);
        this.add(descriptionLayer, new Integer(1));



    }
}
