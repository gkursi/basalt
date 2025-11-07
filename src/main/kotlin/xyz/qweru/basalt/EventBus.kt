package xyz.qweru.basalt

import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap
import xyz.qweru.geo.core.event.Handler
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaMethod

class EventBus {
    /**
     * Class containing handlers to a map of event classes to a list of their respective handlers.
     * Event type references are stored as java classes, because the hashes of KClasses
     * sourced from object::class and the KClasses sourced from reflection differ.
     */
    private val handlerCache = ConcurrentHashMap<KClass<out Any>, Reference2ReferenceOpenHashMap<Class<out Any>, ObjectArrayList<Handle>>>()
    private val handles = ConcurrentHashMap<Class<out Any>, CopyOnWriteArrayList<Handle>>()

    /**
     * Currently you cannot subscribe multiple instances of a class at the same time.
     * This can be easily fixed, however the result is slower, and, since I don't need
     * it, I've decided to not implement it.
     */
    fun subscribe(obj: Any, forceRescan: Boolean = false) {
        scan(obj, forceRescan)
        var i = 0
        handlerCache[obj::class]?.forEach { event, handlersInObj ->
            val eventHandles = getHandles(event)
            val init = eventHandles.size
            handlersInObj.forEach { addHandle(obj, it, eventHandles) }
            i += eventHandles.size - init
        }
        println("Subscribed $i handles")
    }

    private fun addHandle(obj: Any, thisHandle: Handle, list: CopyOnWriteArrayList<Handle>) {
        thisHandle.instance = obj
        var i = 0
        for (handle in list) {
            if (handle.priority < thisHandle.priority) break
            i++
        }
        list.add(i, thisHandle)
    }

    private fun getHandles(event: Class<out Any>) = handles.computeIfAbsent(event) { CopyOnWriteArrayList() }.also {
        println("Handles: ${it.size}")
    }

    fun unsubscribe(obj: Any) {
        var i = 0
        for (infos in handles.values) {
            // replacing this with a binary search based on the priority might be faster
            infos.removeIf { (it.instance == obj).also { b ->
                if (b) i++
            } }
        }
        println("Unsubscribed $i handles")
    }

    fun scan(obj: Any, forceRescan: Boolean = false) {
        if (handlerCache.contains(obj::class) && !forceRescan) return
        val cache = Reference2ReferenceOpenHashMap<Class<out Any>, ObjectArrayList<Handle>>()

        for (function in obj::class.declaredMemberFunctions) {
            val tag = function.findAnnotation<Handler>()
            if (tag == null) continue
            if (function.parameters.size != 2)
                throw IllegalArgumentException(
                    "Handler method ${function.name}" +
                        " can only have 1 parameter" +
                        " (has ${function.parameters.size - 1})")

            val kClass = function.parameters[1].type.classifier!! as KClass<*>
            cache.computeIfAbsent(kClass.java) { ObjectArrayList() }
                .add(Handle(null, unwrap(function), tag.priority))
        }

        if (!cache.isEmpty()) handlerCache.put(obj::class, cache)
    }

    fun <T> post(event: T): T {
        check(event != null) { "Event cannot be null" }
        handles[event::class.java]?.forEach { info -> info.func.invoke(info.instance, event) }
        return event
    }

    fun <T : Cancellable> post(event: T): T {
        check(true) { "Event cannot be null" }
        event.cancelled = false
        handles[event::class.java]?.let {
            for (info in it) {
                info.func.invoke(info.instance, event)
                if (event.cancelled) break
            }
        }
        return event
    }

    /**
     * Clears all caches and handles
     */
    fun clear() {
        handles.clear()
        handlerCache.clear()
    }

    private data class Handle(var instance: Any?, val func: MethodHandle, val priority: Int) {
        override fun equals(other: Any?): Boolean {
            if (other is Handle && other.instance == instance)
                return true
            return super.equals(other)
        }

        override fun hashCode(): Int =  31 * (instance?.hashCode() ?: 0)
    }

    private fun unwrap(kFunction: KFunction<*>): MethodHandle {
        return MethodHandles.lookup().unreflect(kFunction.javaMethod!!.also { it.isAccessible = true })
    }

}