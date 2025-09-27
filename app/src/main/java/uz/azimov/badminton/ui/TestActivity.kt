package uz.azimov.badminton.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.azimov.badminton.R
import uz.azimov.badminton.databinding.ActivityTestBinding
import uz.azimov.badminton.utils.QuestionsData

class TestActivity : AppCompatActivity() {

    private val binding: ActivityTestBinding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }

    private var currentQuestionIndex = 0
    private var score = 0
    private val questions = QuestionsData.questions.shuffled().take(20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadQuestion()

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.nextButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun checkAnswer() {
        val selectedId = binding.optionsGroup.checkedRadioButtonId
        if (selectedId == -1) return

        val question = questions[currentQuestionIndex]
        val correctIndex = question.correctAnswerIndex

        val selectedIndex = when (selectedId) {
            R.id.option1 -> 0
            R.id.option2 -> 1
            R.id.option3 -> 2
            R.id.option4 -> 3
            else -> -1
        }

        if (selectedIndex == correctIndex) score++

        if (currentQuestionIndex < 19) {
            currentQuestionIndex += 1
            loadQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.test_finished))
            .setCancelable(false)
            .setMessage(getString(R.string.your_result, score, questions.size))
            .setPositiveButton("OK") { _, _ -> finish() }
            .show()
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        binding.toolbarName.text =
            getString(R.string.tests_with_count, currentQuestionIndex + 1, questions.size)
        binding.progressBar.progress = (currentQuestionIndex + 1) * 100 / questions.size
        binding.questionText.text = question.text

        binding.option1.text = question.options[0]
        binding.option2.text = question.options[1]
        binding.option3.text = question.options[2]
        binding.option4.text = question.options[3]

        binding.optionsGroup.clearCheck()
    }
}