package com.myProject.tictactoe.strategies.gamedrawingstrategy;

import com.myProject.tictactoe.Models.Board;
import com.myProject.tictactoe.Models.Cell;
import com.myProject.tictactoe.Models.Move;

import java.util.HashSet;

public class OrderOneDrawingStrategy implements GameDrawingStrategy{
    HashSet<Cell> cells = new HashSet<>();

    public OrderOneDrawingStrategy(Board board){
        for(int i=0;i<board.getBoard().size();i++){
            for(int j=0;j<board.getBoard().size();j++){
                cells.add(board.getBoard().get(i).get(j));
            }
        }
    }

    @Override
    public boolean checkGameIsDraw(Board board, Move move) {
        cells.remove(board.getBoard().get(move.getCell().getRow()).get(move.getCell().getCol()));
        if(cells.isEmpty()) return true;
        return false;
    }

    public void applyUndo(Board board, Move move){
        cells.add(board.getBoard().get(move.getCell().getRow()).get(move.getCell().getCol()));
    }
}
