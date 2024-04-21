package com.example.mynotes121

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynotes121.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.mynotes121.feature_note.presentation.notes.NotesScreen
import com.example.mynotes121.feature_note.presentation.util.Screen
import com.example.mynotes121.ui.theme.MyNotes121Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MyNotes121Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController= rememberNavController()

                    NavHost(navController = navController,
                        startDestination = Screen.NoteScreen.route){

                        composable(route=Screen.NoteScreen.route){
                            NotesScreen(navController = navController)
                        }

                        composable(route=Screen.AddEditNoteScreen.route+
                                "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument(
                                name="noteId"
                            ){
                                type= NavType.IntType
                                defaultValue=-1
                            },
                            navArgument(
                                name="noteColor"
                            ){
                                type= NavType.IntType
                                defaultValue=-1
                            }
                        )){
                            val color=it.arguments?.getInt("noteColor")?:-1
                            AddEditNoteScreen(navController = navController, noteColor = color)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNotes121Theme {

    }
}