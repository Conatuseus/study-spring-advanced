package hello.advanced.app.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OrderServiceV1(
    private val orderRepository: OrderRepositoryV1,
    private val trace: HelloTraceV1
) {

    fun orderItem(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderServiceV1.orderItem()")
            orderRepository.save(itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
