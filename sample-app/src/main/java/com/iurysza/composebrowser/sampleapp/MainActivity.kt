package com.iurysza.composebrowser.sampleapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iurysza.composebrowser.ComposeBrowserApi
import com.iurysza.composebrowser.features.navigation.BrowserNavigationListener

private const val TAG = "sample-app"

class MainActivity : AppCompatActivity(), BrowserNavigationListener {

  private val openBrowserBtn by lazy { findViewById<View>(R.id.openBrowserBtn) }
  private val apiInstance = ComposeBrowserApi.getInstance()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    openBrowserBtn.setOnClickListener {
      apiInstance.openKlarnaBrowser(
        context = this,
        onBrowserStarted = {
          apiInstance.addNavigationListener(this)
          apiInstance.loadUrl("https://g1.globo.com/")
        }
      )
    }
  }

  private fun showToast(text: String) {
    Log.e(TAG, text)
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
  }

  override fun onPageFinished(url: String) {
    showToast("Page finished: $url")
  }

  override fun onPageProgressChanged(newProgress: Int) {
    super.onPageProgressChanged(newProgress)
    Log.e(TAG, "onPageProgressChanged: $newProgress")
  }
}
