package com.iurysza.composebrowser.browserfeatures

import com.iurysza.composebrowser.browserfeatures.navigation.BrowserNavigationListener

interface BrowserDelegate {
  fun delegateReload()
  fun delegateStop()
  fun delegateForward()
  fun delegateBack()
  fun delegateLoadUrl(url: String)
  fun setNavigationListener(navigationListener: BrowserNavigationListener)
}
