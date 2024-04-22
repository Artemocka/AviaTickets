package com.dracul.task.di

import com.dracul.task.viewmodels.CountryDependencies
import dagger.Component
import com.dracul.task.viewmodels.MainDependencies
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent: MainDependencies,CountryDependencies {

    @Component.Builder
    interface Builder{
        fun build():AppComponent
    }
}