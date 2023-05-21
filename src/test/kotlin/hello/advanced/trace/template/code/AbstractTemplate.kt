package hello.advanced.trace.template.code

import org.slf4j.LoggerFactory

abstract class AbstractTemplate {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 실행
        call() // 상속
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info("resultTime = $resultTime")
    }

    abstract fun call()
}
