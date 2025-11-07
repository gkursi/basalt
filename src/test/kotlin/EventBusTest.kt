import org.junit.jupiter.api.Test
import xyz.qweru.basalt.EventBus
import xyz.qweru.geo.core.event.Handler
import kotlin.test.assertEquals

class EventBusTest {
    private val bus = EventBus()

    @Test
    fun subscribeTest() {
        bus.subscribe(TestSubscriber)
        bus.post("test")
        assertEquals(TestSubscriber.result, "test")
    }

    private object TestSubscriber {
        var result: String = ""

        @Handler
        private fun stringEvent(event: String) {
            result = event
        }
    }
}