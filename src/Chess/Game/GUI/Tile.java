package Chess.Game.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(boolean isWhite,int x, int y) {
        setWidth(GUIBoard.WIDTH / 8);
        setHeight(GUIBoard.HEIGHT / 8);
        relocate(x * (GUIBoard.WIDTH / 8), y * (GUIBoard.WIDTH / 8));
        setFill(isWhite ? Color.valueOf("White") : Color.valueOf("Green"));
    }
}
