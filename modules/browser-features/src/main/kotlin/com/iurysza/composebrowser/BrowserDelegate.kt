package com.iurysza.composebrowser

import com.iurysza.composebrowser.features.navigation.BrowserNavigationListener

interface BrowserDelegate {
  fun delegateReload()
  fun delegateStop()
  fun delegateForward()
  fun delegateBack()
  fun delegateLoadUrl(url: String)
  fun setNavigationListener(navigationListener: BrowserNavigationListener)
}
