package com.youssef.kotlinflowts.app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.builder.kotlinflowts.AppBuilder
import com.youssef.kotlinflowts.builder.kotlinflowts.buildApp
import com.youssef.kotlinflowts.compose.kotlinflowts.App
import com.youssef.kotlinflowts.compose.kotlinflowts.rememberEditor
import com.youssef.kotlinflowts.models.kotlinflowts.toMutableScreen
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App


@Composable
fun ViewSample(
    appBuilder: AppBuilder
) {
    val editor = rememberEditor(appBuilder.app)

    Column(modifier = Modifier.padding(8.dp)) {
        App(
            editor = editor,
            updateUi = appBuilder.updateUi,
            builders = appBuilder.app.builders,
            showUnsupportedComponents = true,
            onChangeScreen = {
                appBuilder.updateCursor(it.toMutableScreen())
            }
        )
        OutlinedButton(
            onClick = {
                // get the updated document
                val updated = editor.toApp()

                // push it to the server
                service.save(editor.toApp())
            }
        ) {
            Text("Save Document")
        }
    }
}

object service {
    fun getAppBuilder() = buildApp {
        name("HEllOOOO")

        screen("Basic Information")
        row {
            text("First Name")
            text("Last Name")
        }
//        image(
//            title = "Image",
//            value = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6v9BAs8FPSYl6FFv6uA5m_Jeai5i5Wxu3Hw&s"
//        )
//        column {  }
//        text("First Name")
//        text("Last Name")
//        column {
//            date("Date of Birth")
//            text("Phone")
//        }
//
//        table("Education") {
//            text("Degree")
//            text("Institution")
//            text("Start Date")
//            text("End Date")
//        }
//
        screen("Contact Information")
        text("Next of Kin")
//        text("Hello")
//        text("Phone Number")
//
//
//        screen("Sample")
//        chart("chart example"){
//           line("line chart") {
//               it.invoke(1, 2, "didi")
//           }
//        }
//        image(
//            title = "Image",
//            value = "data:image/jpeg;base64,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"
//        )
//
//        select(
//            title = "Select",
//            options = listOf("Option 1", "Option 2", "Option 3"),
//            value = "Option 1"
//        )
//        textarea("Text Area")
//        signature("Signature")
//        image(
//            title = "Image",
//            value = listOf(
//                "data:image/jpeg;base64,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",
//                "data:image/jpeg;base64,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"
//            )
//        )
//
//        screen("BABAy Blue")
//        column {
//            text("Text Field Component")
//            textarea("Text Area Component")
//            signature("Signature")
//            number("Number Component")
//            column {
//                date("Signature Component")
//                dropdown("Dropdown Component", listOf("Option 1", "Option 2", "Option 3"))
//                column {
//                    select("Select Component", listOf("Option 1", "Option 2", "Option 3"))
//                }
//            }
//        }
//        screen("Row test")
//        row {
//            text("hello everyone")
//            text("hi again")
//        }
    }

    fun save(app: App) {
        println("Document hase been saved")
    }
}