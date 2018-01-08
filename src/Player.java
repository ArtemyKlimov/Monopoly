import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Artemy on 06.01.2018.
 */
public class Player {
    private String name = null;
    private int score;
    private Color color = null;
    private int location;
    private JLabel playerLabel = null;
    private ArrayList<Tile> properties;
    private JLabel scoreInfo = null;
    private JPanel propertyInfo = null;

    public void setPropertyInfo(JPanel propertyInfo) {
        this.propertyInfo = propertyInfo;
    }

    public void refreshPropertyInfo() {
        propertyInfo.removeAll();
        for (Tile tile : this.getProperties()) {
            JLabel tmp = new JLabel();
            ImageIcon ii = new ImageIcon(tile.getIcon().getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            tmp.setIcon(ii);
            propertyInfo.add(tmp);
        }
        propertyInfo.revalidate();
        propertyInfo.repaint();
    }

    public JPanel getPropertyInfo() {

        return propertyInfo;
    }



    public ArrayList<Tile> getProperties() {
        return properties;
    }

    public void addPropery(Tile tile) {
        this.properties.add(tile);
    }

    public void setScoreInfo(JLabel scoreInfo) {
        this.scoreInfo = scoreInfo;
    }

    public JLabel getScoreInfo() {
        return scoreInfo;
    }

    private int playerNum;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    private AbstractList<Tile> assets = null;

    public JLabel getPlayerLabel() {
        return playerLabel;
    }


    public int getPlayerNum() {
        return playerNum;
    }

    public Player(String name, Color color, JLabel playerLabel, int location, int playerNum) {
        this.name = name;
        properties = new ArrayList<>();
        this.playerLabel = playerLabel;
        this.score = 15000;
        this.color = color;
        this.location = location;
        this.assets = new ArrayList<>();
        this.playerNum = playerNum;
    }


    public void addScore(int score) {
        this.score += score;
    }

    public String getName() {

        return name;
    }

    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public AbstractList<Tile> getAssets() {
        return assets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        return color != null ? color.equals(player.color) : player.color == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
