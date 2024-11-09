package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun KfScreenSelector(
    screens: List<Screen>,
    screen: Screen,
    onChange: (Screen) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .clickable { expanded = !expanded }
        .border(1.dp, LocalContentColor.current.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Screen #${screens.indexOf(screen) + 1}", color = LocalContentColor.current.copy(alpha = 0.7f))
            Text(screen.name)
        }
        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
    }

    if (expanded) Dialog(
        onDismissRequest = { expanded = false },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box {
            Surface(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp).fillMaxSize(0.9f)) {
                LazyColumn {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 8.dp, bottom = 16.dp, end = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Screens", fontWeight = FontWeight.Bold)
                            Icon(Icons.Outlined.Close, "close", modifier = Modifier.clickable { expanded = false }.padding(8.dp))
                        }
                    }
                    itemsIndexed(screens, key = { _, s -> s.id }) { index, it ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.clickable {
                                onChange(it)
                                expanded = false
                            }.padding(8.dp).height(40.dp)
                        ) {
                            Text(
                                text = "${index + 1}. ${it.name}",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}