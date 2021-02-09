import kotlinx.browser.localStorage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import react.RComponent
import react.RProps
import react.RState

external interface LocalStoreState : RState {
    var items: MutableMap<String, () -> Unit>?
}

abstract class LocalStoreComponent<P : RProps, S : LocalStoreState> : RComponent<P, S> {
    constructor() : super() {
    }

    constructor(props: P) : super(props) {
    }

    override fun componentDidUpdate(prevProps: P, prevState: S, snapshot: Any) {
        prevState.saveState()
    }

    inline fun <reified T> LocalStoreState.register(key: String, defaultValue: T, noinline retrieve: () -> T): T {
        if (items == null) {
            items = mutableMapOf()
        }
        items?.put(key) {
            saveStateItem(key, retrieve.invoke())
        }
        return restoreStateItem(key, defaultValue)
    }

    inline fun <reified T> LocalStoreState.register(key: String, noinline retrieve: () -> T?): T? {
        if (items == null) {
            items = mutableMapOf()
        }
        items?.put(key) {
            saveStateItem(key, retrieve.invoke())
        }
        return restoreStateItem(key)
    }

    private fun LocalStoreState.saveState() {
        items?.apply {
            keys.forEach { key ->
                get(key)?.invoke()
            }
        }
    }

    inline fun <reified T> saveStateItem(key: String, value: T) {
        localStorage.setItem(key, Json.encodeToString(value))
    }

    inline fun <reified T> restoreStateItem(key: String): T? =
        with(localStorage.getItem(key)) {
            if (this != null) {
                try {
                    Json.decodeFromString(this)
                } catch (t: Throwable) {
                    null
                }
            } else {
                null
            }
        }

    inline fun <reified T> restoreStateItem(key: String, default: T): T =
        with(localStorage.getItem(key)) {
            if (this != null) {
                try {
                    Json.decodeFromString(this)
                } catch (t: Throwable) {
                    default
                }
            } else {
                default
            }
        }
}