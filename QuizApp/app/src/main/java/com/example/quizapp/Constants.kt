package com.example.quizapp

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()


        val que1 = Question(1,
            "What Country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Brazil",
            "Argentina",
            "Australia",
            "Belgium",
            1)

        questionsList.add(que1)
        val que2 = Question(2,
            "What Country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Italy",
            "Germany",
            "Netherlands",
            "Belgium",
            2)

        questionsList.add(que2)
        val que3 = Question(3,
            "What Country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Russia",
            "Luxemburg",
            "Austria",
            "Belgium",
            4)

        questionsList.add(que3)
        val que4 = Question(1,
            "What Country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Englang",
            "Switzerland",
            "Denmark",
            "Brussels",
            3)

        questionsList.add(que4)
        return questionsList
    }
}