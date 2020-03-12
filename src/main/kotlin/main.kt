import js.externals.jquery.JQuery
import js.externals.jquery.JQuery.Deferred
import js.externals.jquery.JQuery.jqXHR
import js.externals.jquery.JQueryStatic
import js.externals.jquery.jQuery
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.TagConsumer
import kotlinx.html.dom.create
import kotlinx.html.js.div
import kotlinx.html.span
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun main() {
    js("require('style.css');")

    window.onload = {
        jQuery("body").append("<span>Text added via jQuery</span>")

        GlobalScope.launch {
            val result = jQuery.coroutineLoad("someResource.json")

            jQuery("body").appendHTML {
                div {
                    span {
                        +result.toString()
                    }
                }
            }
        }

    }

}


@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
suspend fun JQueryStatic<*>.coroutineLoad(path: String): dynamic = suspendCoroutine { continuation ->
    val request = get(path)

    val successCodeBlock = { value: dynamic ->
        continuation.resume(value.content.toString())
    }

    val failBlock = {
        continuation.resumeWithException(RuntimeException("Could not load file: '$path'"))
    }

    request.done(successCodeBlock.asDynamic() as Deferred.CallbackBase<Any, dynamic, jqXHR<Any>, Any?>, emptyArray())
    request.fail(failBlock.asDynamic() as Deferred.CallbackBase<jqXHR<Any>, dynamic, String, Any?>, emptyArray())
}

fun JQuery<HTMLElement>.appendHTML(block: TagConsumer<HTMLElement>.() -> HTMLElement): JQuery<HTMLElement> {
    val contents = block(document.create)
    append(contents)
    return jQuery(contents)
}
