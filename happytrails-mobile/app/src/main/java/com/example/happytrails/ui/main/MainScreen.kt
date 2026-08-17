package com.example.happytrails.ui.main

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation3.runtime.NavKey

@Composable
fun MainScreen(
  onItemClick: (NavKey) -> Unit,
  modifier: Modifier = Modifier,
) {
  AndroidView(
    factory = { context ->
      WebView(context).apply {
        webViewClient = WebViewClient()
        settings.apply {
          javaScriptEnabled = true
          domStorageEnabled = true
          useWideViewPort = true
          loadWithOverviewMode = true
        }
        loadUrl("https://huh-kaurkavlin001-6924s-projects.vercel.app")
      }
    },
    modifier = Modifier.fillMaxSize()
  )
}
