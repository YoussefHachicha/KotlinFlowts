package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ListBasedComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal abstract class AbstractListBasedComponentEditor<V : Mappable>(
    app: App,
    override val comp: ListBasedComponent<V>,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<ListBasedComponent<V>>(app, comp, onChange), ListBasedComponentEditor<V> {

    override val value = Value()

    inner class Value : AbstractList<V>(), MutableList<V> {
        private val source get() = this@AbstractListBasedComponentEditor.comp.value
        override val size: Int get() = source.size

        private fun notifyChange() {
            notifyChange(map { it.toMap() }.toMutableList())
        }

        private inline fun <R> mutate(block: () -> R): R {
            val result = block()
            notifyChange()
            return result
        }

        override fun clear() = mutate { source.clear() }

        override fun addAll(elements: Collection<V>) = mutate { source.addAll(elements) }

        override fun addAll(index: Int, elements: Collection<V>) = mutate { source.addAll(index, elements) }

        override fun add(index: Int, element: V) = mutate { source.add(index, element) }

        override fun add(element: V): Boolean = mutate { source.add(element) }

        override fun get(index: Int): V = source[index]

        override fun iterator(): MutableIterator<V> = source.iterator()

        override fun listIterator(): MutableListIterator<V> = source.listIterator()

        override fun listIterator(index: Int): MutableListIterator<V> = source.listIterator(index)

        override fun removeAt(index: Int): V = mutate { source.removeAt(index) }

        override fun set(index: Int, element: V): V = mutate { source.set(index, element) }

        override fun retainAll(elements: Collection<V>): Boolean = mutate { source.removeAll(elements) }

        override fun removeAll(elements: Collection<V>): Boolean = mutate { source.removeAll(elements) }

        override fun remove(element: V): Boolean = mutate { source.remove(element) }

        override fun subList(fromIndex: Int, toIndex: Int): MutableList<V> = source.subList(fromIndex, toIndex)
    }
}