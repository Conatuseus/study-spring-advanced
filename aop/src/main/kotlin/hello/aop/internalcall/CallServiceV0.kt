package hello.aop.internalcall

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CallServiceV0 {

    private val log = LoggerFactory.getLogger(javaClass)

    fun external() {
        log.info("call external")
        internal()  // 내부 메서드 호출
    }

    fun internal() {
        log.info("call internal")
    }
}