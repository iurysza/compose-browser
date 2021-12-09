package com.iurysza.composebrowser.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iurysza.composebrowser.ComposeBrowserApi
import com.iurysza.composebrowser.features.navigation.ComposeBrowserNavigationApi
import com.iurysza.composebrowser.ui.theme.BrowserTheme

@Composable
@Preview(
  name = "LIGHT - Klarna Browser",
)
fun Browser() {
  ThemedBrowser()
}

@Composable
@Preview(
  name = "DARK - KlarnaBrowser",
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun BrowserDark() {
  ThemedBrowser()
}

@Composable
private fun ThemedBrowser() {
  BrowserTheme {
    ComposeBrowser(
      browserViewModel = ComposeBrowserViewModel(ComposeBrowserApi(ComposeBrowserNavigationApi())),
      onBrowserStarted = {},
    )
  }
}
