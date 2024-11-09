package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youssef.kotlinflowts.models.kotlinflowts.components.RichTextComponent
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Composable
internal fun KfRichTextComponent(
    component: RichTextComponent,
) = Column(modifier = Modifier.testTag(component.id).fillMaxWidth()) {
    KfRichTextComponentImpl(component)
}

@Composable
internal fun ColumnScope.KfRichTextComponent(
    component: RichTextComponent,
) = Column(modifier = Modifier.testTag(component.id).fillMaxWidth()) {
    KfRichTextComponentImpl(component)
}


@Composable
internal fun RowScope.KfRichTextComponent(
    component: RichTextComponent,
) = Column(modifier = Modifier.testTag(component.id).weight(1f)) {
    KfRichTextComponentImpl(component)
}

@Composable
private fun KfRichTextComponentImpl(
    component: RichTextComponent,
) {
    DraftJsRichText(component.value)
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun DraftJsRichText(value: String?) {
    val codec = remember { Json { ignoreUnknownKeys = true } }
    val v = try {
        codec.decodeFromString(Value.serializer(), value ?: "")
    } catch (error: Throwable) {
        null
    }

    v?.blocks?.forEach { BlockView(it) }
}

@Composable
private fun BlockView(block: Block) {
    val text = buildAnnotatedString {
        append(block.text)
        for (range in block.inlineStyleRanges) {
            println("Range: $range")
            val style = when {
                range.style.startsWith("fontsize-") -> {
                    val size = range.style.removePrefix("fontsize-").toIntOrNull() ?: 16
                    SpanStyle(fontSize = size.sp)
                }

                range.style.startsWith("BOLD") -> {
                    SpanStyle(fontWeight = FontWeight.Bold)
                }

                else -> null
            }
            if (style == null) continue
            addStyle(style, start = range.offset, end = range.offset + range.length)
        }
    }

    Text(text, modifier = Modifier.fillMaxWidth())
}

@Serializable
private data class Value(
    val blocks: List<Block> = emptyList(),
)

@Serializable
private data class Block(
    val text: String = "",
    val inlineStyleRanges: List<StyleRange> = emptyList(),
)

@Serializable
private data class StyleRange(
    val offset: Int = 0,
    val length: Int = 0,
    val style: String,
)