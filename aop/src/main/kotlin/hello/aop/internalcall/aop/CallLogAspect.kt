package hello.aop.internalcall.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory

@Aspect
class CallLogAspect {
    private val log = LoggerFactory.getLogger(javaClass)

    @Before("execution(* hello.aop..internalcall..*.*(..))")
    fun doLog(joinPoint: JoinPoint) {
        log.info("aop={}", joinPoint.signature)
    }
}