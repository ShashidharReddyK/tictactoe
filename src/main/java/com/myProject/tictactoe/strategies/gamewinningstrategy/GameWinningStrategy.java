package com.myProject.tictactoe.strategies.gamewinningstrategy;

import com.myProject.tictactoe.Models.Board;
import com.myProject.tictactoe.Models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);

    void applyUndo(Board board, Move move);
}
