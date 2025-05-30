package tech.dekar.cocky.shared.ui.control

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationBus {
    private val _actions = MutableSharedFlow<Screens>(extraBufferCapacity = 1)
    val actions: SharedFlow<Screens> = _actions.asSharedFlow()

    suspend fun emit(action: Screens) {
        _actions.emit(action)
    }
}
