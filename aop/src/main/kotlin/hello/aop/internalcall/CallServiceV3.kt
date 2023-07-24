package hello.aop.internalcall

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

// 스프링에서 제일 권장하는 방법
/**
 * 구조를 변경
 */
@Component
class CallServiceV3(
    private val internalService: InternalService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun external() {
        log.info("call external")
        internalService.internal()
    }
}