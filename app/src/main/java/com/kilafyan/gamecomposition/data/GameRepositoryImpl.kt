package com.kilafyan.gamecomposition.data

import com.kilafyan.gamecomposition.domain.entity.GameSettings
import com.kilafyan.gamecomposition.domain.entity.Level
import com.kilafyan.gamecomposition.domain.entity.Question
import com.kilafyan.gamecomposition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl: GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val rightAnswer = sum - visibleNumber
        val options = HashSet<Int>()
        val possibleAnswerFrom = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val possibleAnswerTo = min(rightAnswer + countOfOptions, maxSumValue)

        options.add(rightAnswer)
        while (options.size != countOfOptions) {
            options.add(Random.nextInt(possibleAnswerFrom, possibleAnswerTo))
        }

        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8
                )
            }
            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    20,
                    20,
                    80,
                    40
                )
            }
            Level.HARD -> {
                GameSettings(
                    30,
                    30,
                    95,
                    40
                )
            }
        }
    }
}