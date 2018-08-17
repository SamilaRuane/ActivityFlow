package br.com.poc.samilasantos.sdk

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.poc.samilasantos.external.withStartActivity4Result.ExternalActivity

class ManagerActivity : AppCompatActivity() {

    private val resultCode = 4445

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
    }

    override fun onResume() {
        super.onResume()

        val handler = Handler()
        handler.postDelayed({
            startExternalActivityForResult()
        }, 2000L)
    }

    private fun startExternalActivityForResult() {
        val intent = Intent(this, ExternalActivity::class.java)
        startActivityForResult(intent, resultCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
            if (parent != null) {
                parent.setResult(Activity.RESULT_OK)
            } else {
                setResult(Activity.RESULT_OK)
            }
    }
}
