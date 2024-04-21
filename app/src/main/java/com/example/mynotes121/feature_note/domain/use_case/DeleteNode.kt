package com.example.mynotes121.feature_note.domain.use_case

import com.example.mynotes121.feature_note.domain.model.Note
import com.example.mynotes121.feature_note.domain.repository.NoteRepository

class DeleteNode(
    private val repository:NoteRepository
) {
    suspend operator fun invoke(note:Note){
        repository.deleteNote(note)
    }

}