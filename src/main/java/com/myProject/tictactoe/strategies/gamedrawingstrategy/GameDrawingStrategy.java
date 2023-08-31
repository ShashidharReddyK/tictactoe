package com.myProject.tictactoe.strategies.gamedrawingstrategy;

import com.myProject.tictactoe.Models.Board;
import com.myProject.tictactoe.Models.Move;

public interface GameDrawingStrategy {

    boolean checkGameIsDraw(Board board, Move move);

    void applyUndo(Board board, Move move);
}
