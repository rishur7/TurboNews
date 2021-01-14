package com.example.turbonews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_about_me.*

class AboutMe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)
        insta.movementMethod = LinkMovementMethod.getInstance()
        linkedin.movementMethod= LinkMovementMethod.getInstance()
    }
}