package com.kilafyan.gamecomposition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kilafyan.gamecomposition.R
import com.kilafyan.gamecomposition.domain.entity.GameResult

interface OnOptionClickListener{
    fun onOptionClick(number: Int)
}

@BindingAdapter("requiredAnswer")
fun bindRequiredAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswer")
fun bindScoreAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercent(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercent")
fun bindScorePercent(textView: TextView, gameResult: GameResult) {
    val count = if (gameResult.countOfRightAnswers != 0) {
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    } else {
        0
    }

    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        count
    )
}

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
    if (winner) {
        imageView.setImageResource(R.drawable.ic_smile)
    } else {
        imageView.setImageResource(R.drawable.ic_sad)
    }
}

@BindingAdapter("setPercentOfRightAnswer")
fun setPercentOfRightAnswer(progressBar: ProgressBar, percent: Int) {
    progressBar.setProgress(percent, true)
}

@BindingAdapter("setColorLine")
fun setColorLine(progressBar: ProgressBar, state: Boolean){
    val color =  getColorByState(progressBar.context, state)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("setTextColorDependingAnswer")
fun setTextColorDependingAnswer(textView: TextView, state: Boolean) {
    val color = getColorByState(textView.context, state)
    textView.setTextColor(color)
}

@BindingAdapter("numberAsText")
fun numberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun getColorByState(context: Context, state: Boolean): Int {
    return ContextCompat.getColor(context, if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    })
}