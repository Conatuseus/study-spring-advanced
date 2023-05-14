package hello.advanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class ThreadLocalService {
    private var nameStore: ThreadLocal<String> = ThreadLocal()
    private val logger = LoggerFactory.getLogger(ThreadLocalService::class.java)

    fun logic(name: String): String {
        logger.info("저장 name={} -> nameStore={}", name, nameStore.get())
        nameStore.set(name)
        Thread.sleep(1000)
        logger.info("조회 nameStore={}", nameStore.get())
        return nameStore.get()
    }
}
