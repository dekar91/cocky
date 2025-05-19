package tech.dekar.lockme

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import tech.dekar.cocky.ui.controls.NavigationScreens
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationBus @Inject constructor() {
    private val _actions = MutableSharedFlow<NavigationScreens>(extraBufferCapacity = 1)
    val actions: SharedFlow<NavigationScreens> = _actions.asSharedFlow()

    suspend fun emit(action: NavigationScreens) {
        _actions.emit(action)
    }
}
