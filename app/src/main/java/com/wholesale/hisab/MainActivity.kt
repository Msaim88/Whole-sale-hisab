
package com.wholesale.hisab

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private val bg = Color.rgb(245, 247, 250)
    private val blue = Color.rgb(26, 75, 140)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 24, 20, 20)
            setBackgroundColor(bg)
            layoutDirection = android.view.View.LAYOUT_DIRECTION_RTL
        }

        val title = TextView(this).apply {
            text = "ہول سیل حساب کتاب"
            textSize = 27f
            setTextColor(blue)
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 22)
        }
        root.addView(title)

        val subtitle = TextView(this).apply {
            text = "اپنا کاروباری حساب آسانی سے رکھیں"
            textSize = 16f
            gravity = Gravity.CENTER
            setTextColor(Color.DKGRAY)
        }
        root.addView(subtitle)

        val customer = EditText(this).apply {
            hint = "گاہک کا نام"
            textSize = 18f
            setSingleLine()
        }
        root.addView(customer, fieldParams())

        val amount = EditText(this).apply {
            hint = "رقم (روپے)"
            inputType = 8194
            textSize = 18f
            setSingleLine()
        }
        root.addView(amount, fieldParams())

        val note = EditText(this).apply {
            hint = "تفصیل (مثلاً ادھار یا وصولی)"
            textSize = 16f
        }
        root.addView(note, fieldParams())

        val save = Button(this).apply {
            text = "ریکارڈ محفوظ کریں"
            textSize = 18f
            setBackgroundColor(blue)
            setTextColor(Color.WHITE)
        }
        root.addView(save, fieldParams())

        val records = TextView(this).apply {
            text = "کاروباری ریکارڈ"
            textSize = 21f
            setTextColor(blue)
            setPadding(0, 22, 0, 10)
        }
        root.addView(records)

        val list = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        root.addView(list)

        save.setOnClickListener {
            val name = customer.text.toString().trim()
            val value = amount.text.toString().trim()
            val detail = note.text.toString().trim()

            if (name.isEmpty() || value.isEmpty()) {
                Toast.makeText(
                    this, "گاہک کا نام اور رقم لکھیں",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val entry = "$name  |  Rs. $value\n$detail"
                val old = getPreferences(0)
                    .getString("records", "") ?: ""
                val updated = if (old.isEmpty()) entry
                              else "$entry\n---\n$old"

                getPreferences(0).edit()
                    .putString("records", updated).apply()

                val row = TextView(this).apply {
                    text = entry
                    textSize = 17f
                    setTextColor(Color.DKGRAY)
                    setPadding(8, 12, 8, 12)
                }
                list.addView(row, 0)
                customer.text.clear()
                amount.text.clear()
                note.text.clear()
                Toast.makeText(this, "ریکارڈ محفوظ ہوگیا", Toast.LENGTH_SHORT).show()
            }
        }

        val saved = getPreferences(0).getString("records", "") ?: ""
        if (saved.isNotEmpty()) {
            saved.split("\n---\n").forEach { entry ->
                val row = TextView(this).apply {
                    text = entry
                    textSize = 17f
                    setTextColor(Color.DKGRAY)
                    setPadding(8, 12, 8, 12)
                }
                list.addView(row)
            }
        }

        val scroll = ScrollView(this)
        root.removeView(list)
        scroll.addView(list)
        root.addView(scroll, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
        ))

        setContentView(root)
    }

    private fun fieldParams() = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply {
        bottomMargin = 10
    }
}
