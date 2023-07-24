package hello.aop.internalcall

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class InternalService {
    private val log = LoggerFactory.getLogger(javaClass)

    fun internal() {
        log.info("call internal")
    }
}