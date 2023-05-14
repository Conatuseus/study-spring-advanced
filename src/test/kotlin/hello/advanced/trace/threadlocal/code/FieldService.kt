package hello.advanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class FieldService {
    private var nameStore: String? = null
    private val logger = LoggerFactory.getLogger(FieldService::class.java)

    fun logic(name: String): String {
        logger.info("저장 name={} -> nameStore={}", name, nameStore)
        nameStore = name
        Thread.sleep(1000)
        logger.info("조회 nameStore={}", nameStore)
        return nameStore!!
    }
}
