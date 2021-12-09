package com.iurysza.composebrowser

import com.iurysza.composebrowser.browserfeatures.navigation.ComposeBrowserNavigationApi

internal object BrowserFactory {

  fun createBrowserWith(): ComposeBrowserApi {
    return ComposeBrowserApi(navigationApi = ComposeBrowserNavigationApi())
  }

}
