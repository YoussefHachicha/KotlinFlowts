package com.youssef.kotlinflowts.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.builder.joyfill.buildApp
import com.youssef.kotlinflowts.compose.joyfill.App
import com.youssef.kotlinflowts.compose.joyfill.rememberEditor
import com.youssef.kotlinflowts.models.joyfill.utils.App

@Composable
fun BasicSample() {
    val app = remember { service.getApp() }

    val editor = rememberEditor(app)

    // Render the form with the editor
    Column(modifier = Modifier.padding(8.dp)) {
        App(editor = editor)
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
    fun getApp() = buildApp {
        name("HEllOOOO")

        screen("Basic Information")
        text("First Name")
        text("Last Name")
        date("Date of Birth")
        text("Phone")
        table("Education") {
            text("Degree")
            text("Institution")
            text("Start Date")
            text("End Date")
        }

        screen("Contact Information")
        text("Next of Kin")
        text("Phone Number")


        screen("Sample")
        chart("chart example"){
           line("line chart") {
               it.invoke(1, 2, "didi")
           }
        }
        image(
            title = "Image",
            value = "https://picsum.photos/200"
        )
        select(
            title = "Select",
            options = listOf("Option 1", "Option 2", "Option 3"),
            value = "Option 1"
        )
        textarea("Text Area")
        signature("Signature")
        image(
            title = "Image",
            value = listOf("https://picsum.photos/200", "https://picsum.photos/200")
        )

        screen("BABAy Blue")
        column {
            text("Text Field Component")
            textarea("Text Area Component")
            signature("Signature")
            number("Number Component")
            column {
                date("Signature Component")
                dropdown("Dropdown Component", listOf("Option 1", "Option 2", "Option 3"))
                column {
                    select("Select Component", listOf("Option 1", "Option 2", "Option 3"))
                }
            }

        }
    }

    fun save(app: App) {
        println("Document hase been saved")
    }
}