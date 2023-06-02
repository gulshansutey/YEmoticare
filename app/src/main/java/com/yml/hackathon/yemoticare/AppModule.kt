package com.yml.hackathon.yemoticare

import co.yml.ychat.YChat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideYChat(): YChat {
        return YChat.create(BuildConfig.API_KEY)
    }

}
