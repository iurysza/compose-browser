package com.iurysza.composebrowser.browsercore.engine

import android.webkit.WebChromeClient
import android.webkit.WebView
import com.iurysza.composebrowser.browserfeatures.navigation.BrowserNavigationListener

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
