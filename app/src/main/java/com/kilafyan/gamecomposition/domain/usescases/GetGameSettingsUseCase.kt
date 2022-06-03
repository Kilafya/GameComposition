package com.kilafyan.gamecomposition.domain.usescases

import com.kilafyan.gamecomposition.domain.entity.GameSettings
import com.kilafyan.gamecomposition.domain.entity.Level
import com.kilafyan.gamecomposition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level) : GameSettings {
        return repository.getGameSettings(level)
    }

}