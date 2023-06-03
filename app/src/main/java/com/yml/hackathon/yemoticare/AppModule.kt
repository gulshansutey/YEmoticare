package com.yml.hackathon.yemoticare

import co.yml.ychat.YChat
import co.yml.ychat.entrypoint.features.ChatCompletions
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
    @Singleton
    @Provides
    fun provideYChatCompletion(): ChatCompletions {
        return  YChat.create(BuildConfig.API_KEY).chatCompletions()
            .setMaxTokens(1024)
            .addMessage(
                "system",
                "You are a Health Care Assistant to help people deal with mental wellness. You will only answer questions in the mental wellness domain. If any question does not belong to the mental wellness domain, answer \"I am sorry, I cannot answer this question\""
            )
            .setMaxResults(1)
            .setTemperature(1.0)
            .setTopP(1.0)
    }

}
