package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilder
import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.joyfill.internal.DocumentBuilderImpl
import com.youssef.kotlinflowts.models.joyfill.File
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.toDocument
import com.youssef.kotlinflowts.models.joyfill.toMutableDocument
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.ID

fun buildDocument(builder: DocumentBuilder.() -> Unit): Document {
    val generator = IdentityGenerator.default
    val uid = generator.generate()
    val document = mutableMapOf<String, Any?>(
        ID to uid,
        Document::identifier.name to "document-$uid",
        Document::name.name to "New Document",
        Document::files.name to mutableListOf<File>(),
        Document::fields.name to mutableListOf<Field>()
    ).toDocument().toMutableDocument()
    val db = DocumentBuilderImpl(document, generator)
    db.builder()
    return db.document
}

fun plot(
    identity: IdentityGenerator = IdentityGenerator.default,
    builder: LineBuilder.() -> Unit
): List<Line> = LineBuilderImpl(identity).apply(builder).lines