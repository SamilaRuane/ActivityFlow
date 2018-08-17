package br.com.poc.samilasantos.navigationflow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class SuccessActivity : AppCompatActivity() {

    private val EXTRAS_KEY = "extras"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        if (intent != null) {
            val result = intent.extras.getString(EXTRAS_KEY)
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
    }
}