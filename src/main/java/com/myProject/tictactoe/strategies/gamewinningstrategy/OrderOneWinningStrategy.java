package com.myProject.tictactoe.strategies.gamewinningstrategy;

import com.myProject.tictactoe.Models.Board;
import com.myProject.tictactoe.Models.Game;
import com.myProject.tictactoe.Models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy {
    List<HashMap<Character,Integer>> rowSymbolCounts = new ArrayList<>();
    List<HashMap<Character,Integer>> colSymbolcounts = new ArrayList<>();
    HashMap<Character,Integer> leftDiagonalCounts = new HashMap<>();
    HashMap<Character,Integer> rightDiagonalCounts = new HashMap<>();

    public OrderOneWinningStrategy(int dimension){
        for(int i=0;i<dimension;i++){
            rowSymbolCounts.add(new HashMap<>());
            colSymbolcounts.add(new HashMap<>());
        }
    }
    public boolean isCellOnTopLeftDiagonal(int row,int col,int dimension){
        return row == col;
    }

    public boolean isCellOnTopRightDiagonal(int row,int col,int dimension){
        return row+col == dimension-1;
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        char symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int dimension = board.getBoard().size();

        if(!rowSymbolCounts.get(row).containsKey(symbol)){
            rowSymbolCounts.get(row).put(symbol,0);
        }
        rowSymbolCounts.get(row).put(symbol,rowSymbolCounts.get(row).get(symbol)+1);

        colSymbolcounts.get(col).put(symbol,colSymbolcounts.get(col).getOrDefault(symbol,0)+1);

        if(isCellOnTopLeftDiagonal(row,col,dimension)){
            leftDiagonalCounts.put(symbol,leftDiagonalCounts.getOrDefault(symbol,0)+1);
        }
        if(isCellOnTopRightDiagonal(row,col,dimension)){
            rightDiagonalCounts.put(symbol,rightDiagonalCounts.getOrDefault(symbol,0)+1);
        }

        if(rowSymbolCounts.get(row).get(symbol) == dimension ||
                colSymbolcounts.get(col).get(symbol) == dimension){
            return true;
        }
        if(leftDiagonalCounts.containsKey(symbol) && leftDiagonalCounts.get(symbol) ==dimension){
            return true;
        }
        if(rightDiagonalCounts.containsKey(symbol) && rightDiagonalCounts.get(symbol) == dimension){
            return true;
        }

        return false;
    }

    public void applyUndo(Board board, Move move){
        int dimension = board.getBoard().size();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();
        rowSymbolCounts.get(row).put(symbol,rowSymbolCounts.get(row).get(symbol)-1);
        colSymbolcounts.get(col).put(symbol,colSymbolcounts.get(col).get(symbol)-1);

        if(isCellOnTopLeftDiagonal(row,col,dimension)){
            leftDiagonalCounts.put(symbol,leftDiagonalCounts.get(symbol)-1);
        }

        if(isCellOnTopRightDiagonal(row,col,dimension)){
            rightDiagonalCounts.put(symbol,rightDiagonalCounts.get(symbol)-1);
        }
    }
}
