package com.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class Tile extends Button{
    final static String style_normal= """
            -fx-background-color:darkgrey;
            -fx-border-color:grey;
            -fx-background-radius: 0px;
            -fx-border-width: 0.2px;
            -fx-padding: 0px;
            """,
    style_opened= """
            -fx-background-color:darkgrey;
            -fx-border-color:grey;
            -fx-background-radius: 0px;
            -fx-border-width: 0.2px;
            -fx-padding: 0px;
            """,
    style_flagged= """
            -fx-text-fill:darkred;
            -fx-background-color:darkgrey;
            -fx-border-color:grey;
            -fx-background-radius: 0px;
            -fx-border-width: 0.2px;
            -fx-padding: 0px;
            """,
    style_mineOpened= """
            -fx-text-fill:darkred;
            -fx-background-color:darkgrey;
            -fx-border-color:grey;
            -fx-background-radius: 0px;
            -fx-border-width: 0.2px;
            -fx-padding: 0px;
            """;
    boolean flagged;
    protected int val;
    final int posx, posy;
    Tile(int x, int y, int value){
        posx=x;
        posy=y;
        val=value;
        flagged=false;
        setStyle(style_normal);
        setMaxSize(30,30);
        setMinSize(30,30);

    }
    void toggleFlag(){
        if(flagged){
            flagged=false;
            setText(" ");
            setStyle(style_normal);
        }
        else {
            flagged=true;
            setText("X");
            setStyle(style_flagged);
        }
    }
    public void open(){
        setDisabled(true);
        if(isDisabled()){}
        else if(hasMine())
        {
            setStyle(style_mineOpened);
            setText("X");
        }
        else if(isEmpty())
           setStyle(style_opened);
        else {
          setText(String.valueOf(val));
          setStyle(style_opened);
        }
    }

    public boolean hasMine() {
        return val==-1;
    }
    public boolean isEmpty() {
        return val==0;
    }
}
