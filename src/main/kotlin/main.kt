import kotlinx.browser.document
import react.dom.render

data class Category(val id: Int, val title: String)

val mainCategories = listOf(
    Category(1, "Category #1"),
    Category(2, "Category #2"),
    Category(3, "Category #3"),
    Category(4, "Category #4")
)

fun main() {
    render(document.getElementById("root")) {
        child(App::class) {}
    }
}