package com.example.mynotes121.feature_note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mynotes121.feature_note.domain.model.Note
import com.example.mynotes121.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor:Int,
    viewModel: AddEditNoteViewModel= hiltViewModel()
){
    val titleState=viewModel.noteTitle.value
    val contentStae=viewModel.noteContent.value
    val snackbarState= remember {
     SnackbarHostState()
    }
    val noteBackgroundAnimatable=remember{
        Animatable(
            Color(if (noteColor!=-1){
                noteColor
            }
            else viewModel.noteColor.value)
        )
    }

    val scope= rememberCoroutineScope()

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest {
            event->
            when(event){
                is AddEditNoteViewModel.UiEvent.ShowSnackbar->{
                    snackbarState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is AddEditNoteViewModel.UiEvent.SaveNote->{
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
                       SnackbarHost (hostState = snackbarState)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.OnEvent(AddEditNoteEvent.SaveNote)
            },containerColor = Color.Black ,) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "", tint = Color.White)
            }
        }
    ) {
        it->
        Column(
            modifier= Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp)
                .padding(it)
        ) {
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color->
                    val colorInt=color.toArgb()
                    Box(modifier = Modifier
                        .size(45.dp)
                        .size(50.dp)
                        .shadow(15.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = 3.dp,
                            color = if (viewModel.noteColor.value == colorInt) {
                                Color.Black
                            } else Color.Transparent, shape = CircleShape
                        )
                        .clickable {
                            scope.launch {
                                noteBackgroundAnimatable.animateTo(
                                    targetValue = Color(colorInt),
                                    animationSpec = tween(
                                        durationMillis = 500
                                    )
                                )
                            }
                            viewModel.OnEvent(AddEditNoteEvent.ChangeColor(colorInt))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                ),
                hint = titleState.hint,
                onValueChange = {
                    viewModel.OnEvent(AddEditNoteEvent.EnteredTitle(it))
                }, onFocusChange ={
                    viewModel.OnEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                }, isHintVisible = titleState.isHintVisible,
                singleLine = true,
                )

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = contentStae.text,
                textStyle = TextStyle(
                    fontSize = 14.sp,

                ),
                hint = contentStae.hint,
                onValueChange = {
                    viewModel.OnEvent(AddEditNoteEvent.EnteredContent(it))
                }, onFocusChange ={
                    viewModel.OnEvent(AddEditNoteEvent.ChangeContentFocus(it))
                }, isHintVisible = contentStae.isHintVisible,
                modifier=Modifier.fillMaxHeight()
            )
        }
    }

}