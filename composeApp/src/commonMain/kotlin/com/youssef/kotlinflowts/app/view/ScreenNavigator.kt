package com.youssef.kotlinflowts.app.view

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.GeneratingTokens
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.utils.clickableNoIndication

@Composable
fun ScreenNavigator(
    modifier: Modifier = Modifier,
    screens: List<Screen>,
    currentScreen: Screen,
    onChange: (Screen) -> Unit,
    onAdd: (index: Int) -> Unit,
    generateCode: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        // Button A - Always stuck to the start
        IconButton(
            onClick = { onAdd(screens.size + 1) },
            modifier = Modifier.weight(0.1f)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add"
            )
        }

        VerticalDivider()

        // Flexible LazyRow that shrinks
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier.weight(0.8f)
        ) {
            itemsIndexed(screens) { index, screen ->
                val isSelected by remember(currentScreen.id) {
                    mutableStateOf(currentScreen.id == screen.id)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = screen.name,
                        color = if (isSelected) Color.Black else Color.Gray,
                        softWrap = false,
                        maxLines = 1,
                        modifier = Modifier
                            .clickableNoIndication { onChange(screen) }
                            .padding(8.dp)
                    )

                    if (index != screens.size - 1) {
                        VerticalDivider()
                    }
                }
            }
        }


        VerticalDivider()

        // Button B - Always stuck to the end
        IconButton(
            onClick = { generateCode() },
            modifier = Modifier.weight(0.1f)
        ) {
            Icon(
                Icons.Default.GeneratingTokens,
                contentDescription = "Generate"
            )
        }
    }
}

