package hello.advanced.app.v3

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class OrderRepositoryV3(
    private val trace: LogTrace
) {

    fun save(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderRepository.save()")
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
