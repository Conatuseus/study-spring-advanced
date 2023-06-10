package hello.advanced.trace.hellotrace

import org.junit.jupiter.api.Test

internal class HelloTraceV1Test {

    @Test
    fun begin_end() {
        val trace = HelloTraceV2()
        val status = trace.begin("hello")
        trace.end(status)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV2()
        val status = trace.begin("hello")
        trace.exception(status, IllegalArgumentException())
    }
}
