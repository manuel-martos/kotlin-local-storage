import react.*
import react.dom.WithClassName
import react.dom.div
import react.dom.h1

@JsModule("@app")
internal external object AppModule {
}


class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        h1 {
            +"Categories"
        }
        div {
            categoryList {
                categories = mainCategories
            }
        }
    }
}
