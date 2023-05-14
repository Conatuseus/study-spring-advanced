package hello.advanced.app.v3

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OrderServiceV3(
    private val orderRepository: OrderRepositoryV3,
    private val trace: LogTrace
) {

    fun orderItem(itemId: String) {
        var status: TraceStatus? = null

        try {
            status = trace.begin("OrderService.orderItem()")
            orderRepository.save(itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
