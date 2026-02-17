package chat.amp.jmap.core

import kotlin.test.Test
import kotlin.test.assertTrue

class JvmSmokeTest {
    @Test
    fun jvmTestEnvironmentIsAvailable() {
        assertTrue(System.getProperty("java.version").isNotBlank())
    }
}
