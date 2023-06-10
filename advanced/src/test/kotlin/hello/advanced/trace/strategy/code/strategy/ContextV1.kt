package hello.advanced.trace.strategy.code.strategy

import org.slf4j.LoggerFactory

/**
 * 필드에 전략을 보관하는 방식
 */
class ContextV1(
    private val strategy: Strategy
) {
    /**
     * 람다식을 받을 수 있도록 함
     * 위 strategy를 제거하고, 함수형 타입으로만 받을 수 있도록 한다면 (아니면 Strategy로 받고, fun interface Strategy 로 변경하면 됨. V5는 이방식으로)
     * 사용은 아래와 같이 하면 된다.
     * val context1 = ContextV1 { StrategyLogic1().call() }
     * 기존 테스트를 다 변경하지 않기 위해 생성자만 추가했다.
     */
    constructor(strategy: () -> Unit) : this(
        object : Strategy {
            override fun call() {
                strategy()
            }
        }
    )

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 실행
        strategy.call() // 위임
//        strategy.call() // strategy Strategy 타입일 때
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info("resultTime = $resultTime")
    }
}
