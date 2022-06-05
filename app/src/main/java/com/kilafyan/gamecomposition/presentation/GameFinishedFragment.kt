package com.kilafyan.gamecomposition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kilafyan.gamecomposition.R
import com.kilafyan.gamecomposition.databinding.FragmentGameFinishedBinding
import com.kilafyan.gamecomposition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private val gameResult by lazy {
        args.gameResult
    }

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultDisplay()
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.buttonRetry.setOnClickListener { retryGame() }
    }

    private fun resultDisplay() {
        with(binding) {
            emojiResult.setImageResource(getEmojiId())
            tvRequiredAnswers.text = getRequiredAnswersText()
            tvScoreAnswers.text = getScoreAnswersText()
            tvRequiredPercentage.text = getRequiredPercentageText()
            tvScorePercentage.text = getScorePercentageText()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    private fun getRequiredAnswersText() = getStringFormat(
        R.string.required_score,
        gameResult.gameSettings.minCountOfRightAnswers
    )

    private fun getScoreAnswersText() = getStringFormat(
        R.string.score_answers,
        gameResult.countOfRightAnswers
    )

    private fun getRequiredPercentageText() = getStringFormat(
        R.string.required_percentage,
        gameResult.gameSettings.minPercentOfRightAnswers
    )

    private fun getScorePercentageText() = getStringFormat(
        R.string.score_percentage,
        getPercentOfRightAnswer()
    )

    private fun getPercentOfRightAnswer() = if (gameResult.countOfQuestions != 0) {
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    } else {
        0
    }

    private fun getStringFormat(src: Int, value: Int) = String.format(getString(src), value)

    private fun getEmojiId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}