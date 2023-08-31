package com.myProject.tictactoe.Models;

import java.util.ArrayList;
import java.util.List;


public class Board {
    private List<List<Cell>> board;

    public Board (int dimension){
        this.board = new ArrayList<>();
        for(int i=0;i<dimension;i++){
            this.board.add(new ArrayList<>());
            for(int j=0;j<dimension;j++){
                this.board.get(i).add(new Cell(i,j));
            }
        }
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void displayBoard(){
        int dim = board.size();
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                if(board.get(i).get(j).getCellStatus().equals(CellStatus.EMPTY)){
                    System.out.print("|   |");
                }
                else{
                    System.out.print("| "+board.get(i).get(j).getPlayer().getSymbol()+" |");
                }
            }
            System.out.println();

        }
    }

    public void applyMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        this.getBoard().get(row).get(col).setCellStatus(CellStatus.FILLED);
        this.getBoard().get(row).get(col).setPlayer(move.getPlayer());
    }

    public void applyUndoMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        board.get(row).get(col).setCellStatus(CellStatus.EMPTY);
        board.get(row).get(col).setPlayer(null);
    }
}
