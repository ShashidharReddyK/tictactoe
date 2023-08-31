package com.myProject.tictactoe;

import com.myProject.tictactoe.Models.*;
import com.myProject.tictactoe.controllers.GameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {

        System.out.println("Game is Starting...");

        Scanner sc = new Scanner(System.in);

        System.out.println("what is the dimension of the game?");

        int dimension = sc.nextInt();

        List<Player> players = new ArrayList<>();

        System.out.println("will there be any bot? y/n");

        String isBot = sc.next();
        int noOfHumanPlayers = dimension-1;

        if(isBot.equalsIgnoreCase("y")){
            noOfHumanPlayers = dimension -2;

            System.out.println("Enter the name of the bot");

            String name = sc.next();

            System.out.println("Enter bot Symbol");
            String symbol = sc.next();

            players.add(new Bot(name,symbol.charAt(0), BotDifficultyLevel.EASY));
        }
        for(int i=0;i<noOfHumanPlayers;i++){
            System.out.println("what is the name of the player: "+ (i+1));
            String name = sc.next();

            System.out.println("enter the symbol");
            String symbol = sc.next();

            Player player = new Player(name,symbol.charAt(0), Playertype.HUMAN);
            players.add(player);
        }
        System.out.println(players.size()+" "+dimension);

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension,players);

        while(gameController.getGameStatus(game).equals(GameStatus.INPROGRESSS)){

            System.out.println("This is the current Board");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo? y/n");
            String input = sc.next();
            if(input.equals("y")){
                gameController.undo(game);
            }
            else{
                gameController.executeNextMove(game);
            }
        }

        if(gameController.getGameStatus(game).equals(GameStatus.ENDED)){
            System.out.println("Game Ended \n"+ gameController.getWinner(game).getName()+" is the winner");
        }
        else{
            System.out.println("Game ended in Draw");
        }
    }
}