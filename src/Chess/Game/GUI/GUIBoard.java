package Chess.Game.GUI;

import Chess.Bot.SimpleSearcher;
import Chess.Game.Game;
import Chess.Game.Move;
import Chess.Pieces.Piece;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Chess.Game.Board.Color;

//TODO images from https://marcelk.net/chess/pieces/ put in readme
public class GUIBoard extends Application{
    public static void main(String[] args){
        launch();
    }
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private Parent createChessBoard(Piece[][] board){
        Pane parent = new Pane();
        parent.setPrefSize(WIDTH,HEIGHT);
        boolean isWhite = false;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Tile tile = new Tile(isWhite,i,j);
                parent.getChildren().add(tile);
                isWhite = !isWhite;
                if(board[j][i] != null){
                    String pieceString = board[j][i].color == Color.WHITE ? board[j][i].toString() + "w" : board[j][i].toString();
                    Image pieceImage = new Image("/Chess/Game/GUI/Pictures/" + pieceString + ".png");
                    ImageView piece = new ImageView(pieceImage);
                    piece.setFitHeight(100);
                    piece.setFitWidth(100);
                    piece.relocate(i * 100,j * 100);
                    parent.getChildren().add(piece);
                }

            }
            isWhite = !isWhite;
        }
        return parent;
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("Chess");
        Game game = new Game();
        primaryStage.show();
        AnimationTimer timer = new AnimationTimer(){
            private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                if(lastUpdate != 0){
                    this.stop();
                }
                if(game.isGameOver()){
                    this.stop();
                }
                if (now - lastUpdate >= 1000000000 && !game.isGameOver()) {
                    Piece[][] board = game.getBoard();
                    Scene scene = new Scene(createChessBoard(board));
                    primaryStage.setScene(scene);
                    Move move = SimpleSearcher.findBestMove(game,1);
                    game.makeMove(move);
                    lastUpdate = now ;
                }
            }
        };
        timer.start();


    }
}
