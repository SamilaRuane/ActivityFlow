package br.com.poc.samilasantos.external

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import br.com.poc.samilasantos.external.Keys.CANCELLATION_INTENT_KEY
import br.com.poc.samilasantos.external.Keys.SUCCESS_INTENT_KEY

object ExternalWorldGateway {

    fun callExternalActivity(
        context: Context,
        successIntent: PendingIntent,
        cancellationIntent: PendingIntent
    ): Intent {

        val baseIntent = Intent(context, ExternalActivity::class.java)

        baseIntent.putExtra(SUCCESS_INTENT_KEY, successIntent)
        baseIntent.putExtra(CANCELLATION_INTENT_KEY, cancellationIntent)

        return baseIntent
    }
}