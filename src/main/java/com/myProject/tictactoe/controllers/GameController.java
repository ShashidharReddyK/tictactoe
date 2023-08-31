package com.myProject.tictactoe.controllers;

import com.myProject.tictactoe.Models.Game;
import com.myProject.tictactoe.Models.GameStatus;
import com.myProject.tictactoe.Models.Player;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players){
        Game game = Game.getBuilder().setDimension(dimension).setPlayers(players).build();
        return game;
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public void displayBoard(Game game){
        game.displayBoard();
    }
    public void executeNextMove(Game game){
        game.makeNextMove();
    }
    public void undo(Game game){
        game.undo();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
}
