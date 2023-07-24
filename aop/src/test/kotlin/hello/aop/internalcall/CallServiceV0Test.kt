package hello.aop.internalcall

import hello.aop.internalcall.aop.CallLogAspect
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import


@Import(CallLogAspect::class)
@SpringBootTest
class CallServiceV0Test(
    @Autowired private val callServiceV0: CallServiceV0
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun external() {
        callServiceV0.external()
    }

    @Test
    fun internal() {
        callServiceV0.internal()
    }
}