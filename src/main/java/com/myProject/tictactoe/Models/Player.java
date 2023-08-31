package com.myProject.tictactoe.Models;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;

    private Playertype playertype;

    public Player(String name, char symbol, Playertype playertype){
        this.name = name;
        this.symbol = symbol;
        this.playertype = playertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Playertype getPlayertype() {
        return playertype;
    }

    public void setPlayertype(Playertype playertype) {
        this.playertype = playertype;
    }

    public Move decideMove(Board board){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the row to make move");
        int row = sc.nextInt();

        System.out.println("Enter the column to make move");
        int col = sc.nextInt();

        return new Move(board.getBoard().get(row).get(col),this);
    }
}
