package hello.aop.proxyvs.code

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory

@Aspect
class ProxyDIAspect {

    private val log = LoggerFactory.getLogger(javaClass)

    @Before("execution(* hello.aop..*.*(..))")
    fun doTrace(joinPoint: JoinPoint) {
        log.info("[proxyDIAdvice] {}", joinPoint.signature)
    }
}