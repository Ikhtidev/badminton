package uz.azimov.badminton.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rajat.pdfviewer.util.CacheStrategy
import uz.azimov.badminton.R
import uz.azimov.badminton.databinding.ActivityMyPdfViewerBinding
import java.io.File
import java.io.FileOutputStream

class MyPdfViewerActivity : AppCompatActivity() {

    private val binding: ActivityMyPdfViewerBinding by lazy {
        ActivityMyPdfViewerBinding.inflate(layoutInflater)
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

            val file = File(cacheDir, fileName)
            if (!file.exists()) {
                assets.open(fileName).use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
            }
            pdfView.initWithFile(file, CacheStrategy.DISABLE_CACHE)
        }
    }
}