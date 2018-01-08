import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Artemy on 07.01.2018.
 */
public class PropertyTile extends Tile {
    int price;
    private Player owner;
    protected Industry industry;
    int rent_lvl_1;
    int rent_lvl_2;
    int rent_lvl_3;

    public PropertyTile(String title,
                        String image,
                        TileType tileType,
                        Industry industry,
                        int xPosition,
                        int yPosition,
                        int price,
                        int rent_lvl_1,
                        int rent_lvl_2,
                        int rent_lvl_3) {
        super(title, image, tileType, xPosition, yPosition);
        this.industry = industry;
        this.price = price;
        this.rent_lvl_1 = rent_lvl_1;
        this.rent_lvl_2 = rent_lvl_2;
        this.rent_lvl_3 = rent_lvl_3;
        this.tileType = tileType;
        initTile();
        initTileContent(image);
    }

    public void useAbility(Player player) {
        if ((this.owner != null) && (!player.equals(this.owner))) {
            player.addScore(rent_lvl_1 * (-1));
            System.out.println("Вы попали клетку другого игрока. Штраф " +  rent_lvl_1 * (-1));
        } else {
            player.addScore(rent_lvl_2 * (-1));
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        Border border = BorderFactory.createLineBorder(player.getColor(), 2);
        this.setBorder(border);
    }

    @Override
    public void initTileContent(String image) {
        if (tileType == TileType.PROPERTY_HORIZONTAL_TOP) {
            backgroundLabel.setBounds(1, 2, 68, 86);
            descriptionLayer.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridy = 1;
            c.weighty = 1;
            JLabel priceLabel = new DescriptionLabel(String.valueOf(price), tileType, Color.RED, SwingConstants.CENTER);
            priceLabel.setForeground(Color.WHITE);
            JLabel ren1Label = new DescriptionLabel(String.valueOf(rent_lvl_1), tileType, Color.YELLOW, SwingConstants.RIGHT);
            JLabel ren1Label2 = new DescriptionLabel(String.valueOf(rent_lvl_2), tileType, Color.PINK, SwingConstants.RIGHT);
            JLabel ren1Label3 = new DescriptionLabel(String.valueOf(rent_lvl_3), tileType, Color.ORANGE, SwingConstants.RIGHT);
            descriptionLayer.add(priceLabel, c);
            c.gridy = 2;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridx = 1;
            c.gridy = 0;
            c.weighty = 1;
            descriptionLayer.add(ren1Label, c);
            c.gridy = 1;
            descriptionLayer.add(ren1Label2, c);
            c.gridy = 2;
            descriptionLayer.add(ren1Label3, c);
            descriptionLayer.setBounds(1, 62, 68, 27);
            descriptionLayer.setOpaque(false);
            this.add(descriptionLayer, new Integer(1));
        } else if (tileType == TileType.PROPERTY_HORIZONTAL_BOTTOM) {
            backgroundLabel.setBounds(1, 29, 68, 60);
            descriptionLayer.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridy = 1;
            c.weighty = 1;
            JLabel priceLabel = new DescriptionLabel(String.valueOf(price), tileType, Color.RED, SwingConstants.CENTER);
            priceLabel.setForeground(Color.WHITE);
            JLabel ren1Label = new DescriptionLabel(String.valueOf(rent_lvl_1), tileType, Color.YELLOW, SwingConstants.RIGHT);
            JLabel ren1Label2 = new DescriptionLabel(String.valueOf(rent_lvl_2), tileType, Color.PINK, SwingConstants.RIGHT);
            JLabel ren1Label3 = new DescriptionLabel(String.valueOf(rent_lvl_3), tileType, Color.ORANGE, SwingConstants.RIGHT);
            descriptionLayer.add(priceLabel, c);
            c.gridy = 2;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridx = 1;
            c.gridy = 0;
            c.weighty = 1;
            descriptionLayer.add(ren1Label, c);
            c.gridy = 1;
            descriptionLayer.add(ren1Label2, c);
            c.gridy = 2;
            descriptionLayer.add(ren1Label3, c);
            descriptionLayer.setBounds(1, 1, 68, 27);
            descriptionLayer.setOpaque(false);
            this.add(descriptionLayer, new Integer(1));
        } else if (tileType == TileType.PROPERTY_VERTICAL_RIGHT) {
            backgroundLabel.setBounds(30, 1, 60, 68);
            descriptionLayer.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridy = 1;
            JLabel priceLabel = new DescriptionLabel(String.valueOf(price), tileType, Color.RED, SwingConstants.CENTER);
            priceLabel.setForeground(Color.WHITE);
            JLabel ren1Label = new DescriptionLabel(String.valueOf(rent_lvl_1), tileType, Color.YELLOW, SwingConstants.RIGHT);
            JLabel ren1Label2 = new DescriptionLabel(String.valueOf(rent_lvl_2), tileType, Color.PINK, SwingConstants.RIGHT);
            JLabel ren1Label3 = new DescriptionLabel(String.valueOf(rent_lvl_3), tileType, Color.ORANGE, SwingConstants.RIGHT);
            descriptionLayer.add(priceLabel, c);
            c.gridy = 2;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridx = 0;
            c.gridy = 3;
            descriptionLayer.add(ren1Label, c);
            c.gridy = 4;
            descriptionLayer.add(ren1Label2, c);
            c.gridy = 5;
            descriptionLayer.add(ren1Label3, c);
            descriptionLayer.setBounds(1, 1, 34, 69);
            descriptionLayer.setOpaque(false);
            this.add(descriptionLayer, new Integer(1));
        } else {
            backgroundLabel.setBounds(1, 1, 54, 68);
            descriptionLayer.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridy = 1;
            JLabel priceLabel = new DescriptionLabel(String.valueOf(price), tileType, Color.RED, SwingConstants.CENTER);
            priceLabel.setForeground(Color.WHITE);
            JLabel ren1Label = new DescriptionLabel(String.valueOf(rent_lvl_1), tileType, Color.YELLOW, SwingConstants.RIGHT);
            JLabel ren1Label2 = new DescriptionLabel(String.valueOf(rent_lvl_2), tileType, Color.PINK, SwingConstants.RIGHT);
            JLabel ren1Label3 = new DescriptionLabel(String.valueOf(rent_lvl_3), tileType, Color.ORANGE, SwingConstants.RIGHT);
            descriptionLayer.add(priceLabel, c);
            c.gridy = 2;
            descriptionLayer.add(new DescriptionLabel("", tileType, Color.RED, SwingConstants.LEFT), c);
            c.gridx = 0;
            c.gridy = 3;
            descriptionLayer.add(ren1Label, c);
            c.gridy = 4;
            descriptionLayer.add(ren1Label2, c);
            c.gridy = 5;
            descriptionLayer.add(ren1Label3, c);
            descriptionLayer.setBounds(56, 1, 34, 69);

            descriptionLayer.setOpaque(false);
            this.add(descriptionLayer, new Integer(1));
        }

    }
}
