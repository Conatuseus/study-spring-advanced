package hello.advanced

import hello.advanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    fun logTrace() = ThreadLocalLogTrace()
}
