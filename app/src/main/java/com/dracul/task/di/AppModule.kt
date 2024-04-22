package com.dracul.task.di

import com.dracul.task.data.repository.GetOffersImpl
import com.dracul.task.data.storage.NetworkOffersStorage
import com.dracul.task.data.storage.api.OffersApi
import com.dracul.task.domain.repository.GetOffersRepo
import com.dracul.task.domain.usecase.GetOffers
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object AppModule {
    @Provides
    fun provideApi(): OffersApi {
        // Whenever Dagger needs to provide an instance of type LoginRetrofitService,
        // this code (the one inside the @Provides method) is run.
        return Retrofit.Builder()
            .baseUrl("https://drive.google.com/file/d/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OffersApi::class.java)
    }
    @Provides
    fun provideNetworkStorage(api:OffersApi):NetworkOffersStorage{
        return NetworkOffersStorage(api)
    }
    @Provides
    fun provideRepository(newtworkSotrage:NetworkOffersStorage):GetOffersRepo{
        return GetOffersImpl(newtworkSotrage)
    }
    @Provides
    fun provideUseCase(repository:GetOffersRepo): GetOffers {
        return GetOffers(repository)
    }


}