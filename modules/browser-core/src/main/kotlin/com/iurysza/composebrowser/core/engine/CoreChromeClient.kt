package com.iurysza.composebrowser.core.engine

import android.webkit.WebChromeClient
import android.webkit.WebView
import com.iurysza.composebrowser.features.navigation.BrowserNavigationListener

internal class CoreChromeClient() : WebChromeClient() {
  private var navigationListener: BrowserNavigationListener? = null

  override fun onProgressChanged(view: WebView?, newProgress: Int) {
    super.onProgressChanged(view, newProgress)
    navigationListener?.onPageProgressChanged(newProgress)
  }

  fun setBrowserNavigationListener(navigationListener: BrowserNavigationListener) {
    this.navigationListener = navigationListener
  }
}
