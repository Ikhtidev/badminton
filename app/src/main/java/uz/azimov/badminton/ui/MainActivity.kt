package uz.azimov.badminton.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import uz.azimov.badminton.R
import uz.azimov.badminton.adapters.ThemeAdapter
import uz.azimov.badminton.databinding.ActivityMainBinding
import uz.azimov.badminton.utils.Constants

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null
        navView.setNavigationItemSelectedListener(this)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val themeAdapter = ThemeAdapter(Constants.CHAPTERS_WITH_TOPICS, themeClick = { theme ->
            openPdfActivity(theme.themeName, theme.pdfName)
        })
        binding.rvChapters.layoutManager = LinearLayoutManager(this)
        binding.rvChapters.adapter = themeAdapter

        // callback for OnBackPressedDispatcher
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun openPdfActivity(themeName: String, pdfName: String) {
        val intent = Intent(this, PdfViewerActivity::class.java)
        intent.putExtra("themeName", themeName)
        intent.putExtra("pdfName", "${pdfName}.pdf")
        startActivity(intent)
    }

    // for Navigation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.nav_amaliy -> {
                 openPdfActivity(resources.getString(R.string.practical_materials), "Amaliy_mashgulot")
             }
             R.id.nav_tests -> {
                 openPdfActivity(resources.getString(R.string.tests), "Testlar")
             }
             R.id.nav_glossary -> {
                 openPdfActivity(resources.getString(R.string.glossary), "Glossariy")
             }
             R.id.nav_adabiyot -> {
                 openPdfActivity(resources.getString(R.string.bookmarks), "Foydalangan_adabiyotlar")
             }
             R.id.nav_about -> {
                 startActivity(Intent(this, AboutActivity::class.java))
             }
         }
//        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}