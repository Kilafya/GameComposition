package com.kilafyan.gamecomposition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kilafyan.gamecomposition.R
import com.kilafyan.gamecomposition.data.GameRepositoryImpl
import com.kilafyan.gamecomposition.databinding.FragmentGameBinding
import com.kilafyan.gamecomposition.domain.entity.GameResult
import com.kilafyan.gamecomposition.domain.entity.GameSettings
import com.kilafyan.gamecomposition.domain.entity.Level
import com.kilafyan.gamecomposition.domain.entity.Question

class GameFragment : Fragment() {

    private lateinit var level: Level
    private lateinit var settings: GameSettings
    private lateinit var question: Question
    private val gameRepositoryImpl = GameRepositoryImpl



    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener {
            launchGameFinishedFragment(
                GameResult(true, 0, 0,
                GameSettings(0,0,0,0)
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun initParam() {
        settings = gameRepositoryImpl.getGameSettings(level)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .commit()
    }

    companion object {
        const val NAME = "game_fragment"

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}