package uz.azimov.badminton.models

sealed class ChapterAndTheme {
    data class ChapterItem(val chapter: Chapter) : ChapterAndTheme()
    data class ThemeItem(val theme: Theme, val chapterId: String) : ChapterAndTheme()
}