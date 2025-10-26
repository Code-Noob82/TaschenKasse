import UIKit
import SwiftUI
import shared // Importiert das kompilierte 'shared'-Modul

// SwiftUI-Ansicht, die den Compose-Code hostet
struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.all) // Compose steuert die Safe Areas selbst
    }
}

// Brücke zwischen SwiftUI und Compose (via UIViewController)
struct ComposeView: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> UIViewController {
        // Ruft die "MainViewController"-Funktion auf
        return Main_iosKt.createMainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}




