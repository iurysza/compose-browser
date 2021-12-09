package com.iurysza.composebrowser.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.iurysza.composebrowser.ui.widgets.ComposableWebView
import com.iurysza.composebrowser.ui.widgets.bottombar.BrowserBottomBar
import com.iurysza.composebrowser.ui.widgets.progress.BrowserProgress
import com.iurysza.composebrowser.ui.widgets.toolbar.BrowserToolbar

@Composable
fun ComposeBrowser(
  browserViewModel: ComposeBrowserViewModel,
  onBrowserStarted: () -> Unit,
) {

  val activity = (LocalContext.current as? Activity)

  BackHandler(onBack = { /* NOOP: disables closing browser with back press*/ })

  Scaffold(
    topBar = {
      BrowserToolbar(
        browserViewModel.toolbarViewState.collectAsState().value,
        onQueryChange = browserViewModel::onQueryChange,
        onTextSubmitted = browserViewModel::onSubmit,
        onDisplayToolbarClick = browserViewModel::onDisplayModeClicked,
        onRefreshClicked = browserViewModel::onRefreshClicked,
        onClearButtonClicked = browserViewModel::onClearButtonClicked,
        onCancelClicked = browserViewModel::onCancelClicked,
        onBackPressed = browserViewModel::onBackPressed,
        onCloseButtonClicked = { activity?.finish() },
      )
    },
    bottomBar = {
      BrowserBottomBar(
        browserViewModel.bottomBarViewState.collectAsState().value,
        onGoForwardButtonClicked = browserViewModel::onGoForwardClicked,
        onGoBackButtonClicked = browserViewModel::onGoBackClicked,
      )
    },
  ) {
    Column {
      BrowserProgress(browserViewModel.progressViewState.collectAsState().value)
      ComposableWebView(
        onReady = onBrowserStarted,
        onBrowserDelegateCreated = browserViewModel::setDelegate
      )
    }
  }
}
