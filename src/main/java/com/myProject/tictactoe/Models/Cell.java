package com.myProject.tictactoe.Models;

public class Cell {
    private int row;
    private int col;

    private Player player;

    private CellStatus cellStatus;

    public Cell(int row,int col){
        this.row = row;
        this.col = col;
        this.cellStatus = CellStatus.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int hashCod(){
        return row*31+col;
    }

    public boolean equals(Object obj){
        Cell ob = (Cell)obj;
        return this.row == ob.row && this.col == ob.col;
    }
}
