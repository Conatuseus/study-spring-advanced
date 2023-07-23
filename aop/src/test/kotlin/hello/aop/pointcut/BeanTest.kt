package hello.aop.pointcut

import hello.aop.order.OrderService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(BeanTest.BeanAspect::class)
@SpringBootTest
class BeanTest(
    @Autowired private val orderService: OrderService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Aspect
    class BeanAspect {
        private val log = LoggerFactory.getLogger(javaClass)

        @Around("bean(orderService) || bean(*Repository)")
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[bean] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }
}