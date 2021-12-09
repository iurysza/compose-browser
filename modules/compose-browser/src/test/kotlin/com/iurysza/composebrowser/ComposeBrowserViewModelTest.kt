package com.iurysza.composebrowser

import com.iurysza.composebrowser.browserfeatures.navigation.ComposeBrowserNavigationApi
import com.iurysza.composebrowser.browserfeatures.utils.URLUtils
import com.iurysza.composebrowser.ui.ComposeBrowserViewModel
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class ComposeBrowserViewModelTest : BehaviorSpec({
  given("The view is in the initial state") {
    val (navigationApi, sut) = prepareTest()

    `when`("when the page progress reaches 100") {
      navigationApi.onPageProgressChanged(newProgress = 100)

      then("progressbar should not be visible") {
        sut.progressViewState.value.showProgress shouldBe false
      }
    }
    `when`("when the page progress changes") {
      navigationApi.onPageProgressChanged(newProgress = 20)

      then("progress value should be updated") {
        sut.progressViewState.value.showProgress shouldBe true
        sut.progressViewState.value.progressPercentage shouldBe 20.toFloat().div(100)
      }
    }

    `when`("the user clicks the toolbar in display mode") {
      sut.onDisplayModeClicked()
      then("it should enter edit mode") {
        sut.toolbarViewState.value.isEditMode shouldBe true
      }
      then("the toolbar field should be empty") {
        sut.toolbarViewState.value.query shouldBe ""
      }

      and("submits a valid url") {
        sut.onSubmit(aValidUrl)
        and("the page starts loading") {

          then("the progress value should be updated") {
            sut.progressViewState.value.showProgress shouldBe true
            sut.progressViewState.value.progressPercentage shouldBeGreaterThan 0.0f
          }
        }
        and("the user enters edit mode again") {
          sut.onDisplayModeClicked()
          then("the query on the toolbar should be the url that was submitted") {
            sut.toolbarViewState.value.query shouldBe aValidUrl
          }
          then("the clearTextButton should be visible") {
            sut.toolbarViewState.value.showClearButton shouldBe true
          }
        }
      }
    }
  }

  given("The user is in edit mode") {
    val (_, sut) = prepareTest()

    `when`("cancel is called") {
      sut.onCancelClicked()

      then("should leave edit-mode") {
        sut.toolbarViewState.value.isEditMode shouldBe false
      }
    }
    `when`("backPressed is called") {
      sut.onBackPressed()

      then("should leave edit-mode") {
        sut.toolbarViewState.value.isEditMode shouldBe false
      }
    }
    `when`("The user enters a valid url") {
      sut.onQueryChange(aValidUrl)

      then("the query state should be updated") {
        sut.toolbarViewState.value.query shouldBe aValidUrl
      }
      then("The clear button should be visible") {
        sut.toolbarViewState.value.showClearButton shouldBe true
      }

      and("The user clicks the submit button") {
        sut.onSubmit(aValidUrl)

        then("the displayUrl and the currentPageUrl should be updated ") {
          sut.toolbarViewState.value.displayUrl.isSafe shouldBe true
          sut.toolbarViewState.value.displayUrl.text shouldBe "klarna.de"
        }
        then("the toolbar should leave the edit-mode") {
          sut.toolbarViewState.value.isEditMode shouldBe false
        }
        and("taps the clearTextButton") {
          sut.onClearButtonClicked()
          then("the current query should be empty") {
            sut.toolbarViewState.value.query shouldBe ""
          }
        }
        and("The user presses the clear button") {
          sut.onClearButtonClicked()
          then("The clear button should not be visible") {
            sut.toolbarViewState.value.showClearButton shouldBe false
          }
          and("the user leaves the editMode by backPressing") {
            sut.onBackPressed()
            then("the displayMode toolbar should show the simplified url of the last loaded page") {
              sut.toolbarViewState.value.displayUrl.text shouldBe aSimplifiedUrl
            }
          }
        }
      }
    }
  }
})

private const val aSimplifiedUrl = "klarna.de"
private const val aValidUrl = "https://www.klarna.de/"

private fun prepareTest(): Pair<ComposeBrowserNavigationApi, ComposeBrowserViewModel> {
  val mockUrlUtils = object : URLUtils {
    override fun normalizeUrl(string: String): String = aValidUrl
    override fun getDisplayUrl(url: String): String = aSimplifiedUrl
    override fun isValidUrl(potentialUrl: String): Boolean = potentialUrl.isNotEmpty()
  }

  val navigationApi = ComposeBrowserNavigationApi(mockUrlUtils)
  val sut = ComposeBrowserViewModel(navigationApi, mockUrlUtils)
  return Pair(navigationApi, sut)
}
