package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

class StrategyLogic1 : Strategy {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun call() {
        logger.info("비지니스 로직1 실행")
    }
}
