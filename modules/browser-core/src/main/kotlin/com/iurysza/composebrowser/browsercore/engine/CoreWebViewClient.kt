package com.iurysza.composebrowser.browsercore.engine

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import com.iurysza.composebrowser.browserfeatures.navigation.BrowserNavigationListener

internal class CoreWebViewClient() : WebViewClient() {
  private var navigationListener: BrowserNavigationListener? = null

  fun setBrowserNavigationListener(navigationListener: BrowserNavigationListener) {
    this.navigationListener = navigationListener
  }

  override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
    super.onPageStarted(view, url, favicon)
    navigationListener?.onPageStarted(url)
  }

  override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
    super.doUpdateVisitedHistory(view, url, isReload)
    navigationListener?.onNavigationStateUpdated(view.canGoBack(), view.canGoForward())
  }

  override fun onPageFinished(view: WebView, url: String) {
    super.onPageFinished(view, url)
    navigationListener?.onPageFinished(url)
  }
}
