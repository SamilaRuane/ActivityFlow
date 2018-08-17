package br.com.poc.samilasantos.sdk

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import br.com.poc.samilasantos.external.Keys.CANCELLATION_INTENT_KEY
import br.com.poc.samilasantos.external.Keys.CANCELLATION_INTENT_REQUEST_CODE
import br.com.poc.samilasantos.external.Keys.SUCCESS_INTENT_KEY
import br.com.poc.samilasantos.external.Keys.SUCCESS_INTENT_REQUEST_CODE

object SdkGateway {

    /**
     * Esse método é responsável por encapsular a chamada para a activity do sdk e criar as
     * pending intent para as intents de sucesso e erro.
     *
     * As pending intents são enviadas como extras para a Activity do sdk
     *
     * */
    fun createCallerIntent(
        context: Context,
        successIntent: Intent,
        cancellationIntent: Intent
    ): Intent {
        val baseIntent = Intent(context, ManagerActivity::class.java)
        val successCall =
            PendingIntent.getActivity(context, SUCCESS_INTENT_REQUEST_CODE, successIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val cancellationCall = PendingIntent.getActivity(
            context,
            CANCELLATION_INTENT_REQUEST_CODE,
            cancellationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        baseIntent.putExtra(SUCCESS_INTENT_KEY, successCall)
        baseIntent.putExtra(CANCELLATION_INTENT_KEY, cancellationCall)

        return baseIntent
    }
}