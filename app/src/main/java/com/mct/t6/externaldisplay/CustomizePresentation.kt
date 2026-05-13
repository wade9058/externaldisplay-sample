package com.mct.t6.externaldisplay

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import android.widget.TextView


class CustomizePresentation(outerContext: Context, display: Display) : Presentation(outerContext, display) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.presentation_layout)

        val textView = findViewById<TextView>(R.id.secondary_text)
        textView.text = "2"
    }
}