package br.com.poc.samilasantos.sdk

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class GatewayImpl(private val context: Context) : Gateway {

    private val CANCEL_INTENT = "cancel_intent"
    private val SUCCESS_INTENT = "success_intent"

    override fun createCallerIntent(
        successCall: PendingIntent,
        cancellationCall: PendingIntent
    ): Intent {
        val baseIntent = Intent(context, ManagerActivity::class.java)
        baseIntent.putExtra(SUCCESS_INTENT, successCall)
        baseIntent.putExtra(CANCEL_INTENT, cancellationCall)

        return baseIntent
    }

    override fun wrapperSuccessIntent(intent: Intent): PendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    override fun wrapperCancelationIntent(intent: Intent): PendingIntent =
        PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}