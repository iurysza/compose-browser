package com.iurysza.composebrowser

import com.iurysza.composebrowser.features.navigation.ComposeBrowserNavigationApi

internal object BrowserFactory {

  fun createBrowserWith(): ComposeBrowserApi {
    return ComposeBrowserApi(navigationApi = ComposeBrowserNavigationApi())
  }
}
