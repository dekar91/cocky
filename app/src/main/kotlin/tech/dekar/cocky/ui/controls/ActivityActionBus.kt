package tech.dekar.cocky.ui.controls

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityActionBus @Inject constructor() {
    private val _actions = MutableSharedFlow<ActivityActions>()
    val actions: SharedFlow<ActivityActions> = _actions.asSharedFlow()

    suspend fun emit(action: ActivityActions) {
        _actions.emit(action)
    }
}
