package com.example.a11settings

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

        val b: Button = view.findViewById(R.id.nextActivity)
        b.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_firstFragment)
        }
    }

    private fun initThemeListener(){
        val themeGroup: RadioGroup? = view?.findViewById(R.id.themeGroup)
        themeGroup?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.themeLight -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.themeDark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun initLangListener(){
        val langGroup: RadioGroup? = view?.findViewById(R.id.langGroup)
        langGroup?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.ru -> changeLang("ru")
                R.id.en -> changeLang("en")
            }
        }
    }

    fun changeLang(lang: String){
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        (context as MainActivity).getResources()?.updateConfiguration(configuration, getResources().getDisplayMetrics())
    }



}