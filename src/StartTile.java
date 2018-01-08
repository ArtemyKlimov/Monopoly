/**
 * Created by Artemy on 07.01.2018.
 */
public class StartTile extends Tile {

    public StartTile(String title, String image, TileType tileType, int xPosition, int yPosition) {
        super(title, image, tileType, xPosition, yPosition);
        this.tileType = tileType;
        initTile();
    }

    public void useAbility(Player player) {
        player.addScore(1500); //+1500 к балансу игрока
        player.addScore(player.getAssets().size() * -50); //-50 за каждый филиал.
    }

    @Override
    public void setOwner(Player player) {

    }

    @Override
    public void initTileContent(String image) {

    }
}
