package uz.azimov.badminton.models

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)