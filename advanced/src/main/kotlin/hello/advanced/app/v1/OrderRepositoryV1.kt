package hello.advanced.app.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class OrderRepositoryV1(
    private val trace: HelloTraceV1
) {

    fun save(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderRepositoryV1.save()")
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
