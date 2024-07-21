package ui.wireframe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import ui.bspline.BSplineWindow
import ui.wireframe.component.dialog.ExitDialog
import ui.wireframe.component.menubar.about.AboutDialog
import java.awt.Dimension
import java.awt.MenuItem

@Composable
@Preview
fun WireFrameWindow(onCloseRequest: () -> Unit) {
    var showExitDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    var showBSplineWindow by remember { mutableStateOf(false) }

    Window(
        onCloseRequest = { showExitDialog = true },
    ) {
        window.minimumSize = Dimension(640, 480)

        if (showExitDialog) {
            ExitDialog(
                onConfirm = {
                    showExitDialog = false
                    onCloseRequest()
                },
                onDismiss = {
                    showExitDialog = false
                }
            )
        }

        if (showAboutDialog) {
            AboutDialog {
                showAboutDialog = false
            }
        }

        if (showBSplineWindow) {
            BSplineWindow {
                showBSplineWindow = false
            }
        }

        MenuBar {
            Menu("Edit") {
                Item("Edit b-spline") {
                    showBSplineWindow = true
                }
            }

            Menu("About") {
                Item("About") {
                    showAboutDialog = true
                }
            }
        }
    }
}