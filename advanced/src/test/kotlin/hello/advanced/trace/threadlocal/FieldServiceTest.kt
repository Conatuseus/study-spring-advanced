package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.FieldService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class FieldServiceTest {

    private val logger = LoggerFactory.getLogger(FieldServiceTest::class.java)
    private val fieldService: FieldService = FieldService()

    @Test
    fun field() {
        logger.info("main start!")

        val userA = Runnable { fieldService.logic("userA") }
        val userB = Runnable { fieldService.logic("userB") }

        val threadA = Thread(userA)
        threadA.name = "Thread-A"
        val threadB = Thread(userB)
        threadB.name = "Thread-B"

        threadA.start()
//        Thread.sleep(2000)

        threadB.start()
        Thread.sleep(2000)
    }
}
