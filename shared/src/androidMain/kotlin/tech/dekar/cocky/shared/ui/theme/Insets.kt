package tech.dekar.cocky.shared.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable

actual object Insets {
    actual val safeContentPadding: PaddingValues
        @Composable
        get() = WindowInsets.systemBars
            .union(WindowInsets.displayCutout)
            .asPaddingValues()
}
