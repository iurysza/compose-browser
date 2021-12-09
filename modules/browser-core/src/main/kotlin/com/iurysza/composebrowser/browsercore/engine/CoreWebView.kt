package com.iurysza.composebrowser.browsercore.engine

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.iurysza.composebrowser.browserfeatures.BrowserDelegate
import com.iurysza.composebrowser.browserfeatures.navigation.BrowserNavigationListener

@SuppressLint("SetJavaScriptEnabled")
class CoreWebView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : WebView(context, attrs, defStyleAttr),
  BrowserDelegate {

  private val browserChromeClient = CoreChromeClient()
  private val coreWebViewClient = CoreWebViewClient()

  init {
    /**
     * This is just a workaround for a Jetpack Compose preview mode issue.
     * Since some of the WebView classes are loaded into the classpath
     * during runtime, the WebView crashes in Compose's preview mode, this fixes it.
     */
    runCatching {
      with(settings) {
        javaScriptEnabled = true
        domStorageEnabled = true
        loadWithOverviewMode = true
      }
    }
    webChromeClient = browserChromeClient
    webViewClient = coreWebViewClient
  }

  override fun delegateLoadUrl(url: String) = loadUrl(url)
  override fun delegateReload() = reload()
  override fun delegateStop() = stopLoading()
  override fun delegateForward() = goForward()
  override fun delegateBack() = goBack()
  override fun setNavigationListener(navigationListener: BrowserNavigationListener) {
    coreWebViewClient.setBrowserNavigationListener(navigationListener)
    browserChromeClient.setBrowserNavigationListener(navigationListener)
  }
}
