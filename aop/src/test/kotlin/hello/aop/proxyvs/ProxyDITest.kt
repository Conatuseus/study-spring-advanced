package hello.aop.proxyvs

import hello.aop.member.MemberService
import hello.aop.member.MemberServiceImpl
import hello.aop.proxyvs.code.ProxyDIAspect
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(ProxyDIAspect::class)
//@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"])   // JDK 동적프록시
@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"])   //  cglib 프록시
class ProxyDITest(
    @Autowired private val memberService: MemberService,
    @Autowired private val memberServiceImpl: MemberServiceImpl
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun go() {
        log.info("memberService class={}", memberService.javaClass)
        log.info("memberServiceImpl class={}", memberServiceImpl.javaClass)
        memberServiceImpl.hello("hello")
    }
}