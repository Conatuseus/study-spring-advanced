package hello.aop.pointcut

import hello.aop.member.MemberService
import hello.aop.member.annotation.ClassAop
import hello.aop.member.annotation.MethodAop
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(ParameterTest.ParameterAspect::class)
@SpringBootTest
class ParameterTest(
    @Autowired private val memberService: MemberService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun success() {
        log.info("memberService Proxy={}", memberService.javaClass)
        memberService.hello("helloA")
    }

    @Aspect
    class ParameterAspect {

        private val log = LoggerFactory.getLogger(javaClass)

        @Pointcut("execution(* hello.aop.member..*.*(..))")
        private fun allMember() {}

        @Around("allMember()")
        fun logArgs1(joinPoint: ProceedingJoinPoint): Any? {
            val arg1 = joinPoint.args[0]
            log.info("[logArgs1]{}, arg={}", joinPoint.signature, arg1)
            return joinPoint.proceed()
        }

        @Around("allMember() && args(arg,..)")
        fun logArgs2(joinPoint: ProceedingJoinPoint, arg: Any): Any? {
            log.info("[logArgs2]{}, arg={}", joinPoint.signature, arg)
            return joinPoint.proceed()
        }

        @Before("allMember() && args(arg,..)")
        fun logArgs3(arg: String) {
            log.info("[logArgs3] arg={}", arg)
        }

        // this로 넘어오는건 프록시
        @Before("allMember() && this(obj)")
        fun thisArgs(joinPoint: JoinPoint, obj: MemberService) {
            log.info("[this]{}, obj={}", joinPoint.signature, obj.javaClass)
        }

        // target은 프록시를 까서 나온 실제 대상
        @Before("allMember() && target(obj)")
        fun targetArgs(joinPoint: JoinPoint, obj: MemberService) {
            log.info("[target]{}, obj={}", joinPoint.signature, obj.javaClass)
        }

        @Before("allMember() && @target(annotation)")
        fun atTarget(joinPoint: JoinPoint, annotation: ClassAop) {
            log.info("[@target]{}, annotation={}", joinPoint.signature, annotation)
        }

        @Before("allMember() && @within(annotation)")
        fun atWithin(joinPoint: JoinPoint, annotation: ClassAop) {
            log.info("[@within]{}, annotation={}", joinPoint.signature, annotation)
        }

        @Before("allMember() && @annotation(annotation)")
        fun atAnnotation(joinPoint: JoinPoint, annotation: MethodAop) {
            log.info("[@annotation]{}, annotationValue={}", joinPoint.signature, annotation.value)
        }
    }
}