package com.example.a11settings

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.*


class SettingFragment : Fragment() {


    lateinit var themeLight: RadioButton
    lateinit var themeDark: RadioButton

    lateinit var ru: RadioButton
    lateinit var en: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themeLight = view.findViewById(R.id.themeLight)
        themeDark = view.findViewById(R.id.themeDark)

        ru = view.findViewById(R.id.ru)
        en = view.findViewById(R.id.en)

        initThemeListener()
        initLangListener()
        init()

        val b: Button = view.findViewById(R.id.nextActivity)
        b.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_firstFragment)
        }
    }

    private fun initThemeListener(){
        val themeGroup: RadioGroup? = view?.findViewById(R.id.themeGroup)
        themeGroup?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.themeLight -> changeTheme(0)
                R.id.themeDark -> changeTheme(1)
            }
        }
    }

    private fun initLangListener(){
        val langGroup: RadioGroup? = view?.findViewById(R.id.langGroup)
        langGroup?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.ru -> changeLang(0)
                R.id.en -> changeLang(1)
            }
        }
    }

    private fun init(){
        val theme_kod = (context as MainActivity).getSavedTheme()
        val lang_kod = (context as MainActivity).getSavedLang()

        if (theme_kod == 1) themeDark.isChecked = true
        else themeLight.isChecked = true

        if (lang_kod == 1)  en.isChecked = true
        else ru.isChecked = true
    }

    fun changeLang(l_num: Int){
        var lang: String = "ru"
        if (l_num == 1) lang = "en"
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        (context as MainActivity).getResources()?.updateConfiguration(configuration, getResources().getDisplayMetrics())
        (context as MainActivity).saveLang(l_num)
    }

    fun changeTheme(theme: Int){
        if (theme == 0) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        (context as MainActivity).saveTheme(theme)
    }



}