package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class OrderRepositoryV2(
    private val trace: HelloTraceV2
) {

    fun save(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.beginSync(traceId, "OrderRepositoryV1.save()")
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생!")
            }
            sleep(1000)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
