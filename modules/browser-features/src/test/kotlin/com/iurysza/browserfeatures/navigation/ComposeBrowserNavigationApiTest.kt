package com.iurysza.browserfeatures.navigation

import com.iurysza.composebrowser.BrowserDelegate
import com.iurysza.composebrowser.features.navigation.ComposeBrowserNavigationApi
import com.iurysza.composebrowser.features.utils.URLUtils
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot

class ComposeBrowserNavigationApiTest : BehaviorSpec({

  given("loadUrl is called ") {
    val mockBrowserDelegate = mockk<BrowserDelegate>()
    val mockUrlUtil = mockk<URLUtils>()
    val sut = prepareTest(mockBrowserDelegate, mockUrlUtil)

    and("the url is missing the protocol") {
      val urlArgument = slot<String>()
      every { mockBrowserDelegate.delegateLoadUrl(capture(urlArgument)) } just runs

      every { mockUrlUtil.normalizeUrl(any()) } returns "https://google.com"

      sut.loadUrl("google.com")
      then("delegate should receive a call with the parsed url") {
        urlArgument.captured shouldContain "https"
      }
    }
  }
})

private fun prepareTest(
  mockDelegate: BrowserDelegate,
  urlUtil: URLUtils,
): ComposeBrowserNavigationApi {
  clearAllMocks()
  every { mockDelegate.setNavigationListener(any()) } just runs
  return ComposeBrowserNavigationApi(urlUtil).apply {
    setBrowserDelegate(mockDelegate)
  }
}
