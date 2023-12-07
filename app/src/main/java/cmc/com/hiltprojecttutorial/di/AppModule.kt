package cmc.com.hiltprojecttutorial.di

import android.app.Application
import android.content.Context
import cmc.com.hiltprojecttutorial.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Các hàm cung cấp này khởi tạo static, nó giúp tăng hiệu năng của ứng dụng hơn
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providerDatabase(application: Application) = NoteDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providerNoteDao(database: NoteDatabase) = database.getNoteDao()
}