package ui.wireframe.component.menubar.about

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun AboutDialog(onCloseRequest: () -> Unit) {
    AlertDialog(
        text = {
            Column {
                Text(
                    "WireFrame",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )
                Text(
                    "Wireframe application that forms b-spline curve from anchor points and then translates it to wireframe model.",
                    fontSize = 12.sp,
                    lineHeight = 24.sp
                )
                Text(
                    "B-spline editing",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )
                Text(
                    "B-spline editing is done in dynamic mode: all changed in b-spline editor are shown in wireframe window at the same time.",
                    fontSize = 12.sp,
                    lineHeight = 24.sp
                )
                Text(
                    "In b-spline editor you can add new anchor points and edit them:\n" +
                    "\n" +
                    "> Move center of axes\n" +
                    "> Zoom b-spline\n" +
                    "> Delete anchor point in any place\n",
                    fontSize = 12.sp,
                    lineHeight = 24.sp
                )
            }
        },
        onDismissRequest = onCloseRequest,
        confirmButton = {
            TextButton(onClick = onCloseRequest) {
                Text("Close")
            }
        },
    )
}