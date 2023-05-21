package com.example.handlingpermissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.handlingpermissions.ui.theme.HandlingPermissionsTheme

class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandlingPermissionsTheme {
                requestPermissionLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                        if (isGranted) {
                            // Permission is granted. Continue the action or workflow in your
                            // app.
                            Toast.makeText(applicationContext, "Oh yeah got permission", Toast.LENGTH_SHORT).show()

                        } else {
                            // Explain to the user that the feature is unavailable because the
                            // feature requires a permission that the user has denied. At the
                            // same time, respect the user's decision. Don't link to system
                            // settings in an effort to convince the user to change their
                            // decision.
                            Toast.makeText(applicationContext, "Permission to de", Toast.LENGTH_SHORT).show()
                        }
                    }

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    ) {


                        Button(
                            onClick = {
                                cameraPermission()
                            },

                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),

                            colors = ButtonDefaults.buttonColors(Color(0xFF2E8BC0)),
                            shape = RoundedCornerShape(50)

                        ) {
                            Text(text = "Camera", color = Color.White)
                        }

                        Button(
                            onClick = {
                                storagePermission()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),

                            colors = ButtonDefaults.buttonColors(Color(0xFF2E8BC0)),
                            shape = RoundedCornerShape(50)

                        ) {
                            Text(text = "Storage", color = Color.White)
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    ) {

                        Button(
                            onClick = {

                            },

                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),


                            colors = ButtonDefaults.buttonColors(Color(0xFF2E8BC0)),
                            shape = RoundedCornerShape(50)


                        ) {
                            Text(text = "Contacts", color = Color.White)
                        }

                        Button(
                            onClick = {

                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),

                            colors = ButtonDefaults.buttonColors(Color(0xFF2E8BC0)),
                            shape = RoundedCornerShape(50)

                        ) {
                            Text(text = "Location", color = Color.White)
                        }
                    }
                }

            }
        }
    }

    private fun storagePermission() {
        when {
            ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(applicationContext, "Storage permission already granted", Toast.LENGTH_SHORT).show()
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun cameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(this@MainActivity, "Camera Permission Already Granted!", Toast.LENGTH_SHORT).show()
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}