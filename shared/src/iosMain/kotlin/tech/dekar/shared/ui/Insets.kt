package tech.dekar.shared.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

actual object Insets {
    actual val safeContentPadding: PaddingValues
        get() = PaddingValues(top = 44.dp, bottom = 0.dp) // adjust based on iOS status bar/notch
}
