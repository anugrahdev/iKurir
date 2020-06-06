package com.anugrahdev.app.klikPaket

import android.app.Application
import com.anugrahdev.app.klikPaket.data.db.AppDatabase
import com.anugrahdev.app.klikPaket.data.network.ApiService
import com.anugrahdev.app.klikPaket.data.network.NetworkInterceptor
import com.anugrahdev.app.klikPaket.data.repositories.CostRepository
import com.anugrahdev.app.klikPaket.data.repositories.WaybillRepository
import com.anugrahdev.app.klikPaket.preferences.PreferenceProvider
import com.anugrahdev.app.klikPaket.ui.shipmentcost.CostViewModelFactory
import com.anugrahdev.app.klikPaket.ui.trackwaybill.WaybillViewModelFactory
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class MyApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { CostRepository(instance()) }
        bind() from singleton { WaybillRepository(instance(),instance()) }
        bind() from provider { CostViewModelFactory(instance()) }
        bind() from provider { WaybillViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}