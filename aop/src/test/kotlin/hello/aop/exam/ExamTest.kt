package hello.aop.exam

import hello.aop.exam.aop.RetryAspect
import hello.aop.exam.aop.TraceAspect
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

//@Import(TraceAspect::class)
@Import(TraceAspect::class, RetryAspect::class)
@SpringBootTest
class ExamTest(
    @Autowired private val examService: ExamService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun test() {
        for (i in 0 until 5) {
            log.info("client request i={}", i)
            examService.request("data$i")
        }
    }
}