package tech.dekar.cocky.shared.ui.control

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ActivityActionBus {
    private val _actions = MutableSharedFlow<ActivityActions>()
    val actions: SharedFlow<ActivityActions> = _actions.asSharedFlow()

    suspend fun emit(action: ActivityActions) {
        _actions.emit(action)
    }
}
