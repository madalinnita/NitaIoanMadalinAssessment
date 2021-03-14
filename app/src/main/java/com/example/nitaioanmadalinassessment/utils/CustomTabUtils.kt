package com.example.nitaioanmadalinassessment.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
import androidx.core.content.ContextCompat
import com.example.nitaioanmadalinassessment.R
import com.example.nitaioanmadalinassessment.utils.CustomTabUtils.getCustomTabsPackages
import com.example.nitaioanmadalinassessment.utils.CustomTabUtils.openUrlInBrowser

object CustomTabUtils {
    @SuppressLint("QueryPermissionsNeeded")
    fun getCustomTabsPackages(context: Context): ArrayList<ResolveInfo>? {
        val pm = context.packageManager
        // Get default VIEW intent handler.
        val activityIntent = Intent()
            .setAction(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(Uri.fromParts("http", "", null))

        // Get all apps that can handle VIEW intents.
        val resolvedActivityList = pm.queryIntentActivities(activityIntent, 0)
        val packagesSupportingCustomTabs: ArrayList<ResolveInfo> = ArrayList()
        for (info in resolvedActivityList) {
            val serviceIntent = Intent()
            serviceIntent.action = ACTION_CUSTOM_TABS_CONNECTION
            serviceIntent.setPackage(info.activityInfo.packageName)
            // Check if this package also resolves the Custom Tabs service.
            if (pm.resolveService(serviceIntent, 0) != null) {
                packagesSupportingCustomTabs.add(info)
            }
        }
        return packagesSupportingCustomTabs
    }

    fun openUrlInBrowser(context: Context, uri: Uri) {

        val intentBrowsers = Intent(Intent.ACTION_VIEW)
        intentBrowsers.data = Uri.parse(uri.toString())
        val installedBrowsers = context.packageManager.queryIntentActivities(intentBrowsers, 0)

        if (installedBrowsers.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } else {
            AlertDialog.Builder(context)
                .setTitle(R.string.error_no_browser_installed_title)
                .setMessage(R.string.error_no_browser_installed_text)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }
}

fun Uri.openInCustomTab(context: Context) {

    val packagesSupportingCustomTabs = getCustomTabsPackages(context)

    // if there are browsers which support Custom Tab open with it
    if (packagesSupportingCustomTabs?.isNotEmpty() == true) {
        val builder = CustomTabsIntent.Builder()

        val params = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.white))
            .setNavigationBarColor(ContextCompat.getColor(context, R.color.black))
            .build()

        builder.apply {
            setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_LIGHT, params)
            setStartAnimations(context, R.anim.slide_up_full, android.R.anim.fade_out)
            setExitAnimations(context, android.R.anim.fade_in, R.anim.slide_down_full)
            setShowTitle(true)
            setCloseButtonIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_baseline_close_24
                )
            )
            build()
        }

        try {
            builder.build().launchUrl(context, this)
        } catch (e: Exception) {
            openUrlInBrowser(context, this)
        }
    }
    // if there are no browsers which support Custom Tab, open the URL directly in browser
    else {
        openUrlInBrowser(context, this)
    }
}