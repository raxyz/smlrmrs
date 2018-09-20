package io.gitbub.raxyz.smlrmrs.service

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class DefaultKeyMapperService : KeyMapperService {

    private val map: MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String): KeyMapperService.Add {
        return if (map.containsKey(key)) {
            KeyMapperService.Add.AlreadyExist(key)
        } else {
            map[key] = link
            KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String) = if (map.containsKey(key)) {
        KeyMapperService.Get.Link(map[key]!!)
    } else {
        KeyMapperService.Get.NotFound(key)
    }
}