package hello.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory

@Aspect
class AspectV2 {

    private val log = LoggerFactory.getLogger(javaClass)

    // hello.aop.order 패키지 & 하위패키지
    @Pointcut("execution(* hello.aop.order..*(..))")    // point cut expression
    private fun allOrder() {}   // pointcut 시그니쳐

    @Around("allOrder()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature)   // join point 시그니처
        return joinPoint.proceed()
    }
}