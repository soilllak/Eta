package io.github.mangi.eta.agent.runtime

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * 实时通知按钮与补充输入的落点：只把结果转成运行态操作就返回，
 * 不在接收器里做任何耗时工作，避免拖慢通知动作的响应。
 */
internal class AgentNotificationActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val supplement = RemoteInput.getResultsFromIntent(intent)
            ?.getCharSequence(KEY_SUPPLEMENT)
            ?.toString()
        AgentRuntimeService.dispatchNotificationAction(intent.action.orEmpty(), supplement)
    }

    companion object {
        const val ACTION_SUPPLEMENT = "io.github.mangi.eta.action.SUPPLEMENT_AGENT_RUN"
        const val KEY_SUPPLEMENT = "eta_supplement_text"
    }
}
