package io.gitbub.raxyz.smlrmrs.service

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DefaultKeyConverterServiceTest {

    val service: KeyConverterService = DefaultKeyConverterService()

    @Test
    fun givenIdMustBeConvertedBothWays() {
        val rand = Random()
        for (i in 1..1000L) {
            val initialId = Math.abs(rand.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)

            assertEquals(initialId, id)

        }
    }
}