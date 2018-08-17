package br.com.poc.samilasantos.navigationflow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.poc.samilasantos.sdk.SdkGateway
import kotlinx.android.synthetic.main.activity_main.button

class MainActivity : AppCompatActivity() {

    private val gateway by lazy { SdkGateway }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { callSdk() }
    }

    private fun callSdk() {
        val successIntent = Intent(this, SuccessActivity::class.java)
        val cancellationIntent = Intent(this, CancellationActivity::class.java)

        successIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        cancellationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val baseIntent = gateway.createCallerIntent(this, successIntent, cancellationIntent)

        startActivity(baseIntent)
    }
}
