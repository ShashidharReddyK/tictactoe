package com.myProject.tictactoe.strategies.botplayingstrategy;

import com.myProject.tictactoe.Models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    public Move decideMove(Player player, Board board){

        for(int i=0;i<board.getBoard().size();i++){
            for(int j=0;j<board.getBoard().size();j++){
                if(board.getBoard().get(i).get(j).getCellStatus().equals(CellStatus.EMPTY)){
                    return new Move(board.getBoard().get(i).get(j),player);
                }
            }
        }
        return null;

    }
}
