package chat.amp.jmap.core

import kotlin.test.Test
import kotlin.test.assertEquals

class JmapCoreConstantsTest {
    @Test
    fun coreCapabilityMatchesRfcConstant() {
        assertEquals("urn:ietf:params:jmap:core", JmapCoreConstants.CORE_CAPABILITY)
    }
}
