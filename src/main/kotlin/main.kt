import js.externals.jquery.jQuery
import kotlin.browser.window

fun main() {
    js("require('style.css');")

    window.onload = {
        jQuery("body").append("<span>Text added via jQuery</span>")
    }
}
