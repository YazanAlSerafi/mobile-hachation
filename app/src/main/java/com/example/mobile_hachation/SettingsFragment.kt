package com.example.mobile_hachation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // نربط الواجهة
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // نربط الزر من الواجهة
        val btnToggleTheme = view.findViewById<Button>(R.id.btnToggleTheme)

        // نخلي الزر يبدل بين الوضع الليلي والعادي
        btnToggleTheme.setOnClickListener {
            val currentNightMode = AppCompatDelegate.getDefaultNightMode()

            if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            // نعيد تشغيل النشاط الحالي عشان يتطبق التغيير
            activity?.recreate()
        }

        return view
    }
}
