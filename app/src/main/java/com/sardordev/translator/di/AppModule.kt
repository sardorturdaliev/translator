package com.sardordev.translator.di

import com.sardordev.translator.data.api.TransApi
import com.sardordev.translator.data.database.DataBaseSaved
import com.sardordev.translator.domain.apprepository.AppRepository
import com.sardordev.translator.domain.apprepository.AppRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getTranslateApi(): TransApi {
        return Retrofit.Builder()
            .baseUrl("https://ai-translate.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TransApi::class.java)
    }

    @Singleton
    @Provides
    fun getRepository(transApi: TransApi,dataBaseSaved: DataBaseSaved): AppRepository {
        return AppRepositoryImp(transApi,dataBaseSaved)
    }


    @Singleton
    @Provides
    fun getDataBaseSaved(): DataBaseSaved {
        return DataBaseSaved.getInstance()
    }




}