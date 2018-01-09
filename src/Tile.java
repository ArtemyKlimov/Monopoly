import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Artemy on 06.01.2018.
 */
public abstract class Tile extends JLayeredPane {

    protected String title = null;
    protected JLabel backgroundLabel;
    protected JLabel titleLabel;
    protected JPanel descriptionLayer;
    protected JPanel playersLayer;
    protected TileType tileType;
    protected ImageIcon img;
    protected int xPosition;
    protected int yPosition;

    public Tile(String title, String image, TileType tileType, int xPosition, int yPosition) {
        titleLabel = new JLabel(title);
        backgroundLabel = new JLabel();
        playersLayer = new JPanel();
        descriptionLayer = new JPanel();
        playersLayer.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        this.title = title;
        this.tileType = tileType;
        this.img = new ImageIcon(image);
        this.backgroundLabel.setIcon(img);
        playersLayer.setBounds(0,0,69,89);
        playersLayer.setOpaque(false);
        this.add(backgroundLabel, new Integer(0));
        //this.add(titleLabel, new Integer(1));
        this.add(playersLayer, new Integer(2));

        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void addPlayer(JLabel playerLabel) {
        playersLayer.add(playerLabel);
    }

    public ImageIcon getIcon() {
        return img;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setText(String text) {
        this.title = text;
        this.titleLabel.setText(text);
    }

    public String getTitle() {
        return title;
    }

    protected void initTile() {
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        if ((tileType == TileType.PROPERTY_HORIZONTAL_TOP) || (tileType == TileType.PROPERTY_HORIZONTAL_BOTTOM)){
            setPreferredSize(new Dimension(70, 90));
        } else if ((tileType == TileType.PROPERTY_VERTICAL_LEFT) || (tileType == TileType.PROPERTY_VERTICAL_RIGHT)){
            setPreferredSize(new Dimension(91, 70));
        }else if (tileType == TileType.START_TILE) {
            setPreferredSize(new Dimension(91, 90));
        } else if (tileType == TileType.DICE_TILE) {
            setPreferredSize(new Dimension(70, 70));
        } else if ((tileType == TileType.CHANCE) || (tileType == TileType.INSPECTION) || (tileType == TileType.INCOME)){
            setPreferredSize(new Dimension(70, 90));
        } else {
            setPreferredSize(new Dimension(70, 90));
        }

        this.setBorder(border);
        //this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
    }

    public abstract void initTileContent(String image);
    public abstract void useAbility(Player player);
    public abstract void setOwner(Player player);
}
