package com.iurysza.composebrowser.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.iurysza.composebrowser.ComposeBrowserApi
import com.iurysza.composebrowser.ui.theme.BrowserTheme

internal class ComposeBrowserActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BrowserTheme {
        ComposeBrowser(
          ComposeBrowserViewModel(browserApi),
          onBrowserStarted,
        )
      }
    }
  }

  companion object {
    lateinit var browserApi: ComposeBrowserApi
    lateinit var onBrowserStarted: () -> Unit

    fun openBrowser(context: Context, browserApi: ComposeBrowserApi, onBrowserStarted: () -> Unit) {
      this.onBrowserStarted = onBrowserStarted
      this.browserApi = browserApi
      context.startActivity(
        Intent(context, ComposeBrowserActivity::class.java).apply {
          flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
      )
    }
  }
}
