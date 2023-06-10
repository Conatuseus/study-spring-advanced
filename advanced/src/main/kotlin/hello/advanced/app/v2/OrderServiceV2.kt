package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2,
    private val trace: HelloTraceV2
) {

    fun orderItem(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.beginSync(traceId, "OrderServiceV1.orderItem()")
            orderRepository.save(status.traceId, itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
