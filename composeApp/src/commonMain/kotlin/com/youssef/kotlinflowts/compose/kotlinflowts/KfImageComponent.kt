package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.sharp.ArrowCircleRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.compose.kotlinflowts.internal.ActiveFocusState
import com.youssef.kotlinflowts.compose.kotlinflowts.internal.InActiveFocusState
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileBasedComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment

@Composable
internal fun KfImageComponent(
    editor: FileBasedComponentEditor,
    mode: Mode,
    onUpload: (suspend () -> List<String>)? = null,
    onSignal: (Signal<List<Attachment>>) -> Unit,
) = Column(modifier = Modifier.fillMaxWidth()) {
    KfImageComponentImpl(editor, mode, onUpload, onSignal)
}

@Composable
internal fun ColumnScope.KfImageComponent(
    editor: FileBasedComponentEditor,
    mode: Mode,
    onUpload: (suspend () -> List<String>)? = null,
    onSignal: (Signal<List<Attachment>>) -> Unit,
) = Column(modifier = Modifier.fillMaxWidth()) {
    KfImageComponentImpl(editor, mode, onUpload, onSignal)
}

@Composable
internal fun RowScope.KfImageComponent(
    editor: FileBasedComponentEditor,
    mode: Mode,
    onUpload: (suspend () -> List<String>)? = null,
    onSignal: (Signal<List<Attachment>>) -> Unit,
) = Column(modifier = Modifier.weight(1f)) {
    KfImageComponentImpl(editor, mode, onUpload, onSignal)
}

@Composable
private fun KfImageComponentImpl(
    editor: FileBasedComponentEditor,
    mode: Mode,
    onUpload: (suspend () -> List<String>)? = null,
    onSignal: (Signal<List<Attachment>>) -> Unit,
) {
    val component = remember(editor) { editor.comp }

    Text(
        component.title,
        modifier = Modifier.testTag("${component.id}-preview-title").padding(bottom = 8.dp)
    )

    RawImageComponent(
        id = component.id,
        title = component.title,
        uploaded = editor.value,
        readonly = component.disabled || mode == Mode.readonly,
        onUpload = onUpload,
        onDialog = { opened ->

        },
        onAdded = {
            editor.add(it)
            onSignal(Signal.Change(component.value))
        },
        onRemoved = {
            editor.remove(it)
            onSignal(Signal.Change(component.value))
        },
        preview = { params ->
            FirstImagePreview(
                id = component.id,
                params = params,
                onFocus = {
                    onSignal(if (it.hasFocus) Signal.Focus else Signal.Blur(Unit))
                },
                onRemove = {
                    editor.remove(it)
                    onSignal(Signal.Change(component.value))
                }
            )
        }
    )
}


@Composable
internal fun RawImageComponent(
    id: String,
    title: String,
    uploaded: List<Attachment>,
    readonly: Boolean,
    onUpload: (suspend () -> List<String>)? = null,
    onDialog: (opened: Boolean) -> Unit,
    onAdded: (List<String>) -> Unit,
    onRemoved: (List<String>) -> Unit,
    preview: @Composable (params: PreviewParams) -> Unit,
) {
    val value = remember { mutableStateListOf(*uploaded.map { it.url }.toTypedArray()) }
    val selected = remember { mutableStateListOf<String>() }
    var isDialogOpened by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val params by remember(value, selected, isDialogOpened) {
        derivedStateOf {
            PreviewParams(
                value = value,
                selected = selected,
                setModalState = {
                    isDialogOpened = it
                    onDialog(it)
                },
                readonly = readonly,
                onUpload = onUpload,
                scope = scope,
                onAdded = onAdded
            )
        }
    }

    preview(params)

    if (isDialogOpened) Dialog(
        onDismissRequest = params::closeModal,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize(0.95f)) {
            Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                Column(modifier = Modifier.padding(start = 4.dp)) {
                    Text(title, modifier = Modifier.padding(8.dp))
                    if (!readonly) Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                        UploadLabel(
                            onClick = params::onUploadHandler,
                            modifier = Modifier.testTag("$id-body-upload").padding(8.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        val color = Color.Red.copy(alpha = 0.6f)
                        OutlinedButton(
                            onClick = {
                                value.removeAll(selected)
                                onRemoved(selected)
                                selected.clear()
                                isDialogOpened = value.size > 1
                                if (!isDialogOpened) onDialog(false)
                            },
                            enabled = selected.isNotEmpty(),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = Brush.linearGradient(
                                    listOf(
                                        color,
                                        color
                                    )
                                )
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        ) {
                            Text("Delete")
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Filled.Delete, "Delete Selected")
                        }
                    }
                }
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(150.dp),
                    modifier = Modifier.padding(top = if (readonly) 0.dp else 100.dp)
                ) {
                    items(value) {
                        val marked = selected.contains(it)
                        Picture(
                            url = it,
                            selected = marked,
                            margin = 4.dp,
                            onSelectorClicked = { url: String ->
                                if (marked) selected.remove(url) else selected.add(url)
                                Unit
                            }.takeIf { !readonly },
                            modifier = Modifier.testTag("$id-body-image-$it")
                        )
                    }
                }
                val modifier = Modifier.align(Alignment.TopEnd).clickable { params.closeModal() }
                Icon(Icons.Outlined.Close, "Close Model", modifier)
            }
        }
    }
}

@Composable
internal fun FirstImagePreview(
    id: String,
    params: PreviewParams,
    onFocus: (FocusState) -> Unit,
    onRemove: (String) -> Unit
) = Column(modifier = Modifier.testTag("$id-preview").fillMaxWidth()) {
    when (val first = params.value.firstOrNull()) {
        null -> UploadLabel(
            modifier = Modifier.testTag("$id-preview-label").fillMaxWidth().height(200.dp),
            onClick = {
                if (params.onUpload == null || params.readonly) return@UploadLabel
                onFocus(ActiveFocusState)
                params.onUploadHandler { onFocus(InActiveFocusState) }
            }
        )

        else -> Box {
            Picture(
                url = first,
                selected = params.selected.contains(first),
                onDelete = { url: String ->
                    params.value.remove(url)
                    onRemove(url)
                    if (params.value.size == 0) {
                        params.setModalState(false)
                    }
                }.takeIf { !params.readonly },
                modifier = Modifier.testTag("$id-preview-picture-$first")
            )
            Button(
                onClick = { params.openModal() },
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .testTag("$id-preview-button")
                    .padding(bottom = 12.dp, end = 16.dp)
            ) {
                val text = "More" + when (val size = params.value.size - 1) {
                    0 -> ""
                    else -> " +$size"
                }
                Icon(Icons.Sharp.ArrowCircleRight, "more")
                Text(text)
            }
        }
    }
}


@Composable
internal fun UploadLabel(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .dashedBorder(
            width = 2.dp,
            radius = 8.dp,
            color = LocalContentColor.current
        )
        .padding(4.dp)
        .background(color = LocalContentColor.current.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
        .then(modifier)
        .clickable { onClick() }
) {
    Text("Upload")
    Spacer(modifier = Modifier.width(4.dp))
    Icon(Icons.Filled.Backup, "Upload icon")
}

@Composable
internal fun Picture(
    url: String,
    selected: Boolean,
    onSelectorClicked: ((String) -> Unit)? = null,
    onDelete: ((String) -> Unit)? = null,
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
        .then(modifier)
) {
    Image(
        url = url,
        description = url,
        modifier = Modifier.fillMaxWidth().heightIn(200.dp, 400.dp).clip(RoundedCornerShape(8.dp))
    )
    if (onSelectorClicked != null) Box(
        modifier = Modifier
            .padding(top = 4.dp, end = 4.dp)
            .clip(RoundedCornerShape(15.dp))
            .size(24.dp)
            .align(Alignment.TopEnd).clickable { onSelectorClicked(url) }
            .border(
                width = 2.dp,
                color = LocalContentColor.current.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            ).background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
    ) {
        if (selected) Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "select picture",
            modifier = Modifier.align(Alignment.Center),
        )
    }

    if (onDelete != null) Box(
        modifier = modifier
            .padding(top = 4.dp, end = 4.dp)
            .align(Alignment.TopEnd).clickable { onDelete(url) }
    ) {
        Icon(Icons.Filled.Delete, "select picture", modifier = Modifier.align(Alignment.Center))
    }
}

private fun Modifier.dashedBorder(width: Dp, color: Color, radius: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { width.toPx() }
        val cornerRadiusPx = density.run { radius.toPx() }

        then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 180f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)