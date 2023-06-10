package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.slf4j.LoggerFactory

class FieldLogTrace : LogTrace {

//    private var traceIdHolder: TraceId? =  null  // traceId 동기화, 동시성 이슈 발생
    private var traceIdHolder: TraceId? = null
    private val logger = LoggerFactory.getLogger(FieldLogTrace::class.java)

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EXCEPTION_PREFIX = "<X-"
    }

    private fun syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = TraceId()
            return
        }
        traceIdHolder = traceIdHolder?.nextTraceId()
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        // 로그 출력
        logger.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus?, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus?, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = status?.let { stopTimeMs - it.startTimeMs } ?: stopTimeMs
        val traceId: TraceId = status!!.traceId

        if (e == null) {
            logger.info(
                "[{}] {}{} time={}ms",
                traceId.id,
                addSpace(COMPLETE_PREFIX, traceId.level),
                status.message,
                resultTimeMs
            )
        } else {
            logger.info(
                "[{}] {}{} time={}ms ex={}",
                traceId.id,
                addSpace(EXCEPTION_PREFIX, traceId.level),
                status.message,
                resultTimeMs,
                e.toString()
            )
        }

        releaseTraceId()
    }

    private fun releaseTraceId() {
        if (traceIdHolder?.isFirstLevel() == true) {
            traceIdHolder = null
            return
        }
        traceIdHolder = traceIdHolder?.previousTraceId()
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }
}
