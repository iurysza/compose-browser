package com.iurysza.composebrowser

import android.content.Context
import com.iurysza.composebrowser.features.navigation.BrowserNavigationApi
import com.iurysza.composebrowser.ui.ComposeBrowserActivity

class ComposeBrowserApi internal constructor(
  val navigationApi: BrowserNavigationApi,
) : BrowserNavigationApi by navigationApi {

  fun openKlarnaBrowser(context: Context, onBrowserStarted: () -> Unit = {}) {
    ComposeBrowserActivity.openBrowser(context, instance, onBrowserStarted)
  }

  companion object {
    private lateinit var instance: ComposeBrowserApi

    @Synchronized
    fun getInstance(): ComposeBrowserApi {
      if (!this::instance.isInitialized) {
        instance = BrowserFactory.createBrowserWith()
      }
      return instance
    }
  }
}
