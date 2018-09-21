package io.gitbub.raxyz.smlrmrs.service

interface KeyConverterService {
    fun idToKey(id: Long): String
    fun keyToId(key: String): Long
}
