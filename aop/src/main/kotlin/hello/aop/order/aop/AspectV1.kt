package hello.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory

@Aspect
class AspectV1 {

    private val log = LoggerFactory.getLogger(javaClass)

    // hello.aop.order 패키지 & 하위패키지
    @Around("execution(* hello.aop.order..*(..))")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature)   // join point 시그니처
        return joinPoint.proceed()
    }
}