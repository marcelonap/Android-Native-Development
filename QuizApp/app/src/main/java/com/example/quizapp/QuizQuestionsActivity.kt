package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat


import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var username : String? = null
    private var mflag : Boolean = true
    private var count : Int = 0
    private var currentPosition : Int = 0
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption : Int = 0

    private var pbar: ProgressBar? = null
    private var tvPbar : TextView? = null
    private var tvQ : TextView? = null
    private var img : ImageView? = null

    private var tvQ1 : TextView? = null
    private var tvQ2 : TextView? = null
    private var tvQ3 : TextView? = null
    private var tvQ4 : TextView? = null

    private var btnSub : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        username = intent.getStringExtra(Constants.USER_NAME)

        pbar = findViewById(R.id.UI_PB)
        tvPbar = findViewById(R.id.UI_TV_PB)
        tvQ = findViewById(R.id.UI_TV_Q)
        img = findViewById(R.id.UI_IMG)

        tvQ1 = findViewById(R.id.UI_Q1)
        tvQ2 = findViewById(R.id.UI_Q2)
        tvQ3 = findViewById(R.id.UI_Q3)
        tvQ4 = findViewById(R.id.UI_Q4)
        btnSub = findViewById(R.id.UI_BTN_SUB)

        tvQ1?.setOnClickListener(this)
        tvQ2?.setOnClickListener(this)
        tvQ3?.setOnClickListener(this)
        tvQ4?.setOnClickListener(this)
        btnSub?.setOnClickListener(this)



         mQuestionsList = Constants.getQuestions()

       setQuestion()
    }

    private fun setQuestion() {


        mQuestionsList = Constants.getQuestions()


        val currQ: Question = mQuestionsList!![currentPosition]
        pbar?.max = mQuestionsList!!.size
        pbar?.progress = currentPosition + 1
        tvPbar?.text = "${currentPosition + 1}/${pbar?.max}"
        tvQ?.text = currQ.question
        tvQ1?.text = currQ.optionOne
        tvQ2?.text = currQ.optionTwo
        tvQ3?.text = currQ.optionThree
        tvQ4?.text = currQ.optionFour
        img?.setImageResource(currQ.image)


        if(currentPosition == mQuestionsList!!.size){
            btnSub?.text= "FINISH"
        }else{
            btnSub?.text = "SUBMIT"
        }
    }

    private fun defaultOptions(){
        val options = ArrayList<TextView>()
        tvQ1?.let{ options.add(0,it)}
        tvQ2?.let{ options.add(1,it)}
        tvQ3?.let{ options.add(2,it)}
        tvQ4?.let{ options.add(3,it)}

        for(option in options){
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOption(tv : TextView, selected: Int){
            if(mflag) {
                defaultOptions()
                mSelectedOption = selected
                tv.setTextColor(Color.parseColor("#363a43"))
                tv.setTypeface(tv.typeface, Typeface.BOLD)
                tv.background = ContextCompat.getDrawable(this, R.drawable.selected_border_bg)
            }
    }
    private fun markRight(tv: TextView){
        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_border_bg)
    }

    private fun markWrong(tv: TextView){

    }
    private fun check(answer: Int, bgV: Int){
        when(answer){
            1 ->{
                tvQ1?.background = ContextCompat.getDrawable(this, bgV)
            }
           2 ->{
                tvQ2?.background = ContextCompat.getDrawable(this, bgV)
            }
            3 ->{
                tvQ3?.background = ContextCompat.getDrawable(this, bgV)
            }
            4 ->{
                tvQ4?.background = ContextCompat.getDrawable(this, bgV)
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.UI_BTN_SUB ->{
                if(mSelectedOption== 0) {
                    currentPosition++
                    mflag = true
                    when{
                        currentPosition < mQuestionsList!!.size ->{
                            setQuestion()
                            defaultOptions()
                        }else ->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, username)
                        intent.putExtra(Constants.CORRECT_ANSWERS, count.toString() )
                        intent.putExtra(Constants.TOTAL_QUESTIONS, (mQuestionsList!!.size).toString())
                        startActivity(intent)
                        finish()
                    }
                    }
                }else{
                    val question = mQuestionsList?.get(currentPosition)
                    mflag = false
                    if(question!!.correctAnswer != mSelectedOption){
                        check(mSelectedOption, R.drawable.wrong_border)
                        --count
                    }
                    check(question.correctAnswer, R.drawable.correct_border)
                    ++count

                    btnSub?.text = "Next Question"
                    if(currentPosition == mQuestionsList!!.size-1 ){
                        btnSub?.text = "FINISH"
                    }

                    mSelectedOption = 0
                }
            }
            R.id.UI_Q1 -> {
                tvQ1?.let {
                    selectedOption(it, 1)
                }
            }
            R.id.UI_Q2 -> {
                tvQ2?.let {
                    selectedOption(it, 2)
                }
            }
            R.id.UI_Q3 -> {
                tvQ3?.let {
                    selectedOption(it, 3)
                }
            }
            R.id.UI_Q4 -> {
                tvQ4?.let {
                    selectedOption(it, 4)
                }
            }


        }
    }
}