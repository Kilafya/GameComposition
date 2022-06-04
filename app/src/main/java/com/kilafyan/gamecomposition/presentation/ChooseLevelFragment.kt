package com.kilafyan.gamecomposition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kilafyan.gamecomposition.R
import com.kilafyan.gamecomposition.databinding.FragmentChooseLevelBinding
import com.kilafyan.gamecomposition.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupClickListeners() {
        with(binding) {
            btnLvlTest.setOnClickListener { launchGameFragment(Level.TEST) }
            btnLvlEasy.setOnClickListener { launchGameFragment(Level.EASY) }
            btnLvlNormal.setOnClickListener { launchGameFragment(Level.NORMAL) }
            btnLvlHard.setOnClickListener { launchGameFragment(Level.HARD) }
        }
    }

    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(GameFragment.NAME)
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .commit()
    }

    companion object {

        const val NAME = "choose_level_fragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }

    }
}