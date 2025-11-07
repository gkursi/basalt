package xyz.qweru.geo.core.event

import xyz.qweru.basalt.EventPriority

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Handler(val priority: Int = EventPriority.NONE)
