import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Artemy on 08.01.2018.
 */
public class MainPanel extends JLayeredPane{
    private static MainPanel mainPanel = null;
    JLabel backgroundLabel = null;
    private JLabel playerName = null;
    private JLabel playerNameLabel = null;
    private JPanel elements = null;
    private JLabel throwDimeLabel = null;
    private JPanel throwDime = null;
    private JPanel alignmentPanel = null;
    private JPanel choicePanel = null;
    private JLabel dime1Label = null;
    private JLabel dime2Label = null;
    private JLabel dime3Label = null;
    private JLabel resultLabel = null;
    private JButton yesButton = null;
    private JButton noButton = null;
    private boolean choiseResult;
    private Monitor monitor;

    ImageIcon img;

    ArrayList<ImageIcon> dimes;
    ImageIcon currentDime1;
    ImageIcon currentDime2;

    private MainPanel(String image, Monitor monitor) {

        backgroundLabel = new JLabel();
        this.img = new ImageIcon(image);
        this.backgroundLabel.setIcon(img);
        initSecondLayer();
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(backgroundLabel, new Integer(0));
        setPreferredSize(new Dimension(700, 420));
        backgroundLabel.setBounds(1, 1, 698, 418);
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        this.add(alignmentPanel, new Integer(1));
        this.monitor = monitor;
    }

    private void initDimes() {
        dimes = new ArrayList<>();
        dimes.add(new ImageIcon("img\\1.jpg"));
        dimes.add(new ImageIcon("img\\2.jpg"));
        dimes.add(new ImageIcon("img\\3.jpg"));
        dimes.add(new ImageIcon("img\\4.jpg"));
        dimes.add(new ImageIcon("img\\5.jpg"));
        dimes.add(new ImageIcon("img\\6.jpg"));
    }

    public static MainPanel initMainPanel(String img, Monitor monitor) {
        if (mainPanel == null) {
            mainPanel = new MainPanel(img, monitor);
        }
        return mainPanel;
    }

    public JLabel getPlayerName() {
        return playerName;
    }

    public JPanel getThrowDime() {
        return throwDime;
    }

    public void setDimes(int dime1, int dime2) {
        currentDime1 = dimes.get(dime1 -1);
        currentDime2 = dimes.get(dime2 -1);
        dime1Label.setIcon(currentDime1);
        dime2Label.setIcon(currentDime2);
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }


    public boolean setChoicePanel(String yesChoice, String noChoice) {
        yesButton.setText(yesChoice);
        noButton.setText(noChoice);

        choicePanel.add(yesButton);
        choicePanel.add(noButton);
        choicePanel.setVisible(true);

        System.out.println("setChoisepanel ended");
      //  choicePanel.setVisible(false);
        return true;
    }

    public JPanel getChoicePanel() {
        return choicePanel;
    }

    private void initSecondLayer() {
        elements = new JPanel();
        elements.setLayout(new GridBagLayout());
        alignmentPanel = new JPanel();
        choicePanel = new JPanel();
        choicePanel.setOpaque(false);
        alignmentPanel.setLayout(new FlowLayout());
        alignmentPanel.setOpaque(false);
        yesButton = new JButton("Да!");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choiseResult = true;
                monitor.setResult(true);
                choicePanel.removeAll();
                choicePanel.setVisible(false);
                synchronized (monitor) {
                    System.out.println("Yes selected");
                    monitor.notifyAll();
                }
            }
        });
        noButton = new JButton("Нет");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choiseResult = false;
                monitor.setResult(false);
             //   choicePanel.removeAll();
                choicePanel.setVisible(false);
                synchronized (monitor) {
                    System.out.println("No Selected");
                    monitor.notifyAll();
                }
            }
        });
        //choicePanel.add(noButton);
        //choicePanel.add(yesButton);
        alignmentPanel.setBounds(10, 1, 680, 400);
        playerName = new JLabel();
        dime1Label = new JLabel();
        dime2Label = new JLabel();
        dime3Label = new JLabel();
        resultLabel = new JLabel();

        initDimes();
        dime1Label.setIcon(currentDime1);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 28));
        dime2Label.setIcon(currentDime2);
        dime3Label.setPreferredSize(new Dimension(30, 30));
        dime3Label.setOpaque(false);
        playerNameLabel = new JLabel("Ход игрока: ");

        throwDime = new JPanel();
        throwDime.setLayout(new GridLayout(1, 3));
        throwDime.setOpaque(false);
        throwDime.add(dime1Label);
        throwDime.add(dime3Label);
        throwDime.add(dime2Label);
        throwDimeLabel = new JLabel("Бросаем кости: ");
        elements.setBounds(0,0,698, 418);
        elements.setOpaque(false);

        playerNameLabel.setFont(new Font("Serif", Font.BOLD, 28));
        throwDimeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        playerName.setFont(new Font("Serif", Font.BOLD, 28));

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        elements.add(playerNameLabel, c);
        c.gridx = 1;
        elements.add(playerName, c);
        c.gridx = 0;
        c.gridy = 1;
        elements.add(throwDimeLabel, c);
        c.gridx = 1;
        elements.add(throwDime, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        elements.add(resultLabel, c);
        c.gridy = 3;
        elements.add(choicePanel, c);
        alignmentPanel.add(elements);
    }
}
