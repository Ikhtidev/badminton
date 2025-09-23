package uz.azimov.badminton.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.azimov.badminton.databinding.ItemChapterBinding
import uz.azimov.badminton.databinding.ItemThemeBinding
import uz.azimov.badminton.models.Chapter
import uz.azimov.badminton.models.ChapterAndTheme
import uz.azimov.badminton.models.Theme

class ThemeAdapter(
    val chapters: List<Chapter>,
    val themeClick: (Theme) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val displayList: MutableList<ChapterAndTheme> = mutableListOf()

    companion object {
        const val VIEW_TYPE_CHAPTER = 1
        const val VIEW_TYPE_THEME = 2
    }
    init {
        chapters.forEach { chapter ->
            displayList.add(ChapterAndTheme.ChapterItem(chapter))
            chapter.topics.forEach { theme ->
                displayList.add(ChapterAndTheme.ThemeItem(theme, chapter.id))
            }
        }
    }

    inner class ChapterViewHolder(val binding: ItemChapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ChapterAndTheme.ChapterItem) {
            binding.chapterName.text = item.chapter.title
        }
    }
    inner class ThemeViewHolder(val binding: ItemThemeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ChapterAndTheme.ThemeItem) {
            binding.themeName.text = item.theme.themeName
            itemView.setOnClickListener {
                themeClick(item.theme)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (displayList[position]) {
            is ChapterAndTheme.ChapterItem -> VIEW_TYPE_CHAPTER
            is ChapterAndTheme.ThemeItem -> VIEW_TYPE_THEME
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CHAPTER -> {
                ChapterViewHolder(ItemChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            VIEW_TYPE_THEME -> {
                ThemeViewHolder(ItemThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = displayList[position]) {
            is ChapterAndTheme.ChapterItem -> (holder as ChapterViewHolder).onBind(item)
            is ChapterAndTheme.ThemeItem -> (holder as ThemeViewHolder).onBind(item)
        }
    }

    override fun getItemCount(): Int = displayList.size
}