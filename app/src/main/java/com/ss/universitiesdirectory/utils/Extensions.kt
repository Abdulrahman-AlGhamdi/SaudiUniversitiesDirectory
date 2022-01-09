package com.ss.universitiesdirectory.utils

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

fun connectTo(address: String): Any {
    return try {
        (URL(address).openConnection() as HttpsURLConnection).apply {
            this.requestMethod = "GET"
            this.connectTimeout = 15000
            this.readTimeout = 15000
            this.doInput = true
        }
    } catch (e: MalformedURLException) {
        e.printStackTrace()
        "Error : wrong url format"
    } catch (e: IOException) {
        e.printStackTrace()
        "Error : unable to establish connection"
    }
}

fun View.showSnackBar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    anchorView: Int? = null,
    actionMessage: String? = null,
    action: (View) -> Unit = {}
) {
    Snackbar.make(this, message, length).apply {
        actionMessage?.let { this.setAction(actionMessage) { action(it) } }
        anchorView?.let { this.setAnchorView(anchorView) }
    }.show()
}

fun NavController.navigateTo(action: NavDirections, fragmentId: Int) {
    if (this.currentDestination == this.graph.findNode(fragmentId))
        this.navigate(action)
}

fun Context.isDarkThemeOn(): Boolean {
    return resources.configuration.uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
}