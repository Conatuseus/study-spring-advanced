package hello.aop.exam.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory

@Aspect
class TraceAspect {
    private val log = LoggerFactory.getLogger(javaClass)

    @Before("@annotation(hello.aop.exam.annotation.Trace)")
    fun doTrace(joinPoint: JoinPoint) {
        val args = joinPoint.args
        log.info("[trace] {} args={}", joinPoint.signature, args)
    }
}