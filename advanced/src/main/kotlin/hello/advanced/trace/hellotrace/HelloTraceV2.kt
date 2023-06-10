package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class HelloTraceV2 {
    private val logger = LoggerFactory.getLogger(HelloTraceV2::class.java)

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EXCEPTION_PREFIX = "<X-"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        // 로그 출력
        logger.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun beginSync(beforeTraceId: TraceId, message: String): TraceStatus {
        val nextTraceId = beforeTraceId.nextTraceId()
        val startTimeMs = System.currentTimeMillis()
        // 로그 출력
        logger.info("[{}] {}{}", nextTraceId.id, addSpace(START_PREFIX, nextTraceId.level), message)
        return TraceStatus(nextTraceId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(status: TraceStatus?, e: Exception) {
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
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }
}
