package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class PreviewParams(
    val value: SnapshotStateList<String>,
    val setModalState: (open: Boolean) -> Unit,
    val selected: SnapshotStateList<String>,
    val onAdded: (List<String>) -> Unit,
    val readonly: Boolean,
    val onUpload: (suspend () -> List<String>)?,
    private val scope: CoroutineScope,
) {
    fun onUploadHandler(onComplete: (() -> Unit)? = null) {
        val uploader = onUpload
        if (!readonly && uploader != null) scope.launch {
            try {
                val received = uploader()
                value.addAll(received)
                onAdded(received)
                onComplete?.invoke()
            } catch (_: Throwable) {

            }
        }
    }

    fun openModal() = setModalState(true)

    fun closeModal() = setModalState(false)
}

