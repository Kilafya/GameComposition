package com.kilafyan.gamecomposition.domain.repository

import com.kilafyan.gamecomposition.domain.entity.GameSettings
import com.kilafyan.gamecomposition.domain.entity.Level
import com.kilafyan.gamecomposition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}