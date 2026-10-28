package com.wholesale.hisab

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val layout = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        setBackgroundColor(Color.WHITE)
    }

    val title = TextView(this).apply {
        text = "ہول سیل حساب کتاب"
        textSize = 28f
        setTextColor(Color.rgb(26, 75, 140))
        gravity = Gravity.CENTER
    }

    val message = TextView(this).apply {
        text = "ٹیسٹ کامیاب!"
        textSize = 20f
        setTextColor(Color.DKGRAY)
        gravity = Gravity.CENTER
    }

    layout.addView(title)
    layout.addView(message)

    setContentView(layout)
}

}
