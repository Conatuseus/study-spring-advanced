package hello.aop.order.aop

import org.aspectj.lang.annotation.Pointcut


class Pointcuts {

    // hello.aop.order 패키지 & 하위패키지
    @Pointcut("execution(* hello.aop.order..*(..))")    // point cut expression
    fun allOrder() {}   // pointcut 시그니쳐

    // 클래스명 패턴이 *Service 인 것
    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {}

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    fun orderAndService() {}
}