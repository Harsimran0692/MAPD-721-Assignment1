package com.example.mapd_721_assignment1

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test() // Call the Test composable to display the UI
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Preview(showBackground = true)
@Composable
fun Test() {
    // Get instances of FocusManager, Context, and DataStore
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val dataStore = DataStore(context)

    // State variables for input fields
    var studentId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var courseName by remember { mutableStateOf("") }

    // State variables for stored data
    var storedStudentId by remember { mutableStateOf("") }
    var storedUserName by remember { mutableStateOf("") }
    var storedCourseName by remember { mutableStateOf("") }

    // Function to handle Load button click
    fun loadButtonClicked() {
        kotlinx.coroutines.GlobalScope.launch {
            dataStore.getData().collect { data ->
                // Update stored data state variables
                storedStudentId = data.first
                storedUserName = data.second
                storedCourseName = data.third
            }
            println(storedStudentId) // Print stored student ID for debugging
        }
    }

    // Function to handle Store button click
    fun storeButtonClicked() {
        // Validate input fields
        if (studentId.isEmpty() || courseName.isEmpty() || userName.isEmpty()) {
            return Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
        }
        // Save data to DataStore
        kotlinx.coroutines.GlobalScope.launch {
            dataStore.saveData(studentId, userName, courseName)
        }
        Toast.makeText(context, "Data stored successfully!", Toast.LENGTH_SHORT).show()
    }

    // Function to handle Reset button click
    fun resetButtonClicked() {
        // Clear DataStore and reset state variables
        kotlinx.coroutines.GlobalScope.launch {
            dataStore.clearData()
            studentId = ""
            userName = ""
            courseName = ""
            storedStudentId = ""
            storedUserName = ""
            storedCourseName = ""
        }
        Toast.makeText(context, "Data Reset successfully!", Toast.LENGTH_SHORT).show()
    }

    // Scaffold to structure the UI with a TopAppBar and content
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0, 0, 179) // Set top bar color
                ),
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "MAPD-721 | Assignment 1",
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
                        detectTapGestures { focusManager.clearFocus() } // Clear focus on tap outside
                    }
                    .padding(16.dp), // Add padding to the column
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Header Text
                Text(
                    text = "Please Enter Your Details",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0, 128, 255), // Set text color
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp), // Add vertical padding
                )

                // Input fields for student ID, username, and course name
                TextFields(
                    studentId,
                    onStudentIdChange = { studentId = it },
                    userName,
                    onStudentNameChange = { userName = it },
                    courseName,
                    onStudentCourseNameChange = { courseName = it }
                )

                // Buttons for Load, Store, and Reset actions
                CustomButtons(
                    loadButtonClicked = { loadButtonClicked() },
                    storeButtonClicked = { storeButtonClicked() },
                    resetButtonClicked = { resetButtonClicked() }
                )

                // Display stored results
                DisplayResult(storedStudentId, storedUserName, storedCourseName)
            }
        }
    )
}

@Composable
fun TextFields(
    studentId: String,
    onStudentIdChange: (String) -> Unit,
    studentName: String,
    onStudentNameChange: (String) -> Unit,
    courseName: String,
    onStudentCourseNameChange: (String) -> Unit
) {
    // Text field for Student ID
    OutlinedTextField(
        value = studentId,
        onValueChange = onStudentIdChange,
        label = { Text("Student Id") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = MaterialTheme.shapes.medium, // Rounded corners
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Text field for Username
    OutlinedTextField(
        value = studentName,
        onValueChange = onStudentNameChange,
        label = { Text("Username") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = MaterialTheme.shapes.medium,
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Text field for Course Name
    OutlinedTextField(
        value = courseName,
        onValueChange = onStudentCourseNameChange,
        label = { Text("Course Name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = MaterialTheme.shapes.medium,
    )
}

@Composable
fun CustomButtons(
    loadButtonClicked: () -> Unit,
    storeButtonClicked: () -> Unit,
    resetButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing between buttons
    ) {
        // Load button
        ElevatedButton(
            onClick = loadButtonClicked,
            modifier = Modifier.weight(1f), // Distribute space evenly
            shape = MaterialTheme.shapes.small, // Rounded corners
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black, // Text color
                containerColor = Color(255, 208, 0) // Button color
            )
        ) {
            Text("Load", fontSize = 18.sp)
        }

        // Store button
        ElevatedButton(
            onClick = storeButtonClicked,
            modifier = Modifier.weight(1f),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, // Text color
                containerColor = Color(6, 64, 43) // Button color
            )
        ) {
            Text("Store", fontSize = 18.sp, color = Color.White)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Reset button
    ElevatedButton(
        onClick = resetButtonClicked,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White, // Text color
            containerColor = Color(190, 0, 0) // Button color
        )
    ) {
        Text("Reset", fontSize = 18.sp, color = Color.White)
    }

    Spacer(modifier = Modifier.height(26.dp))
}

@Composable
fun DisplayResult(
    studentId: String,
    userName: String,
    courseName: String
) {
    // Box to display stored results
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(216, 216, 255)) // Background color
            .height(200.dp)
            .clip(MaterialTheme.shapes.medium), // Rounded corners
        contentAlignment = Alignment.CenterStart,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp), // Padding inside the column
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            // Display Student ID
            Text(
                text = "Student Id: $studentId",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )

            // Display Username
            Text(
                text = "Username: $userName",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}