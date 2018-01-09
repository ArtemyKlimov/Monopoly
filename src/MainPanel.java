import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Artemy on 08.01.2018.
 */
public class MainPanel extends JLayeredPane {
  //  private MainPanel mainPanel = null;
    private JLabel backgroundLabel = null;
    private JLabel playerName = null;
    private JLabel playerNameLabel = null;
    private JPanel elements = null;
    private JLabel throwDimeLabel = null;
    private JPanel throwDime = null;
    private JLabel dime1Label = null;
    private JLabel dime2Label = null;
    ImageIcon icon;

    ArrayList<ImageIcon> dimes;
    ImageIcon currentDime1;
    ImageIcon currentDime2;

    public MainPanel(String img) {

        backgroundLabel = new JLabel();

        icon = new ImageIcon(img);
        backgroundLabel.setIcon(icon);
        //backgroundLabel.setOpaque(false);
        System.out.println("Constructor called " + img);
      //  initSecondLayer();
        this.add(backgroundLabel, new Integer(3));
        backgroundLabel.setBounds(1, 1, 698, 418);
        //   this.setIcon(icon);
     //   this.add(elements, new Integer(1));
    }

    private void initDimes() {
        dimes = new ArrayList<>();
        dimes.add(new ImageIcon(""));
        dimes.add(new ImageIcon(""));
        dimes.add(new ImageIcon(""));
        dimes.add(new ImageIcon(""));
        dimes.add(new ImageIcon(""));
        dimes.add(new ImageIcon(""));
    }
/*
    public static MainPanel initMainPanel(String img) {
        if (mainPanel == null) {
            mainPanel = new MainPanel(img);
        }
        return mainPanel;
    }
*/
    public JLabel getPlayerName() {
        return playerName;
    }

    public JPanel getThrowDime() {
        return throwDime;
    }

    public void setDimes(int dime1, int dime2) {
        currentDime1 = dimes.get(dime1 -1);
        currentDime2 = dimes.get(dime2 -1);
    }

    private void initSecondLayer() {
        elements = new JPanel();
        playerName = new JLabel();
        dime1Label = new JLabel();
        dime2Label = new JLabel();
        initDimes();
        setDimes(1, 1);
        dime1Label.setIcon(currentDime1);
        dime1Label.setIcon(currentDime2);
        playerNameLabel = new JLabel("Делает ход игрок ");
        throwDime = new JPanel();
        throwDime.setLayout(new GridLayout(1, 2));
        throwDimeLabel = new JLabel("Бросаем кости: ");
        elements.setBounds(0,0,698, 418);
        elements.setOpaque(false);
        elements.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        elements.add(playerNameLabel, c);
        c.gridx = 1;
        elements.add(playerName, c);

    }
}
