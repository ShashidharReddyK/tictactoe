package com.myProject.tictactoe.Models;

import com.myProject.tictactoe.strategies.gamedrawingstrategy.GameDrawingStrategy;
import com.myProject.tictactoe.strategies.gamedrawingstrategy.OrderOneDrawingStrategy;
import com.myProject.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;
import com.myProject.tictactoe.strategies.gamewinningstrategy.OrderOneWinningStrategy;
import exceptions.InvalidGameDimensionException;
import exceptions.InvalidNumberOfPlayersException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;

    private List<Player> players;

    private List<Move> moves;

    private int nextPlayerIndex;

    private GameStatus gameStatus;

    private GameWinningStrategy gameWinningStrategy;

    private GameDrawingStrategy gameDrawingStrategy;

    private Player winner;

    private Game(){
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Player getWinner() {
        return winner;
    }

    public void displayBoard(){
        this.board.displayBoard();
    }

    public void undo(){
        if(moves.isEmpty()) return;
        Move move = moves.remove(moves.size()-1);

        gameWinningStrategy.applyUndo(board,move);
        gameDrawingStrategy.applyUndo(board,move);

        board.applyUndoMove(move);

        nextPlayerIndex += players.size()-1;
        nextPlayerIndex %= players.size();
    }

    public void makeNextMove(){
        Player playerToMove = players.get(nextPlayerIndex);
        Move move;

        System.out.println("It is "+ playerToMove.getName() + "'s turn");
        if(playerToMove.getPlayertype().equals(Playertype.BOT)){
            Bot player = (Bot)playerToMove;
            move = player.getBotPlayingStrategy().decideMove(player,this.getBoard());
        }
        else move = playerToMove.decideMove(board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY)){
            board.applyMove(move);
            moves.add(move);

            if(gameWinningStrategy.checkWinner(board,move)){
                gameStatus = GameStatus.ENDED;
                winner = playerToMove;
            }
            if(!gameStatus.equals(GameStatus.ENDED) && gameDrawingStrategy.checkGameIsDraw(board,move)){
                gameStatus = GameStatus.DRAW;
            }

            nextPlayerIndex += 1;
            nextPlayerIndex %= players.size();
        }
        else{
            System.out.println("try valid move");
        }
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameDrawingStrategy getGameDrawingStrategy() {
        return gameDrawingStrategy;
    }

    public void setGameDrawingStrategy(GameDrawingStrategy gameDrawingStrategy) {
        this.gameDrawingStrategy = gameDrawingStrategy;
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        private boolean isValid() throws InvalidGameDimensionException,InvalidNumberOfPlayersException {
            if(players.size() != dimension-1){
                throw new InvalidNumberOfPlayersException("Number of players should be 1 less than Dimension");
            }

            if(dimension <3){
                throw new InvalidGameDimensionException("Dimension should be >= 3");
            }

            return true;
        }

        public Game build(){
            try{
                isValid();
            }catch(InvalidGameDimensionException e){
                System.out.println(e.getMessage());
                return null;
            }catch(InvalidNumberOfPlayersException e){
                System.out.println(e.getMessage());
                return null;
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.INPROGRESSS);
            game.setBoard(new Board(dimension));
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimension));
            game.setGameDrawingStrategy(new OrderOneDrawingStrategy(game.getBoard()));

            return game;
        }
    }


}
