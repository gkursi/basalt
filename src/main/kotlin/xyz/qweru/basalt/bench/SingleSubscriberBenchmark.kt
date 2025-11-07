package xyz.qweru.basalt.bench

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import xyz.qweru.basalt.EventBus
import xyz.qweru.geo.core.event.Handler

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
class SingleSubscriberBenchmark {

    val eventBus = EventBus()

    @Setup
    fun setup() {
        eventBus.subscribe(SampleSubscriber)
    }

    @Benchmark
    fun bench() {
        eventBus.post("")
    }

    private object SampleSubscriber {
        @Handler
        private fun stringHandler(e: String) {}
    }
}