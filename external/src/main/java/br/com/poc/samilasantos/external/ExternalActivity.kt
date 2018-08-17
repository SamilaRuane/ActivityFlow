package br.com.poc.samilasantos.external

import android.app.PendingIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.poc.samilasantos.external.Keys.CANCELLATION_INTENT_KEY
import br.com.poc.samilasantos.external.Keys.EXTRAS_KEY
import br.com.poc.samilasantos.external.Keys.SUCCESS_INTENT_KEY
import kotlinx.android.synthetic.main.activity_external.cancellationButton
import kotlinx.android.synthetic.main.activity_external.successButton

/**
 * Essa activity representa o mundo externo, os dois botões simulam um caso de sucesso e erro
 * */
class ExternalActivity : AppCompatActivity() {

    private lateinit var successIntent: PendingIntent
    private lateinit var cancellationIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external)

        val extrasToSend = Intent()
        extrasToSend.putExtra(EXTRAS_KEY, Keys.RESULT_OK)

        if (intent != null) {
            successIntent = intent.extras.getParcelable(SUCCESS_INTENT_KEY)
            cancellationIntent = intent.extras.getParcelable(
                CANCELLATION_INTENT_KEY
            )

            /**
             * Chama Activity de sucesso
             * */
            successButton.setOnClickListener {
                successIntent.send(this, 0, extrasToSend)
                finish()
            }

            /**
             * Chama Activity de cancelamento
             * */
            cancellationButton.setOnClickListener {
                cancellationIntent.send()
                finish()
            }
        }
    }

    /**
     * Chama Activity de cancelamento
     * */
    override fun onBackPressed() {
        finish()
        cancellationIntent.send()
        super.onBackPressed()
    }
}
