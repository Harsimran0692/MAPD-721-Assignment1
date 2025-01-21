package com.example.mapd_721_assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test();
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Test() {
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0, 0, 179)
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                    ){
                        Text("MAPD-721 | Assignment 1",
                            color = Color.White,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.W400,
                            )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .pointerInput(Unit) {
                        detectTapGestures { focusManager.clearFocus() }
                    }
                    .padding(16.dp), // Add some padding to the whole column for better spacing
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Header Text with improved styling
                Text(
                    text = "Please Enter Your Details",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0, 128, 255), // Purple color for title
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp), // Vertical padding for the header
                )

                // Input fields with better padding and rounded corners
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Student Id") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = MaterialTheme.shapes.medium, // Rounded corners
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Course Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                )

                // Action buttons with improved styling
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing between buttons
                ) {
                    ElevatedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f), // Make buttons fill available space evenly
                        shape = MaterialTheme.shapes.small, // Rounded corners
                        colors = ButtonDefaults.buttonColors( // Use buttonColors to change content color
                            contentColor = Color.Black, // Set the text color here
                            containerColor = Color(255, 208, 0)
                        )
                    ) {
                        Text("Load", fontSize = 18.sp)
                    }
                    ElevatedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f),
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.buttonColors( // Use buttonColors to change content color
                            contentColor = Color.White, // Set the text color here
                            containerColor = Color(6, 64, 43)
                        )

                    ) {
                        Text("Store", fontSize = 18.sp, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Reset button with styling
                ElevatedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors( // Use buttonColors to change content color
                        contentColor = Color.White, // Set the text color here
                        containerColor = Color(190, 0, 0)
                    )
                ) {
                    Text("Reset", fontSize = 18.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.height(26.dp))

                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(216, 216, 255))
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.CenterStart,
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(16.dp), // Padding inside the column
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {
                        // Text items
                        Text(
                            text = "Id: ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                        Text(
                            text = "Username: ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Course Name: ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    )
}