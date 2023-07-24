package hello.aop.internalcall

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CallServiceV1 {

    private val log = LoggerFactory.getLogger(javaClass)

    fun external() {
        log.info("call external")
    }

    fun internal() {
        log.info("call internal")
    }
}