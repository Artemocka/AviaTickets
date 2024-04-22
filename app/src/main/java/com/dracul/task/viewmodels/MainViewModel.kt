package com.dracul.task.viewmodels

import androidx.lifecycle.ViewModel
import com.dracul.task.di.DaggerInjector
import com.dracul.task.domain.usecase.GetOffers
import dagger.Component
import dagger.Module
import javax.inject.Inject


@Component(modules = [MainModule::class], dependencies = [MainDependencies::class])
interface MainComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: MainDependencies): Builder
        fun build(): MainComponent
    }

    fun inject(target: MainViewModel)
}

@Module
class MainModule

interface MainDependencies {
    fun getOffers(): GetOffers
}


class MainViewModel : ViewModel() {
    @Inject
    lateinit var getOffers:GetOffers

    init {
        DaggerMainComponent.builder()
            .dependencies(dependencies = DaggerInjector.appComponent)
            .build()
            .inject(this@MainViewModel)


    }

    fun getOffersList()=getOffers.execute().offers




}

