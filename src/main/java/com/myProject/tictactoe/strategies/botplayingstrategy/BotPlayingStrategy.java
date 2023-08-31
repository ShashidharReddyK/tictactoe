package com.myProject.tictactoe.strategies.botplayingstrategy;

import com.myProject.tictactoe.Models.Board;
import com.myProject.tictactoe.Models.Move;
import com.myProject.tictactoe.Models.Player;

public interface BotPlayingStrategy {
    Move decideMove(Player player, Board board );
}
