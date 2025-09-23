package uz.azimov.badminton.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uz.azimov.badminton.R
import uz.azimov.badminton.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private val binding: ActivityAboutBinding by lazy {
        ActivityAboutBinding.inflate(layoutInflater)
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

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
            ownerPhone.setOnClickListener {
                if (ActivityCompat.checkSelfPermission(
                        this@AboutActivity,
                        android.Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@AboutActivity,
                        arrayOf(android.Manifest.permission.CALL_PHONE),
                        101
                    )
                } else {
                    val callIntent = Intent(ACTION_VIEW, "tel:+998997037873".toUri())
                    startActivity(callIntent)
                }
            }
            ownerEmail.setOnClickListener {
                val intent = Intent(ACTION_VIEW, "mailto:nodir14041988@gmail.com".toUri())
                startActivity(intent)
            }
            ownerTelegram.setOnClickListener {
                val intent = Intent(ACTION_VIEW, "https://t.me/nodirazimov88".toUri())
                startActivity(intent)
            }
        }
    }
}