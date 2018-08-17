package br.com.poc.samilasantos.navigationflow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.poc.samilasantos.sdk.Gateway
import br.com.poc.samilasantos.sdk.GatewayImpl
import kotlinx.android.synthetic.main.activity_main.button

class MainActivity : AppCompatActivity() {

    private val gateway: Gateway by lazy { GatewayImpl(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { callSdk() }
    }

    private fun callSdk() {
        val successIntent = Intent(this, SuccessActivity :: class.java)
        val cancellationIntent = Intent (this, CancellationActivity :: class.java)

        val baseIntent = gateway.run {
            createCallerIntent(
                wrapperSuccessIntent(successIntent),
                wrapperCancelationIntent(cancellationIntent)
            )
        }

        startActivity(baseIntent)

    }
}
