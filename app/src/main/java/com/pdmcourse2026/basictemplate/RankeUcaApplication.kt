package com.pdmcourse2026.basictemplate

import android.app.Application
import com.pdmcourse2026.basictemplate.data.app.AppProvider

class RankeUcaApplication : Application() {
  val appProvider by lazy { AppProvider(this) }
}
