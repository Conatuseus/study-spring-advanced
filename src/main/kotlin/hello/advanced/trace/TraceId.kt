package hello.advanced.trace

import java.util.UUID

class TraceId(
    val id: String,
    val level: Int
) {
    constructor() : this(
        id = UUID.randomUUID().toString().substring(0, 8),
        level = 0
    )

    fun nextTraceId() = TraceId(id, level + 1)

    fun previousTraceId() = TraceId(id, level - 1)

    fun isFirstLevel(): Boolean = this.level == 0
}
