package com.myProject.tictactoe.factories;

import com.myProject.tictactoe.Models.BotDifficultyLevel;
import com.myProject.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;
import com.myProject.tictactoe.strategies.botplayingstrategy.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }
        return null;
    }
}
