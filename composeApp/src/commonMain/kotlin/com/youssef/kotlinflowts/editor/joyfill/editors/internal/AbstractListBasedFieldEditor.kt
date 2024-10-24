package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ListBasedFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.fields.ListBasedField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal abstract class AbstractListBasedFieldEditor<V : Mappable>(
    document: Document,
    override val field: ListBasedField<V>,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyFieldEditor<ListBasedField<V>>(document, field, onChange), ListBasedFieldEditor<V> {

    override val value = Value()

    inner class Value : AbstractList<V>(), MutableList<V> {
        private val source get() = this@AbstractListBasedFieldEditor.field.value
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