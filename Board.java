import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Board{
    final Tile[][] board;
    /*
    *
    * The array temp is used to traverse around the mines for any 'a' in x=posx+temp[a] y=posy+temp[a+1],
    * it is an adjacent element to the mine.
    *
    * DO NOT CHANGE THE ORDER...
    *
    * Pretty proud, came up with it myself!
    *
     */
    private final static int[] temp={-1,0,-1,-1,1,1,0,1,-1};

    private final int xsize, ysize, mines;
    private int flagCount=0;
    Board(int xsize, int ysize, int mines){
        Random rnd=new Random();
        this.xsize = xsize;
        this.ysize = ysize;
        this.mines = mines;
        board=new Tile[xsize][ysize];
        //----------------------creating board-------------------------
        int count=0, tempx, tempy;
        for(tempx=0;tempx<xsize;tempx++)
            for(tempy=0;tempy<ysize;tempy++) {
                board[tempx][tempy] = new Tile(tempx, tempy, 0);
            }
        //-----------------------adding mines--------------------------
        while(count != mines){
            tempx=rnd.nextInt(xsize);
            tempy=rnd.nextInt(ysize);
            if(board[tempx][tempy].hasMine()) {
                continue;
            }
            count++;
            board[tempx][tempy].val=-1;
            //-----------updating tiles around the mines---------------
            for(int x=0; x<8; x++)
                if (isInRange(temp[x] + tempx, temp[x + 1] + tempy) && !board[tempx + temp[x]][tempy + temp[x + 1]].hasMine())
                    board[tempx + temp[x]][tempy + temp[x + 1]].val++;
        }
        //----------------------add action----------------------------
        for(int x=0; x<xsize; x++)
            for(int y=0; y<ysize; y++)
                addAction(board[x][y]);
    }

    private boolean isInRange(int i, int i1) {
        return i>=0 && i<xsize && i1>=0 && i1<ysize;
    }
    private void addAction(Tile tile){
        tile.setOnMouseClicked(e -> {
            if(e.getButton()== MouseButton.SECONDARY) {
                if(tile.flagged)
                    --flagCount;
                else
                    ++flagCount;
                tile.toggleFlag();
                if(flagCount==mines && checkWinByFlags())
                    gameEnd();
            }
        });
        if(tile.isEmpty())
            tile.setOnAction(e -> recurse(tile));
        else if(tile.hasMine())
            tile.setOnAction(e -> gameEnd());               //Map your tasks to be executed after loosing here 
        else
            tile.setOnAction(e->tile.open());
    }

    private boolean checkWinByFlags() {
        for(int x=0; x<xsize; x++)
            for(int y=0; y<ysize; y++)
                if(board[x][y].hasMine() ^ board[x][y].flagged)
                    return false;
        System.out.println("You win!!");                    //Map your tasks to be executed after winning here
        return true;
    }

    private void gameEnd() {
        System.out.println("Game End!");
        for(int x=0; x<xsize; x++)
            for(int y=0; y<ysize; y++)
                board[x][y].open();
    }

    private void recurse(Tile tile) {
        tile.open();
        int x=tile.posx, y=tile.posy;
        for(int i=0; i<8; i++) {
            if (isInRange(x + temp[i], y + temp[i + 1]) && !board[x + temp[i]][y + temp[i + 1]].hasMine())
                board[x + temp[i]][y + temp[i + 1]].fire();
        }
    }

    GridPane getGridPane(){
        GridPane gp=new GridPane();
        for(int x=0; x<xsize; x++)
            for (int y = 0; y < ysize; y++)
                gp.add(board[x][y], x, y);
        return gp;
    }
}
