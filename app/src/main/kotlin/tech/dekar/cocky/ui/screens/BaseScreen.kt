package tech.dekar.cocky.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tech.dekar.cocky.ui.theme.CockyTheme
import tech.dekar.cocky.ui.theme.Dimens

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    applySafeArea: Boolean = true,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    CockyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = contentAlignment
            ) {
                val combinedInsets = WindowInsets.systemBars.union(WindowInsets.displayCutout)
                Column(
                    modifier = Modifier
                        .then(
                            if (applySafeArea) {
                                Modifier.padding(combinedInsets.asPaddingValues())
                            } else {
                                Modifier
                            }
                        )
                        .padding(Dimens.ScreenPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.ElementSpacing),
                    content = content
                )

                snackbarHostState?.let {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.Companion.padding(Dimens.ScreenPadding)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    name = "Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Night Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
fun BaseScreenPreview() {
    BaseScreen(contentAlignment = Alignment.Center) {
    }
}
