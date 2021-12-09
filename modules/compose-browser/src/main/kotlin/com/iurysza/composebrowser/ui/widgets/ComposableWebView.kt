package com.iurysza.composebrowser.ui.widgets

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.iurysza.composebrowser.BrowserDelegate
import com.iurysza.composebrowser.core.engine.CoreWebView

@Composable
fun ComposableWebView(
  onReady: () -> Unit = {},
  onBrowserDelegateCreated: (BrowserDelegate) -> Unit,
) {
  AndroidView(
    factory = { context ->
      CoreWebView(context).apply {
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
      }
    },
    update = {
      onBrowserDelegateCreated(it)
      onReady()
    }
  )
}
