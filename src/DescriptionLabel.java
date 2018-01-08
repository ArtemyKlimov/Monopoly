import javax.swing.*;
import java.awt.*;

/**
 * Created by Artemy on 08.01.2018.
 */
public class DescriptionLabel extends JLabel {
    public DescriptionLabel(String title, TileType tileType, Color background, int horizontalAlignment) {
        super(title);
        setBackground(background);
        setOpaque(true);
        if ((tileType == TileType.PROPERTY_HORIZONTAL_TOP) || (tileType == TileType.PROPERTY_HORIZONTAL_BOTTOM)) {
            setPreferredSize(new Dimension(34, 9));
        } else {
            setPreferredSize(new Dimension(34, 11));
        }
        setBorder(BorderFactory.createEmptyBorder( -3 /*top*/, 0, 0, 0 ));
        setFont(new Font("Serif", Font.BOLD, 11));
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(SwingConstants.CENTER);
       // setAlignmentX(SwingConstants.RIGHT);
    }
}
