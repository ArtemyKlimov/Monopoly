/**
 * Created by Artemy on 07.01.2018.
 */
public class StartTile extends Tile {

    public StartTile(String title, String image, TileType tileType, int xPosition, int yPosition) {
        super(title, image, tileType, xPosition, yPosition);
        this.tileType = tileType;
        initTile();
    }

    @Override
    public String useAbility(Player player, MainPanel mainPanel, Monitor monitor) {
        String result;
        if (tileType == TileType.START_TILE) {
            player.addScore(1500); //+1500 к балансу игрока
            player.addScore(player.getAssets().size() * -50); //-50 за каждый филиал.
        } else if (tileType == TileType.PRISON) {
            int fine;
            if (player.getProperties().size() == 0)
                fine = 100;
            else
                fine = player.getProperties().size() * 100;
            result =  "За налоговые махинации " + player.getName() + " попал под следствие";
            mainPanel.getResultLabel().setText(result);
            mainPanel.setChoicePanel("Откупиться за " + fine, "Отсидеть");
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

                player.addScore(fine * -1);
                player.getScoreInfo().setText(String.valueOf(player.getScore()));
                player.refreshPropertyInfo();

            } else {
                player.setInJail(true);
            }

        }
        return "";
    }

    @Override
    public void initTileContent(String image) {

    }
}
