package uz.azimov.badminton.models

data class Chapter(
    val id: String,
    val title: String,
    val topics: List<Theme>
)
