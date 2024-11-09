package com.youssef.kotlinflowts.models.kotlinflowts.utils

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

open class JsonList<T : Mappable>(
    private val wrapped: MutableList<MutableMap<String, Any?>>,
    private val factory: (MutableMap<String, Any?>) -> T
) : AbstractList<T>(), MutableList<T> {

    constructor(
        wrapped: Any?,
        factory: (MutableMap<String, Any?>) -> T
    ) : this(wrapped as MutableList<MutableMap<String, Any?>>, factory)

    override val size: Int get() = wrapped.size
    override fun addAll(elements: Collection<T>) = wrapped.addAll(elements.map { it.toMap() })

    override fun addAll(index: Int, elements: Collection<T>) = wrapped.addAll(index, elements.map { it.toMap() })

    override fun add(index: Int, element: T) = wrapped.add(index, element.toMap())

    override fun add(element: T) = wrapped.add(element.toMap())

    override fun get(index: Int): T = factory(wrapped[index])

    private fun entries() = wrapped.map { factory(it) }.toMutableList()

    override fun iterator(): MutableIterator<T> = entries().iterator()

    override fun listIterator(): MutableListIterator<T> = entries().listIterator()

    override fun listIterator(index: Int): MutableListIterator<T> = entries().listIterator(index)

    override fun removeAt(index: Int): T = factory(wrapped.removeAt(index))

    override fun set(index: Int, element: T): T = factory(wrapped.set(index, element.toMap()))

    override fun retainAll(elements: Collection<T>): Boolean = wrapped.retainAll(elements.map { it.toMap() })

    override fun removeAll(elements: Collection<T>): Boolean = wrapped.removeAll(elements.map { it.toMap() })

    override fun remove(element: T): Boolean = wrapped.remove(element.toMap())

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> =
        wrapped.subList(fromIndex, toIndex).map { factory(it) }.toMutableList()

    override fun clear() = wrapped.clear()
}