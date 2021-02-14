import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RProps
import react.ReactElement
import react.dom.p

external interface CategoryListProps : RProps {
    var categories: List<Category>
}

external interface CategoryListState : LocalStoreState {
    var selectedCategory: Category?
}

@JsExport
class CategoryList : LocalStoreComponent<CategoryListProps, CategoryListState>() {
    override fun CategoryListState.init() {
        selectedCategory = register("selectedCategory") { state.selectedCategory }
    }

    override fun RBuilder.render() {
        for (category in props.categories) {
            p {
                key = category.id.toString()
                attrs {
                    onClickFunction = {
                        setState({
                            it.selectedCategory = category
                            it
                        })
                    }
                }
                if (category == state.selectedCategory) {
                    +"▶ "
                }
                +category.title
            }
        }
    }

    private fun getSelectedCategory(): Category? {
        return state.selectedCategory
    }
}

fun RBuilder.categoryList(handler: CategoryListProps.() -> Unit): ReactElement {
    return child(CategoryList::class) {
        this.attrs(handler)
    }
}