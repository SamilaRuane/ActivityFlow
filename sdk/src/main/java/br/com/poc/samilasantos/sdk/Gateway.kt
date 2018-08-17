package br.com.poc.samilasantos.sdk

import android.app.PendingIntent
import android.content.Intent

interface Gateway {

    fun createCallerIntent (successCall: PendingIntent, cancellationCall: PendingIntent) : Intent

    fun wrapperSuccessIntent(intent: Intent): PendingIntent

    fun wrapperCancelationIntent (intent: Intent): PendingIntent

}