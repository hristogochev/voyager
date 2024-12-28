import androidx.compose.ui.window.Window
import io.github.hristogochev.vortex.sample.multiplatform.SampleApplication
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
    NSApplication.sharedApplication()
    Window("Vortex") {
        SampleApplication()
    }
    NSApp?.run()
}
