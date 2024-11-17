package com.drimo_app.util

import com.drimo_app.data.api.DreamApi
import com.drimo_app.data.api.UserApi
import com.drimo_app.data.repository.CycleRepository
import com.drimo_app.util.Constants.Companion.BASE_URL
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
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthApi(retrofit: Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun providesDreamApi(retrofit: Retrofit) : DreamApi {
        return retrofit.create(DreamApi::class.java)
    }

    @Provides
    fun provideCycleRepository(): CycleRepository {
        return CycleRepository()
    }

}
