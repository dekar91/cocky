package tech.dekar.cocky.shared.ui

import androidx.compose.foundation.layout.PaddingValues

/**
* Insets: expect/actual for platform-specific safe area insets
*/
expect object Insets {
    val safeContentPadding: PaddingValues
}