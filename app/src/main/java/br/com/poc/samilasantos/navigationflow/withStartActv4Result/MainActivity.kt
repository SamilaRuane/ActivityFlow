package br.com.poc.samilasantos.navigationflow.withStartActv4Result

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.poc.samilasantos.navigationflow.R
import br.com.poc.samilasantos.sdk.ManagerActivity
import kotlinx.android.synthetic.main.activity_main.button

class MainActivity : AppCompatActivity() {

    private val resultCode = 4654

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { startSdkActivityForResult() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
            Log.i("TRACKER", "Sucesso")
        else
            Log.i("TRACKER", "Error")
    }

    private fun startSdkActivityForResult() {
        val intent = Intent(this, ManagerActivity::class.java)
        startActivityForResult(intent, resultCode)
    }
}
