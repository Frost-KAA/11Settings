package com.example.a11settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import java.util.*

class MainActivity : AppCompatActivity() {

    private val theme_sharedPrefs by lazy {  getSharedPreferences("theme", Context.MODE_PRIVATE) }
    private val lang_sharedPrefs by lazy {  getSharedPreferences("lang", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode())
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.settingFragment);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveTheme(theme: Int) = theme_sharedPrefs.edit().putInt("prefs.theme", theme).apply()

    fun getSavedTheme() = theme_sharedPrefs.getInt("prefs.theme", -1)

    fun saveLang(lang: Int) = lang_sharedPrefs.edit().putInt("prefs.lang", lang).apply()

    fun getSavedLang() = lang_sharedPrefs.getInt("prefs.lang", -1)

    private fun init(){
        val theme_kod = getSavedTheme()
        val lang_kod = getSavedLang()

        changeTheme(theme_kod)
        changeLang(lang_kod)
    }

    fun changeLang(l_num: Int){
        var lang: String = "ru"
        if (l_num == 1) lang = "en"
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        baseContext.getResources()?.updateConfiguration(configuration, getResources().getDisplayMetrics())

    }

    fun changeTheme(theme: Int){
        if (theme == 0) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

}