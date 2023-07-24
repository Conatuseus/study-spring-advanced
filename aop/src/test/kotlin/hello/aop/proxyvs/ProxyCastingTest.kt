package hello.aop.proxyvs

import hello.aop.member.MemberService
import hello.aop.member.MemberServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.aop.framework.ProxyFactory

class ProxyCastingTest {

    @Test
    fun jdkProxy() {
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = false // jdk 동적 프록시

        // 프록시를 인터페이스로 캐스팅 성공
        val memberServiceProxy = proxyFactory.proxy as MemberService

        // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 발생
        assertThrows<ClassCastException> { memberServiceProxy as MemberServiceImpl }
    }

    @Test
    fun cglibProxy() {
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true // cglib

        // 프록시를 인터페이스로 캐스팅 성공
        val memberServiceProxy = proxyFactory.proxy as MemberService

        // cglib 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 발생
        memberServiceProxy as MemberServiceImpl
    }
}