package uz.azimov.badminton.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.azimov.badminton.R
import uz.azimov.badminton.databinding.ActivityPdfViewerBinding

class PdfViewerActivity : AppCompatActivity() {

    private val binding: ActivityPdfViewerBinding by lazy {
        ActivityPdfViewerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val themeName = intent.getStringExtra("themeName")?:"unknown"
        val fileName = intent.getStringExtra("pdfName")?:"0.pdf"

        binding.apply {
            toolbarName.text = themeName
            btnBack.setOnClickListener {
                finish()
            }
            pdfView.fromAsset(fileName)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(false)
                .defaultPage(0)
                .load()
        }
    }
}