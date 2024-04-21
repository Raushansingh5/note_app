package com.example.mynotes121.feature_note.domain.use_case

data class NoteUseCases(
    val getNotes:GetNotes,
    val deleteNode: DeleteNode,
    val addNote: AddNote,
    val getNote: GetNote
)
