package br.com.poc.samilasantos.external.withStartActivity4Result

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.poc.samilasantos.external.R
import kotlinx.android.synthetic.main.activity_external.button

class ExternalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external)

        button.setOnClickListener {
            sendResult()
        }
    }

    private fun sendResult() {
        if (parent != null)
            parent.setResult(Activity.RESULT_OK)
        else
            setResult(Activity.RESULT_OK)

        finish()
    }
}
