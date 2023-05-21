package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV1
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV1Test {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun strategyV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 실행
        logger.info("비지니스 로직1 실행")
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info("resultTime = $resultTime")
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 실행
        logger.info("비지니스 로직2 실행")
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info("resultTime = $resultTime")
    }

    /**
     * 전략패턴 사용
     */
    @Test
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val context3 = ContextV1 { strategyLogic1.call() }
        context3.execute()

        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = StrategyLogic2()
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV2() {
        val strategyLogic1 = object : Strategy {
            override fun call() {
                logger.info("비즈니스 로직1 실행")
            }
        }

        val context1 = ContextV1(object : Strategy {
            override fun call() {
                logger.info("비즈니스 로직1 실행")
            }
        })
        logger.info("strategyLogic1=${strategyLogic1.javaClass}")
        context1.execute()

        val strategyLogic2 = object : Strategy {
            override fun call() {
                logger.info("비즈니스 로직2 실행")
            }
        }

        val context2 = ContextV1(strategyLogic2)
        logger.info("strategyLogic2=${strategyLogic2.javaClass}")
        context2.execute()
    }

    @Test
    fun strategyV3() {
        val context1 = ContextV1(object : Strategy {
            override fun call() {
                logger.info("비즈니스 로직1 실행")
            }
        })
        context1.execute()

        val context2 = ContextV1(object : Strategy {
            override fun call() {
                logger.info("비즈니스 로직2 실행")
            }
        })
        context2.execute()
    }

    @Test
    fun strategyV4() {
        val context1 = ContextV1 { logger.info("비즈니스 로직1 실행") }
        context1.execute()

        val context2 = ContextV1 { logger.info("비즈니스 로직2 실행") }
        context2.execute()
    }
}
