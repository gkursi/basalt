package xyz.qweru.basalt.bench

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.benchmark.TearDown
import xyz.qweru.basalt.EventBus
import xyz.qweru.geo.core.event.Handler

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
class SubscribeBenchmark {

    val eventBus = EventBus()

    @Benchmark
    fun bench() {
        eventBus.subscribe(SampleSubscriber)
    }

    @TearDown
    fun clean() {
        eventBus.unsubscribe(SampleSubscriber)
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