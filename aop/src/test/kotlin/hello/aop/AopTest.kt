package hello.aop

import hello.aop.order.OrderRepository
import hello.aop.order.OrderService
import hello.aop.order.aop.AspectV1
import hello.aop.order.aop.AspectV2
import hello.aop.order.aop.AspectV3
import hello.aop.order.aop.AspectV4Pointcut
import hello.aop.order.aop.AspectV5Order
import hello.aop.order.aop.AspectV6Advice
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.lang.IllegalStateException

//@Import(AspectV1::class)
//@Import(AspectV2::class)
//@Import(AspectV3::class)
//@Import(AspectV4Pointcut::class)
//@Import(AspectV5Order.LogAspect::class, AspectV5Order.TxAspect::class)
@Import(AspectV6Advice::class)
@SpringBootTest
class AopTest(
    @Autowired private val orderService: OrderService,
    @Autowired private val orderRepository: OrderRepository,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService))
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository))
    }

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Test
    fun exception() {
        Assertions.assertThatThrownBy { orderService.orderItem("ex") }
            .isInstanceOf(IllegalStateException::class.java)
    }
}