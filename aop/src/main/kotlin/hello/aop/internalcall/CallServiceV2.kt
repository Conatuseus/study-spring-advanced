package hello.aop.internalcall

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class CallServiceV2(
//    private val applicationContext: ApplicationContext
    private val callServiceProvider: ObjectProvider<CallServiceV2>
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun external() {
        log.info("call external")
//        val callServiceV2 = applicationContext.getBean(CallServiceV2::class.java)
        val callServiceV2 = callServiceProvider.getObject()
        callServiceV2.internal()
    }

    fun internal() {
        log.info("call internal")
    }
}