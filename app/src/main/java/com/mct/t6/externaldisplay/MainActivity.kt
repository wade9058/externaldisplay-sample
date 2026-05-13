package com.mct.t6.externaldisplay

import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var presentation: CustomizePresentation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showOnExternalDisplay()
    }

    private fun showOnExternalDisplay() {
        // 1. 取得 DisplayManager
        val displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

        // 2. 獲取所有適合顯示 Presentation 的顯示器
        val displays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION)

        if (displays.isNotEmpty()) {
            // 取得第一個外部顯示器 (通常索引 0 即為外部螢幕)
            val secondaryDisplay = displays[0]

            // 3. 建立並顯示 Presentation
            presentation = CustomizePresentation(this, secondaryDisplay)
            presentation?.show()
        }
    }

    override fun onStop() {
        super.onStop()
        // 記得在 Activity 停止時關閉，避免記憶體洩漏
        presentation?.dismiss()
        presentation = null
    }
}