package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilder
import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.joyfill.internal.AppBuilderImpl
import com.youssef.kotlinflowts.models.joyfill.File
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.chart.Line
import com.youssef.kotlinflowts.models.joyfill.toApp
import com.youssef.kotlinflowts.models.joyfill.toMutableApp
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.ID

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