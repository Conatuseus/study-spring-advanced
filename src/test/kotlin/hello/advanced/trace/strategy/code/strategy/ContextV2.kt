package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

/**
 * 전략을 파라미터로 전달 받는 방식
 */
class ContextV2 {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 실행
        strategy.call() // strategy가 Strategy 타입일 때
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info("resultTime = $resultTime")
    }

    // for 람다. 위에 Strategy 를 받는 걸 제거하면, contextV2Test에서
    // context.execute { strategyV1() } 이렇게 사용
    fun execute(strategy: () -> Unit) {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 실행
        strategy() // strategy가 Strategy 타입일 때
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info("resultTime = $resultTime")
    }
}
