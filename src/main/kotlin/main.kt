import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import react.buildElements
import react.dom.render
import kotlin.coroutines.CoroutineContext

data class Category(val id: Int, val title: String)

val mainCategories = listOf(
    Category(1, "Category #1"),
    Category(2, "Category #2"),
    Category(3, "Category #3"),
    Category(4, "Category #4")
)

private class Application : CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    fun start() {
        document.getElementById("root")?.let {
            render(
                buildElements {
                    child(App::class) {}
                }, it
            )
        }
    }
}

fun main() {
    Application().start()
}