package hello.advanced.trace.template

import hello.advanced.trace.template.code.AbstractTemplate
import hello.advanced.trace.template.code.SubClassLogic1
import hello.advanced.trace.template.code.SubClassLogic2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TemplateMethodTest {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun templateMethodV0() {
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
     * 템플릿메서드 패턴 적용
     */
    @Test
    fun templateMethodV1() {
        val template1: AbstractTemplate = SubClassLogic1()
        template1.execute()

        val template2: AbstractTemplate = SubClassLogic2()
        template2.execute()
    }

    @Test
    fun templateMethodV2() {
        val template1 = object : AbstractTemplate() {
            override fun call() {
                logger.info("비지니스 로직1 실행")
            }
        }
        logger.info("클래스 이름1=${template1.javaClass}")
        template1.execute()

        val template2 = object : AbstractTemplate() {
            override fun call() {
                logger.info("비지니스 로직2 실행")
            }
        }

        logger.info("클래스 이름2=${template2.javaClass}")
        template2.execute()
    }
}
