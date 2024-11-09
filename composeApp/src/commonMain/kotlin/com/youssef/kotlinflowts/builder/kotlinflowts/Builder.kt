package com.youssef.kotlinflowts.builder.kotlinflowts

import com.youssef.kotlinflowts.builder.kotlinflowts.chart.LineBuilder
import com.youssef.kotlinflowts.builder.kotlinflowts.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.kotlinflowts.internal.AppBuilderImpl
import com.youssef.kotlinflowts.models.kotlinflowts.File
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.toApp
import com.youssef.kotlinflowts.models.kotlinflowts.toMutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

fun buildApp(builder: AppBuilder.() -> Unit): App {
    val generator = IdentityGenerator.default
    val uid = generator.generate()
    val app = mutableMapOf<String, Any?>(
        ID to uid,
        App::identifier.name to "app-$uid",
        App::name.name to "New App",
        App::files.name to mutableListOf<File>(),
        App::components.name to mutableListOf<Component>()
    ).toApp().toMutableApp()
    val db = AppBuilderImpl(app, generator)
    db.builder()
    return db.app
}

fun plot(
    identity: IdentityGenerator = IdentityGenerator.default,
    builder: LineBuilder.() -> Unit
): List<Line> = LineBuilderImpl(identity).apply(builder).lines