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

/**
 * Essa Activity é a activity principal do SDK aqui os pending intents são recuperados e uma nova
 * chamada é realizada para uma activity externa.
 * */
class ManagerActivity : AppCompatActivity() {

    private lateinit var successIntent: PendingIntent
    private lateinit var cancellationIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
        val intent = createExternalIntent()

        /**
         * Coloquei o botão para que pudesse controlar o fluxo, tinha colocado um handler com timer,
         * mas acredito que tava executando em background e executava a ação mesmo depois que eu
         * clicava no back.
         * */
        sendButton.setOnClickListener { startActivity(intent) }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()

        /**
         * Aqui são recebidos os dados da intent externa, vale ressaltar que quando essa activity é
         * iniciada pela primeira vez também passa por esse método, mas tô assumindo que em um primeiro
         * momento esses dados não existem e logo não entra na execução da função que só deve ser
         * executada depois da comunicação com o mundo externo.
         * */
        val data: String? = intent.extras.getString(EXTRAS_KEY) ?: ""
        if (data != null && data.isNotEmpty()) {
            infoText.visibility = View.VISIBLE
            sendButton.setOnClickListener {
                /**
                 * Chama a Activity de Sucesso
                 * */
                successIntent.send(this, 0, intent)
                finish()
            }
        }
    }

    private fun createExternalIntent(): Intent {

        /**
         * Uma vez que é desejável que depois de executar a ação no mundo externo o fluxo volte para
         * essa activity com o resultado da ação para que seja executada uma nova tarefa, é criado um
         * novo PendingIntent para sucesso, mas dessa vez o redirect é realizado para esta activity.
         *
         * As flags FLAG_ACTIVITY_SINGLE_TOP e FLAG_ACTIVITY_CLEAR_TOP me garantem que uma vez que
         * essa activity é iniciada e está na stack ela é reutilizada e todas as activities que
         * estiverem acima dela são finalizadas.
         *
         * */
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

    /**
     * Chama a Activity de Cancelamento
     * */
    override fun onBackPressed() {
        cancellationIntent.send()
        super.onBackPressed()
        finish()
    }
}
