package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV2
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV2Test {
    private val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * 전략 패턴 적용
     */
    @Test
    fun strategyV1() {
        val context = ContextV2()
        context.execute(StrategyLogic1())
        context.execute(StrategyLogic2())
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    fun strategyV2() {
        val context = ContextV2()
        context.execute(object : Strategy {
            override fun call() {
                logger.info("비지니스 로직1 실행")
            }
        })
        context.execute(object : Strategy {
            override fun call() {
                logger.info("비지니스 로직2 실행")
            }
        })
    }

    /**
     * 전략 패턴 익명 내부 클래스2, 람다
     */
    @Test
    fun strategyV3() {
        val context = ContextV2()
        context.execute { logger.info("비지니스 로직2 실행") }
        context.execute { logger.info("비지니스 로직2 실행") }
    }
}
