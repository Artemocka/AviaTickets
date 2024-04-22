package com.dracul.task.viewmodels

import androidx.lifecycle.ViewModel
import com.dracul.task.di.DaggerInjector
import com.dracul.task.domain.usecase.GetOffers
import com.dracul.task.domain.usecase.GetTicketsOffers
import dagger.Component
import dagger.Module
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@Component(modules = [CountryModule::class], dependencies = [CountryDependencies::class])
interface CountryComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: CountryDependencies): Builder
        fun build(): CountryComponent
    }

    fun inject(target: CountyViewModel)
}

@Module
class CountryModule

interface CountryDependencies {
    fun getTicketsOffers(): GetTicketsOffers
}
class CountyViewModel : ViewModel() {
    @Inject
    lateinit var getTicketsOffers: GetTicketsOffers

    init {
        DaggerCountryComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@CountyViewModel)
    }



}

