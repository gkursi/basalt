package xyz.qweru.basalt.bench

import kotlinx.benchmark.*
import xyz.qweru.basalt.EventBus
import xyz.qweru.geo.core.event.Handler

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
class SubscribeBenchmark {

    var eventBus = EventBus()

    @Benchmark
    fun bench() {
        eventBus.subscribe(SampleSubscriber)
        eventBus.unsubscribe(SampleSubscriber) // required due to silly jmh code
    }

    private object SampleSubscriber {
        @Handler
        private fun handlerMethod(o: String) {}
        @Handler
        private fun handlerMethod1(o: Int) {}
        @Handler
        private fun handlerMethod2(o: Boolean) {}
        @Handler
        private fun handlerMethod3(o: String) {}
        @Handler
        private fun handlerMethod4(o: String) {}
    }
}