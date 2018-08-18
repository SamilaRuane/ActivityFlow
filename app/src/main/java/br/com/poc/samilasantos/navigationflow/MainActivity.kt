package br.com.poc.samilasantos.navigationflow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.poc.samilasantos.sdk.SdkGateway
import kotlinx.android.synthetic.main.activity_main.button

/**
 *
 * Essa activity representa o App cliente, ela se comunica com o SDK pelo contrato
 * (objeto Gateway, que na pratica seria uma interface) que o sdk fornece.
 *
 *
 * */
class MainActivity : AppCompatActivity() {

    /**
     * Esse atributo é o elo da aplicação cliente e o SDK
     * */
    private val gateway by lazy { SdkGateway }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { callSdk() }
    }

    /**
     *
     * Esse método cria as intents de sucesso e cancelamento, estas intents guarda o redirect para
     * as telas que devem ser chamadas para caso o fluxo aconteça com sucesso ou o usuário cancele
     * a ação no meio do caminho.
     *
     * Nestas mesmas intents é usada a FLAG_ACTIVITY_CLEAR_TASK, a qual é
     * responsável por limpar a stack antes de iniciar a activity.
     *
     * */
    private fun callSdk() {
        val successIntent = Intent(this, SuccessActivity::class.java)
        val cancellationIntent = Intent(this, CancellationActivity::class.java)

        successIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        cancellationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val baseIntent = gateway.createCallerIntent(this, successIntent, cancellationIntent)

        startActivity(baseIntent)
        finish()
    }
}
