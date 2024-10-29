package com.youssef.kotlinflowts.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.builder.joyfill.buildDocument
import com.youssef.kotlinflowts.compose.joyfill.Form
import com.youssef.kotlinflowts.compose.joyfill.rememberEditor
import com.youssef.kotlinflowts.models.joyfill.utils.App

@Composable
fun BasicSample() {
    val document = remember { service.getEmployeeDocument() }

    val editor = rememberEditor(document)

    // Render the form with the editor
    Column(modifier = Modifier.padding(8.dp)) {
        Form(editor = editor)
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
    fun getEmployeeDocument() = buildDocument {
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
        screen("Colum Screen example")
        column {
            text("First Name")
            text("Last Name")
            date("Date of Birth")
            text("Phone")
        }
    }

    fun save(app: App) {
        println("Document hase been saved")
    }
}