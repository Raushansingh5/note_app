package com.example.mynotes121.di

import android.app.Application
import androidx.room.Room
import com.example.mynotes121.feature_note.data.data_source.NoteDatabase
import com.example.mynotes121.feature_note.data.repository.NoteRespositoryImpl
import com.example.mynotes121.feature_note.domain.repository.NoteRepository
import com.example.mynotes121.feature_note.domain.use_case.AddNote
import com.example.mynotes121.feature_note.domain.use_case.DeleteNode
import com.example.mynotes121.feature_note.domain.use_case.GetNote
import com.example.mynotes121.feature_note.domain.use_case.GetNotes
import com.example.mynotes121.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesRepository(db:NoteDatabase):NoteRepository{
        return NoteRespositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNode = DeleteNode(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}