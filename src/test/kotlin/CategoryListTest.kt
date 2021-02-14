import kotlin.test.Test
import kotlin.test.assertEquals

class CategoryListTest : EnzymeTest() {
    @Test
    fun testCategoryList() = runAsyncTest {
        val mainCategories = listOf(
            Category(1, "Category #1"),
            Category(2, "Category #2"),
            Category(3, "Category #3"),
            Category(4, "Category #4")
        )


        val element = enzymeMount {
            child(CategoryList::class) {
                attrs.categories = mainCategories
            }
        }

        println(element.debug())
        assertEquals(4, element.find("p").map { it.domInstance() }.size)
    }
}