package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.ThreadLocalService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ThreadLocalServiceTest {

    private val logger = LoggerFactory.getLogger(ThreadLocalServiceTest::class.java)
    private val threadLocalService: ThreadLocalService = ThreadLocalService()

    @Test
    fun field() {
        logger.info("main start!")

        val userA = Runnable { threadLocalService.logic("userA") }
        val userB = Runnable { threadLocalService.logic("userB") }

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
