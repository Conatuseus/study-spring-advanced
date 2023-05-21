package hello.advanced.trace.template.code

import org.slf4j.LoggerFactory

class SubClassLogic2 : AbstractTemplate() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun call() {
        logger.info("비지니스 로직2 실행")
    }
}
