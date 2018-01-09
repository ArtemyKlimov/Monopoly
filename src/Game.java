import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Artemy on 06.01.2018.
 */
public class Game extends JFrame {
    JPanel mainPanel = null;
    JPanel infoPanel = null;
    JTextField dice1 = null;
    JTextField dice2 = null;
    JButton nextTurn = null;
    JPanel gamePanel = null;
    JLabel playerName = null;
    JPanel throwDime = null;
    volatile ArrayList<Tile> tiles;
    ArrayList<Player> players;
    static Game game;
    Player currentPlayer;

    public static void main(String[] args) throws Exception{
        JLabel playerOneLabel = new JLabel();
        JLabel playerTwoLabel = new JLabel();
        JLabel playerThreeLabel = new JLabel();
        playerOneLabel.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        playerTwoLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        playerThreeLabel.setIcon(UIManager.getIcon("OptionPane.warningIcon"));

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Oleg", Color.RED, playerOneLabel, 0, 1));
        playerList.add(new Player("Dima", Color.YELLOW, playerTwoLabel, 0, 2));
        playerList.add(new Player("Miha", Color.GREEN, playerThreeLabel, 0, 3));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    game = new Game(playerList);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Game(ArrayList<Player> players) throws Exception{
        super("Monopoly Game");
        mainPanel = new JPanel();
        currentPlayer = players.get(0);
        this.add(mainPanel);
        this.players = players;
        setSize(1070, 680);

        setLocationRelativeTo(null);
       // setResizable(false);
        gamePanel = new JPanel();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(getGameField(), BorderLayout.WEST);
        mainPanel.add(getInfoField(), BorderLayout.EAST);
        mainPanel.add(getScoreField(), BorderLayout.SOUTH);

        setVisible(true);
        drawPlayers();
    }

    public void getNextPlayer() {
        if (currentPlayer.getPlayerNum() == players.size() )
            currentPlayer = players.get(0);
        else
            currentPlayer = players.get(currentPlayer.getPlayerNum());
    }

    public int throwDice(){
        long t = System.currentTimeMillis();
        long end = t+3000;
        dice1 = new JTextField();
        dice2 = new JTextField();

    //    while (System.currentTimeMillis() < end) {

            Random r = new Random();
            int num1 = r.nextInt(6) + 1;
            int num2  =r.nextInt(6) + 1;

            Tile tile1 = tiles.get(tiles.size()-2);
            Tile tile2 = tiles.get(tiles.size()-1);
            tile1.setText(String.valueOf(num1));
            tile2.setText(String.valueOf(num2));
  //      }
        return num1 + num2;
    }

    public void movePlayer(Player player, int steps) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < steps; i++) {
                    try {
                        Thread.sleep(200);
                    }catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    int currentTile = player.getLocation();
                    int nextTile = currentTile + 1;
                    if (nextTile == 36) {
                        nextTile = 0;
                    }
                    tiles.get(nextTile).addPlayer(player.getPlayerLabel());
                    player.setLocation(nextTile);
                 //   System.out.println("Player " + player.getName() + "location: " + player.getLocation());
                    game.repaint();
                    game.revalidate();
                }

                tiles.get(player.getLocation()).useAbility(player);
                tiles.get(player.getLocation()).setOwner(player);
                player.getScoreInfo().setText(String.valueOf(player.getScore()));
                player.addPropery(tiles.get(player.getLocation()));
                player.refreshPropertyInfo();
                playerName.repaint();
                playerName.revalidate();
            }
        }).start();

    }

    public void drawPlayers() throws Exception{
        for (Player player : players) {
            tiles.get(0).addPlayer(player.getPlayerLabel());
            tiles.get(0).repaint();
            tiles.get(0).revalidate();
        }
    }

    public JPanel getGameField() throws Exception{
        GridBagConstraints c = new GridBagConstraints();
        gamePanel.setLayout(new GridBagLayout());
        tiles = new ArrayList<>();
        initTiles();
        for (Tile t : tiles) {
            c.gridx = t.getXPosition();
            c.gridy = t.getYPosition();
            gamePanel.add(t, c);
        }
        c.gridx = 0;
        c.gridy = 0;
        MainPanel mainFrame = new MainPanel("img\\mnpl.jpg");
      //  MainPanel JLabel = new JLabel();
      //  gamePanel.add(mainFrame, c);
      //  ImageIcon icon = new ImageIcon("img\\mnpl.jpg");
      //  mainFrame.setIcon(icon);
        playerName = mainFrame.getPlayerName();
        throwDime = mainFrame.getThrowDime();
//        c.gridx = 1;
//        c.gridy = 1;
//        c.gridwidth = 10;
//        c.gridheight = 6;
        gamePanel.add(mainFrame, c);
        return gamePanel;
    }

    public void initTiles() throws Exception{
        tiles.add(new StartTile("Start", null, TileType.START_TILE, 0, 0));
        tiles.add(new ChanceTile("Приз 1500", null, TileType.PROPERTY_HORIZONTAL_TOP, 1, 0));
        tiles.add(new PropertyTile("adidas", "img\\adidas-logos2.jpg" , TileType.PROPERTY_HORIZONTAL_TOP, null, 2, 0, 500, 150, 300, 600));
        tiles.add(new PropertyTile("puma", null, TileType.PROPERTY_HORIZONTAL_TOP, null, 3, 0, 700, 200, 350, 700));
        tiles.add(new PropertyTile("Lacoste", "img\\Lacoste2.jpg", TileType.PROPERTY_HORIZONTAL_TOP, null, 4, 0, 900, 250, 400, 800));
        tiles.add(new ChanceTile("ШАНС", "img\\chance2.jpg", TileType.CHANCE, 5, 0));
        tiles.add(new InspectionTile("<html>Штраф 100<br>с филиала</html>", "img\\fns3.jpg", TileType.INSPECTION, 6, 0));
        tiles.add(new PropertyTile("АвтоВАЗ", "img\\VAZ2.jpg", TileType.PROPERTY_HORIZONTAL_TOP, null, 7, 0, 1000, 250, 500, 900));
        tiles.add(new PropertyTile("WV", "img\\vw.jpg", TileType.PROPERTY_HORIZONTAL_TOP, null, 8, 0, 1200, 250, 500, 1000));
        tiles.add(new PropertyTile("BMW", "img\\bmw.jpg", TileType.PROPERTY_HORIZONTAL_TOP, null, 9, 0, 1500, 300, 600, 1300));
        tiles.add(new ChanceTile("<html>Доход<br>2000</html>", "img\\Income.jpg", TileType.INCOME, 10, 0));
        tiles.add(new StartTile("Биржа", "img\\VAZ2.jpg", TileType.START_TILE, 11, 0));
        tiles.add(new PropertyTile("Хилтон", "img\\VAZ2.jpg", TileType.PROPERTY_VERTICAL_RIGHT, null, 11, 1, 1800, 350, 700, 1400));
        tiles.add(new PropertyTile("Марриотт", "img\\Marriott.jpg", TileType.PROPERTY_VERTICAL_RIGHT, null, 11, 2, 2000, 350, 700, 1400));
        tiles.add(new PropertyTile("Метрополь", "img\\VAZ2.jpg", TileType.PROPERTY_VERTICAL_RIGHT, null, 11, 3, 2300, 400, 800, 1600));
        tiles.add(new PropertyTile("Мегафон", "img\\VAZ2.jpg", TileType.PROPERTY_VERTICAL_RIGHT, null, 11, 4, 2500, 400, 800, 1800));
        tiles.add(new PropertyTile("МТС", "img\\VAZ2.jpg", TileType.PROPERTY_VERTICAL_RIGHT, null, 11, 5, 2800, 450, 900, 1900));
        tiles.add(new PropertyTile("Билайн", "img\\VAZ2.jpg", TileType.PROPERTY_VERTICAL_RIGHT, null, 11, 6, 3100, 500, 1000, 2000));
        tiles.add(new StartTile("<html>Налоговая<br>инспекция<br>Штраф 1500</html>", null, TileType.START_TILE, 11, 7));
        tiles.add(new ChanceTile("ШАНС", "img\\chance2.jpg", TileType.CHANCE, 10, 7));
        tiles.add(new PropertyTile("Молокозавод", "img\\VAZ2.jpg", TileType.PROPERTY_HORIZONTAL_BOTTOM, null, 9, 7, 3300, 600, 1100, 2100));
        tiles.add(new PropertyTile("Мясокомбинат", null, TileType.PROPERTY_HORIZONTAL_BOTTOM, null, 8, 7, 3500, 650, 1200, 2200));
        tiles.add(new PropertyTile("Хлебокомбинат", null, TileType.PROPERTY_HORIZONTAL_BOTTOM, null, 7, 7, 3700, 700, 1300, 2300));
        tiles.add(new InspectionTile("<html>Штраф 100<br>с филиала</html>", "img\\fns3.jpg", TileType.INSPECTION, 6, 7));
        tiles.add(new ChanceTile("<html>Доход<br>5000</html>", "img\\Income.jpg", TileType.INCOME, 5, 7));
        tiles.add(new PropertyTile("Реал", null, TileType.PROPERTY_HORIZONTAL_BOTTOM, null, 4, 7, 4000, 750, 1400, 2400));
        tiles.add(new PropertyTile("Милан", null, TileType.PROPERTY_HORIZONTAL_BOTTOM, null, 3, 7, 4200, 800, 1450, 2500));
        tiles.add(new PropertyTile("Челси", null, TileType.PROPERTY_HORIZONTAL_BOTTOM, null, 2, 7, 4300, 850, 1500, 2600));
        tiles.add(new ChanceTile("ШАНС", "img\\chance2.jpg", TileType.CHANCE, 1, 7));
        tiles.add(new StartTile("Тюрьма", null, TileType.START_TILE, 0, 7));
        tiles.add(new PropertyTile("Панам", null, TileType.PROPERTY_VERTICAL_LEFT, null, 0, 6, 4500, 900, 1550, 2700));
        tiles.add(new PropertyTile("S7 Airlines", null, TileType.PROPERTY_VERTICAL_LEFT, null, 0, 5, 4700, 950, 1600, 2750));
        tiles.add(new PropertyTile("Аэрофлот", null, TileType.PROPERTY_VERTICAL_LEFT, null, 0, 4, 4800, 1000, 1700, 2800));
        tiles.add(new PropertyTile("Лукойл", null, TileType.PROPERTY_VERTICAL_LEFT, null, 0, 3, 5000, 1050, 1750, 2850));
        tiles.add(new PropertyTile("Роснефть", null, TileType.PROPERTY_VERTICAL_LEFT, null, 0, 2, 5200, 1100, 1800, 2900));
        tiles.add(new PropertyTile("Газпром", "img\\gazprom2.jpg", TileType.PROPERTY_VERTICAL_LEFT, null, 0, 1, 5500, 1150, 1900, 3000));
      //  tiles.add(new ChanceTile("0", null, TileType.DICE_TILE, 5, 2));
      //  tiles.add(new ChanceTile("0", null, TileType.DICE_TILE, 5, 3));
        System.out.println("Size is: " + tiles.size());
    }

    public JPanel getInfoField() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4,1));
        for (Player p : players) {
            JPanel jl = getPlayerInfoField(p);
            infoPanel.add(jl);
        }
        return infoPanel;
    }

    public JPanel getPlayerInfoField(Player player) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(player.getColor(), 2));
       // panel.setBackground(new Color(215,215,215));
        GridBagConstraints c = new GridBagConstraints();
        JLabel playerName = new JLabel(player.getName());
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 2;
        panel.add(playerName, c);
        JLabel playerScoreLabel = new JLabel("Средства: ");
        JLabel playerScoreSum = new JLabel(String.valueOf(player.getScore()));
        player.setScoreInfo(playerScoreSum);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        panel.add(playerScoreLabel, c);
        c.gridx = 1;
        panel.add(playerScoreSum, c);
        JLabel playerPropertyLabel = new JLabel("Имущество: ");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(playerPropertyLabel, c);
        JPanel playerProperty = new JPanel();
        player.setPropertyInfo(playerProperty);
        playerProperty.setLayout(new FlowLayout());

        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 2;
        panel.add(playerProperty, c);
        return panel;
    }

    public JPanel getScoreField() {
        JPanel scorePanel = new JPanel();
        nextTurn = new JButton("Next Turn");
        nextTurn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int steps = throwDice();
                movePlayer(currentPlayer, steps);
                getNextPlayer();
                System.out.println("Current player is "+ currentPlayer.getName()
                        + " Location is: " + currentPlayer.getLocation()
                        + " Property is: "
                );
                for (Tile tile : currentPlayer.getProperties()) {
                    System.out.print(tile.getTitle());
                }
                System.out.print(" Средства: "+ currentPlayer.getScore());
            }
        });
        scorePanel.add(nextTurn);
        scorePanel.setBackground(Color.YELLOW);
        return scorePanel;
    }
}
