# 🎡 TaschenKasse
**Einfache Offline-Kassen-App für Feste, Märkte & Verkaufsstände**

[![Build Status](https://img.shields.io/github/actions/workflow/status/Code-Noob82/TaschenKasse/build.yml?branch=main&label=Build)](https://github.com/Code-Noob82/TaschenKasse/actions)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Downloads](https://img.shields.io/github/downloads/Codenoob/TaschenKasse/total.svg)](https://github.com/Code-Noob82/TaschenKasse/releases)
[![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20iOS-lightgrey)](#)

---

## 🧭 Überblick
**TaschenKasse** ist eine minimalistische, werbefreie Kassen-App – entwickelt für Jahrmärkte, Vereinsfeste, Ausschankwagen und Weihnachtsmärkte.  
Sie ersetzt komplizierte Kassensysteme durch eine einfache, intuitive Tablet-App: Artikel antippen, Gesamtbetrag sehen, kassieren, fertig.

> 💡 Ziel: Eine Kasse, die jeder bedienen kann – ohne Internet, ohne TSE-Pflicht, ohne Stress.

---

## ✨ Hauptfunktionen
- 🧮 **20 Artikeltasten** – Name und Preis frei konfigurierbar
- ✏️ **Long-Press zum Bearbeiten** – Artikel oder Preise direkt ändern
- 🔢 **Mehrfach-Mengen per Ziffernwahl (1–9)** – z. B. „3 + Bier“ → 3 Bier
- 🛒 **Warenkorb mit Live-Summe** – übersichtliche Anzeige für Bedienpersonal
- 🔁 **Neuer-Vorgang-Button** – Warenkorb löschen, nächster Kunde
- ⚡ **Offline-Nutzung** – kein Login, keine Cloud, keine Werbung
- 💾 **Lokale Speicherung** – Preise bleiben auch nach Neustart erhalten

---

## 🧱 Technischer Aufbau

| Layer | Technologie | Beschreibung |
|-------|--------------|--------------|
| **UI** | [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) | Gemeinsames UI für Android & iOS |
| **Architektur** | MVVM + Repository Pattern | Klare Trennung von Logik & UI |
| **State Management** | Kotlin Flow | Reaktive Zustandsverwaltung |
| **Persistenz** | Multiplatform Settings (DataStore-ähnlich) | Artikeldaten & Preise offline speichern |
| **DI** | Koin | Leichtgewichtig & KMP-kompatibel |
| **Buildsystem** | Gradle KMP | Android & iOS in einem Projekt |

---

## 📱 Beispiel-Workflow

1. App starten
2. Artikel mit Langdruck konfigurieren (z. B. „Bier – 3,50 €“)
3. Per Fingertipp verkaufen
4. Menge mit Zahlen 1–9 multiplizieren
5. Nach Zahlung → „Neuer Kunde“ drücken

---

## 🧠 Roadmap

v1.0.0  →  MVP mit Artikeltasten & Warenkorb          ✅ in Entwicklung  
v1.2.0  →  CSV-Export & Umsatzübersicht               🕐 geplant  
v1.3.0  →  Bondrucker-Integration (optional)          🔜 in Vorbereitung  
v1.0.0  →  Veröffentlichung im Play Store & App Store 🚀 geplanter Release

---

## 🖼️ Screenshots



---

## 💡 Warum Kotlin Multiplatform?

- Gemeinsame Business-Logik für Android & iOS 
- Einheitliches Verhalten, weniger Wartung 
- Moderne Compose UI für beide Plattformen 
- Schnellere Entwicklung zukünftiger Features

--- 

## 🧑‍💻 Entwickler

Dominik Baki App-Entwickler aus Mannheim, Deutschland
👉 byteundhandwerk.de

---

## 📄 Lizenz

Dieses Projekt steht unter der MIT License.

---

🪙 TaschenKasse – Kassieren leicht gemacht.
Für alle, die auf dem Fest lieber ausschenken als rechnen wollen 🍻