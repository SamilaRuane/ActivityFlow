package br.com.poc.samilasantos.sdk

import android.app.PendingIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.poc.samilasantos.external.ExternalWorldGateway
import br.com.poc.samilasantos.external.Keys.CANCELLATION_INTENT_KEY
import br.com.poc.samilasantos.external.Keys.EXTRAS_KEY
import br.com.poc.samilasantos.external.Keys.LOOP_INTENT_REQUEST_CODE
import br.com.poc.samilasantos.external.Keys.SUCCESS_INTENT_KEY
import kotlinx.android.synthetic.main.activity_manager.infoText
import kotlinx.android.synthetic.main.activity_manager.sendButton

class ManagerActivity : AppCompatActivity() {

    private lateinit var successIntent: PendingIntent
    private lateinit var cancellationIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
        val intent = createExternalIntent()
        sendButton.setOnClickListener { startActivity(intent) }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()

        val data: String? = intent.extras.getString(EXTRAS_KEY) ?: ""
        if (data != null && data.isNotEmpty()) {
            infoText.visibility = View.VISIBLE
            sendButton.setOnClickListener {
                successIntent.send(this, 0, intent)
            }
        }
    }

    private fun createExternalIntent(): Intent {

        val intentToMyself = Intent(this, ManagerActivity::class.java)
        intentToMyself.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intentToMyself.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntentToMyself = PendingIntent.getActivity(
            this,
            LOOP_INTENT_REQUEST_CODE,
            intentToMyself,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (intent != null) {
            successIntent = intent.extras.getParcelable(SUCCESS_INTENT_KEY)
            cancellationIntent = intent.extras.getParcelable(
                CANCELLATION_INTENT_KEY
            )
        }

        return ExternalWorldGateway.callExternalActivity(
            this,
            pendingIntentToMyself,
            cancellationIntent
        )
    }

    override fun onBackPressed() {
        cancellationIntent.send()
        super.onBackPressed()
    }
}
