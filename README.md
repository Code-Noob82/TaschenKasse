# 🎡 TaschenKasse  
**Einfache Offline-Kassen-App für Feste, Märkte & Verkaufsstände**

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

## 📁 Projektstruktur
```text
TaschenKasse/
 ├─ shared/
 │   ├─ src/commonMain/
 │   │   ├─ model/
 │   │   ├─ repository/
 │   │   ├─ datastore/
 │   │   └─ viewmodel/
 │   ├─ src/androidMain/
 │   └─ src/iosMain/
 ├─ androidApp/
 └─ iosApp/
