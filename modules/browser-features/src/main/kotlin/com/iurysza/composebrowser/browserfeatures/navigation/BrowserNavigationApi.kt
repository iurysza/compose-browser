package com.iurysza.composebrowser.browserfeatures.navigation

import com.iurysza.composebrowser.browserfeatures.BrowserDelegate

/**
 *  Exposes the navigation API to consumers
 */
interface BrowserNavigationApi {
  fun setBrowserDelegate(browserDelegate: BrowserDelegate)
  fun addNavigationListener(browserNavigationListener: BrowserNavigationListener)
  fun reload()
  fun stop()
  fun goForward()
  fun goBack()
  fun loadUrl(url: String)
}

/**
 * Receives navigation events
 */
interface BrowserNavigationListener {
  fun onPageFinished(url: String) {}
  fun onPageStarted(url: String) {}
  fun onPageProgressChanged(newProgress: Int) {}
  fun onNavigationStateUpdated(canGoBack: Boolean, canGoForward: Boolean) {}
}
