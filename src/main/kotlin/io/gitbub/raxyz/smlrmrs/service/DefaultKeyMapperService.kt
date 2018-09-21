package io.gitbub.raxyz.smlrmrs.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class DefaultKeyMapperService : KeyMapperService {
    private val map: MutableMap<Long, String> = ConcurrentHashMap()

    @Autowired
    lateinit var converter: KeyConverterService

    val sequence = AtomicLong(10000000L)

    override fun add(link: String): String {
        val id = sequence.getAndIncrement()
        val key = converter.idToKey(id)
        map[id] = link
        return key
    }

    override fun getLink(key: String): KeyMapperService.Get {
        val id = converter.keyToId(key)
        val result = map[id]

        return if (result == null) {
            KeyMapperService.Get.NotFound(key)
        } else {
            KeyMapperService.Get.Link(result)
        }
    }
}
