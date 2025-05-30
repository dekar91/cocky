package tech.dekar.cocky.shared.ui.control

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationBus {
    private val _actions = MutableSharedFlow<Screen>(extraBufferCapacity = 1)
    val actions: SharedFlow<Screen> = _actions.asSharedFlow()

    suspend fun emit(action: Screen) {
        _actions.emit(action)
    }
}
