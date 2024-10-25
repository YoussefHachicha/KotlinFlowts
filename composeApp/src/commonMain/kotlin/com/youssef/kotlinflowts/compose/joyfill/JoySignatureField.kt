package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Redo
import androidx.compose.material.icons.outlined.Undo
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.compose.joyfill.utils.onDrawing
import com.youssef.kotlinflowts.compose.joyfill.utils.toByteArray
import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.manager.joyfill.Mode
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.SignatureComponent
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
internal fun JoySignatureComponent(
    editor: SignatureComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) {

    val component = remember(editor) { editor.component }

    var state by remember(component) {
        val s = when (val value = component.value) {
            null -> State.Empty
            "" -> State.Empty
            else -> State.Preview(value)
        }
        mutableStateOf(s)
    }
    Column(modifier = Modifier.fillMaxWidth().testTag(component.id)) {
        JoyTitle(component, modifier = Modifier.testTag("${component.id}-preview-title"))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .testTag("${component.id}-preview-body")
                .semantics { contentDescription = state.toContentDescription() }
                .border(
                    width = 1.dp,
                    color = LocalContentColor.current.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(4.dp)
                ).clickable {
                    if (component.disabled || mode == Mode.readonly) return@clickable
                    onSignal(Signal.Focus)
                    state = state.toCapturing()
                }
        ) {
            when (val s = state) {
                is State.Preview -> Preview(
                    url = s.url,
                    onClicked = {
                        if (component.disabled || mode == Mode.readonly) return@Preview
                        state = s.toCapturing()
                        onSignal(Signal.Focus)
                    }
                )

                is State.Capturing -> Capture(
                    component = component,
                    url = s.url,
                    onCaptured = {
                        state = if (it == null) State.Empty else State.Preview(it)
                        onSignal(Signal.Change(it))
                        onSignal(Signal.Blur(it))
                        editor.value = it
                    },
                    onCanceled = {
                        state = if (s.url == null) State.Empty else State.Preview(s.url)
                        onSignal(Signal.Blur(s.url))
                    }
                )

                is State.Empty -> {}
                else -> {}
            }
        }
    }
}

private sealed interface State {
    data object Empty : State
    data class Preview(val url: String) : State
    data class Capturing(val url: String?) : State

    fun toCapturing() = when (this) {
        is Empty -> Capturing(null)
        is Preview -> Capturing(url)
        is Capturing -> this
    }

    fun toContentDescription() = when (this) {
        is Empty -> "empty"
        is Capturing -> "updating url from $url"
        is Preview -> "previewing $url"
    }
}


@OptIn(ExperimentalEncodingApi::class)
@Composable
private fun Capture(
    component: SignatureComponent,
    url: String? = null,
    onCaptured: (String?) -> Unit,
    onCanceled: () -> Unit,
) {
    var value by remember { mutableStateOf(url) }
    val points = remember { mutableStateListOf<Offset>() }
    var drawing by remember { mutableStateOf(false) }
    var cleared by remember { mutableStateOf(false) }

    val paths = remember { mutableStateListOf<Path>() }

    var text by remember { mutableStateOf("") }

    val measurer = TextMeasurer(
        defaultFontFamilyResolver = LocalFontFamilyResolver.current,
        defaultDensity = LocalDensity.current,
        defaultLayoutDirection = LocalLayoutDirection.current
    )

    var size by remember { mutableStateOf<Size?>(null) }

    val color = LocalContentColor.current
    val style = LocalTextStyle.current.copy(fontSize = 60.sp, fontFamily = FontFamily.Cursive, color = color)

    Dialog(onDismissRequest = onCanceled, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(
            modifier = Modifier
                .testTag("${component.id}-capture")
                .padding(
                    vertical = 16.dp,
                    horizontal = 8.dp
                )
                .fillMaxWidth()
                .heightIn(min = 200.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                val v = value

                Row(modifier = Modifier.fillMaxWidth().height(60.dp), verticalAlignment = Alignment.CenterVertically) {
                    Row(horizontalArrangement = Arrangement.Start) { JoyTitle(component) }
                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                        val redo = remember { mutableStateListOf<Path>() }
                        var deleting by remember { mutableStateOf(false) }

                        if (paths.isNotEmpty() && !deleting) IconButton(onClick = {
                            val path = paths.last()
                            redo.add(path)
                            paths.remove(path)
                        }) {
                            Icon(Icons.Outlined.Undo, "Undo")
                        }

                        if (redo.isNotEmpty() && !deleting) IconButton(onClick = {
                            val path = redo.last()
                            paths.add(path)
                            redo.remove(path)
                        }) {
                            Icon(Icons.Outlined.Redo, "redo")
                        }


                        if (v != null || paths.isNotEmpty() || text.isNotEmpty()) DeleteOption(
                            component = component,
                            deleting = deleting,
                            onDelete = { deleting = true },
                            onConfirm = {
                                value = null
                                paths.clear()
                                text = ""
                                cleared = true
                                deleting = false
                            },
                            onCanceled = { deleting = false }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .onDrawing(
                            started = { drawing = true },
                            progressing = {
                                if (drawing) points.add(it)
                            },
                            ended = {
                                drawing = false
                                paths.add(points.toPath())
                                points.clear()
                            }
                        )
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = LocalContentColor.current,
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    when {
                        v != null && !drawing && paths.isEmpty() && text.isEmpty() -> Image(v, v)
                        else -> Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                            if (size == null) {
                                size = this.size
                            }
                            drawSignature(measurer, style, paths, points, text, color)
                        }
                    }
                }

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Type signature") },
                    modifier = Modifier.testTag("${component.id}-capture-text").fillMaxWidth().padding(top = 12.dp)
                )

                Text(
                    text = listOf(
                        "The parties agree that this agreement may be electronically signed.",
                        "The parties agree that the electronic signatures appearing on this agreement are the same",
                        "as handwritten signatures for the purposes of validity, enforceability, and admissibility."
                    ).joinToString(" "),
                    style = LocalTextStyle.current.copy(textAlign = TextAlign.Justify, fontSize = 12.sp, fontWeight = FontWeight(300)),
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                val density = LocalDensity.current
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                    OutlinedButton(
                        modifier = Modifier.testTag("${component.id}-capture-cancel"),
                        onClick = onCanceled,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier.testTag("${component.id}-capture-submit"),
                        onClick = {
                            if (text.isEmpty() && paths.isEmpty()) {
                                if (cleared) {
                                    return@Button onCaptured(null)
                                } else {
                                    return@Button
                                }
                            }
                            val width = size?.width?.toInt() ?: return@Button
                            val height = size?.height?.toInt() ?: return@Button

                            val scope = CanvasDrawScope()
                            val bitmap = ImageBitmap(width, height)
                            val canvas = androidx.compose.ui.graphics.Canvas(bitmap)
                            scope.draw(
                                density = density,
                                layoutDirection = LayoutDirection.Ltr,
                                canvas = canvas,
                                size = Size(width.toFloat(), height.toFloat())
                            ) {
                                drawSignature(measurer, style, paths, points, text, Color.Black)
                            }
                            onCaptured("data:image/png;base64," + Base64.encode(bitmap.toByteArray()))
                        },
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Submit")
                    }
                }
            }

        }
    }
}

@Composable
private fun DeleteOption(
    component: Component,
    deleting: Boolean,
    onDelete: () -> Unit,
    onCanceled: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!deleting) IconButton(
        modifier = Modifier.testTag("${component.id}-capture-delete"),
        onClick = onDelete
    ) {
        Icon(Icons.Outlined.Delete, "Delete", tint = Color.Red)
    } else {
        OutlinedButton(
            modifier = Modifier.testTag("${component.id}-capture-delete-cancel"),
            onClick = onCanceled,
            shape = RoundedCornerShape(8.dp),
        ) {
            Text("Cancel")
        }
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(
            modifier = Modifier.testTag("${component.id}-capture-delete-confirm"),
            onClick = onConfirm,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text("Confirm")
        }
    }
}

private fun List<Offset>.toPath() = Path().apply {
    forEachIndexed { index, point ->
        if (index == 0) {
            moveTo(point.x, point.y)
        } else {
            lineTo(point.x, point.y)
        }
    }
}

private fun DrawScope.drawSignature(
    measurer: TextMeasurer,
    style: TextStyle,
    paths: List<Path>,
    points: List<Offset>,
    text: String,
    color: Color,
) {
    if (text.isNotEmpty()) {
        val dimension = measurer.measure(text, style).size
        val pos = Offset(size.width - dimension.width, size.height - dimension.height) / 2f
        drawText(textMeasurer = measurer, topLeft = pos, text = text, style = style)
    } else {
        for (p in paths + points.toPath()) drawPath(p, color = color, alpha = 0.5f, style = Stroke(width = 4f))
    }
}

@Composable
private fun Preview(
    url: String,
    onClicked: (() -> Unit)? = null,
    margin: Dp = 0.dp,
    modifier: Modifier = Modifier,
) = Box(
    modifier = Modifier.fillMaxWidth()
        .padding(margin)
        .border(
            width = 2.dp,
            color = LocalContentColor.current.copy(alpha = 0.2f),
            shape = RoundedCornerShape(8.dp)
        ).padding(4.dp)
        .clickable { onClicked?.invoke() }
        .then(modifier)
) {
    Image(url, url)
}