package com.example.myshootinrangediary.form

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.imageResource
import androidx.core.graphics.applyCanvas
import java.io.File
import java.util.*


@ExperimentalComposeUiApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun SignatureField(id: UUID)
{
        Column {
            val openDialog = remember { mutableStateOf(false)  }
            val action: MutableState<Any?> = mutableStateOf(null)
            val path = Path()

            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Wykonaj podpis")
            }

            if (openDialog.value) {
                AlertDialog(
                    modifier = Modifier.fillMaxHeight(),
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    title = {},
                    text = {
                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                                .pointerInteropFilter {
                                    when (it.action) {
                                        MotionEvent.ACTION_DOWN -> {
                                            action.value = it.action
                                            path.moveTo(it.x, it.y)
                                        }
                                        MotionEvent.ACTION_MOVE -> {
                                            action.value = it.action
                                            path.lineTo(it.x, it.y)
                                        }
                                        MotionEvent.ACTION_UP -> {
                                            action.value = it.action
                                        }
                                        else -> false
                                    }
                                    true
                                },

                        )
                        {
                            action.value?.let {
                                drawPath(
                                    path = path,
                                    color = Color.Black,
                                    alpha = 1f,
                                    style = Stroke(5f)
                                )
                            }
                        }
                    },
                    confirmButton = {
                        val view = LocalView.current
                        val context = LocalContext.current
                        Button(
                            onClick = {
                                // Save bitmap as file
                                val handler = Handler(Looper.getMainLooper())
                                handler.postDelayed(Runnable {
                                    val bmp = Bitmap.createBitmap(view.width, view.height,
                                        Bitmap.Config.ARGB_8888).applyCanvas {
                                        view.draw(this)
                                    }

                                    val filename: String = String.format("%s.png", id.toString())

                                    bmp.let {
                                        File(context.filesDir, filename)
                                            .writeBitmap(
                                                bmp,
                                                Bitmap.CompressFormat.PNG,
                                                85
                                            )
                                    }
                                    openDialog.value = false // close dialog
                                }, 1000)
                            }
                        )
                        {
                            Text("Zapisz podpis")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Zamknij okno")
                        }
                    }
                )
            }
        }
}

private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}