import kotlin.browser.document

fun main() {
    js("require('style.css');")
    document.write("Hello, world!, hey its working!!!")
}
