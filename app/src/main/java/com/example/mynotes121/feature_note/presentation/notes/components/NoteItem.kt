package com.example.mynotes121.feature_note.presentation.notes.components

import androidx.annotation.ColorInt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotes121.feature_note.domain.model.Note
import com.example.mynotes121.feature_note.domain.util.NoteOrder
import com.example.mynotes121.ui.theme.RedOrange

@Composable
fun NoteItem(
    note:Note,
    backgroundColor: Color,
    modifier:Modifier=Modifier,
    onDeleteClick:()->Unit
    
){

    val roundedCornerShape: Shape = RoundedCornerShape(60.dp)
    Box( modifier = modifier.background(backgroundColor, RoundedCornerShape(10.dp))){
        Column(modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(end = 32.dp)) {
        Text(text = note.title,color=Color.Black,
            maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 20.sp, fontWeight = FontWeight.W500)
        Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content,color=Color.Black,
                maxLines = 10, fontSize = 14.sp, fontWeight = FontWeight.Normal, overflow = TextOverflow.Ellipsis)

        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Notes",
                tint = Color.Black // Change the icon color to black
            )
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    NoteItem(note = Note("Hello","this is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for previewthis is the content for preview",9485,  RedOrange.toArgb()), onDeleteClick = {})
//}