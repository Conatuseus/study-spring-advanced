package hello.advanced.trace.strategy.code.template

import org.slf4j.LoggerFactory

class TimeLogTemplate {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun execute(callback: () -> Unit) {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 실행
        callback()
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info("resultTime = $resultTime")
    }
}
