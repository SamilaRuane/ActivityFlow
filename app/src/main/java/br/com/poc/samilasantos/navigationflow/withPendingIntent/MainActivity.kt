package br.com.poc.samilasantos.navigationflow.withPendingIntent

import android.app.PendingIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.poc.samilasantos.navigationflow.R
import kotlinx.android.synthetic.main.activity_main.button

class MainActivity : AppCompatActivity() {

    private val requestCode = 4568

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { setPendingIntent() }
    }

    private fun setPendingIntent() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}
