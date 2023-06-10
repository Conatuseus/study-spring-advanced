package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

class StrategyLogic2 : Strategy {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun call() {
        logger.info("비지니스 로직2 실행")
    }
}
