This is a Kotlin Multiplatform project targeting Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

[Replicando WINDOWS 95 con Kotlin Multiplatform Escritorio - Curso Avanzado de KMP desktop.](https://www.youtube.com/watch?v=QFPTUwFW9p8)
min:02:12:00
[Example Windows 95 Minesweeper](https://minesweepergame.com/download/windows-95-minesweeper.php)
[Windows 95 onLine](https://www.pcjs.org/software/pcx86/sys/windows/win95/4.00.950/)