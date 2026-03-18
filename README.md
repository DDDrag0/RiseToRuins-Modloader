# Rise to Ruins Mod Loader

A lightweight, non-intrusive mod loader for the game **Rise to Ruins**.  
It allows you to load modifications (mods) without altering the original game files, provides a simple graphical interface to manage them, and handles all the technical complexities (class loading, native libraries, dependencies) automatically.

---

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [How to Use](#how-to-use)
  - [Mod Structure](#mod-structure)
  - [Managing Mods](#managing-mods)
  - [Launching the Game](#launching-the-game)
- [For Developers](#for-developers)
  - [Building from Source](#building-from-source)
  - [Bytecode Viewer Plugin (Complementary Tool)](#bytecode-viewer-plugin-complementary-tool)
- [Upcoming Features](#upcoming-features)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

## Features

- **Non‑intrusive** – the original `core.jar` and game files are never modified.
- **Drag & drop installation** – drop `.zip` files directly into the window.
- **Full mod management** – enable, disable or delete mods with one click.
- **Multi‑mod support** – load several mods at the same time; the custom class loader merges them transparently.
- **Real‑time log** – see exactly what happens during installation and game launch.
- **Automatic native handling** – detects and configures LWJGL native libraries (`natives/` or `lib/natives/`).
- **Preserves original game** – the game itself stays untouched; the loader runs alongside it.
- **Open source (MIT)** – fork, modify, and share your own versions freely.

---

## Requirements

- **Java Runtime Environment 8** (major version 52) or +
- **Rise to Ruins** (last version) installed on your computer.
- Operating system: Windows / Linux / macOS (the loader should be platform‑independent, but the game’s native libraries differ).

---

## Installation

1. **Download** the latest `ModLoader.jar` from the [Releases](https://github.com/DDDrag0/RiseToRuins-Modloader/releases) page.
2. **Place** the downloaded `.jar` file **inside your Rise to Ruins game folder** – the same folder that contains `core.jar` (usually where `RtR64.exe` or the main launcher resides).
3. **Ensure Java 8** or + is available on your system. You can check by opening a terminal and typing `java -version`.
4. **Run** the loader by double‑clicking `ModLoader.jar` or by executing:
   ```
   java -jar ModLoader.jar
   ```

On first launch, the loader will create a `mods/` folder (where all installed mods will be stored) and, if missing, a `natives/` folder (used for LWJGL native libraries, for some reasons i had problems).

---

## How to Use

### Mod Structure

Mods must be packaged as **`.zip` files** with the following internal structure:

```
YourModName.zip
└── rtr/
    └── objects/
        └── objectflags/
            └── village/
                └── Modified.class
```

- The zip file’s name (without the `.zip` extension) becomes the mod name displayed in the list.
- Inside the zip, the folder hierarchy must exactly match the Java package structure of the classes you want to replace.
- You can include multiple modified classes in one zip; the loader will scan all `.class` files recursively.

> **Important:** The loader currently only accepts `.zip` files. Single `.class` files or other archive formats (`.jar`, `.rar`) are not supported – this keeps the installation process simple and consistent.

### Managing Mods

1. **Launch** the loader – the main window appears.
2. **Drag & drop** any `.zip` mod onto the window, or click the `📂 Drop .zip files here` area to select a file via file chooser.
3. If a mod with the same name already exists, the loader will ask whether you want to **overwrite** it.
4. Installed mods appear in the left panel under **Installed Mods**.
5. **Right‑click** on any mod to open a context menu:
   - **Enable** – the mod will be loaded the next time you start the game.
   - **Disable** – the mod will be ignored (kept in the folder but not loaded).
   - **Delete** – permanently remove the mod folder.
6. Use the top buttons:
   - **Enable all** / **Disable all** – quickly toggle all mods.
   - **Refresh mods** – rescan the `mods/` folder (useful if you added files manually).

### Launching the Game

1. After enabling the desired mods, click `🚀 Start game`.
2. The loader will:
   - Collect all enabled mods.
   - Set up a custom class loader that intercepts class requests.
   - Configure the path to native libraries (`natives/` or `lib/natives/`).
   - Launch the original game (by calling `rtr.system.Launcher.main()`).
3. The game starts normally – you will see its own window. Meanwhile, the loader’s log panel shows progress messages.
4. When you exit the game, the loader returns to its ready state.

All enabled mods are applied **transparently** – the game behaves as if the original classes were replaced, but no files have been touched.

---

## For Developers

### Building from Source
> ⚠️ **Important:**
> I'm developing the modloader inside the game folder, so if errors appear, the solution is most likely to develop there. You can copy the game folder into any folder you choose, as the game always works for testing.
The project is structured as a standard Java application.

To build it yourself:

1. Clone the repository:
   ```
   git clone https://github.com/DDDrag0/RiseToRuins-Modloader.git
   cd rise-to-ruins-modloader
   ```
2. Ensure you have **JDK 8** or + installed, but i recomend 8 for no problems with newer versions as the game uses JDK 8.
3. Compile the sources (all classes are in the `rtrModGui` package):
   ```
   javac -d out rtrModGui/*.java
   ```
4. Create a runnable JAR with a manifest pointing to `rtrModGui.MainGUI`:
   ```
   jar cfe ModLoader.jar rtrModGui.MainGUI -C out .
   ```
5. The resulting `ModLoader.jar` can be placed in the game folder and executed.

### Bytecode Viewer Plugin (Complementary Tool)

To easily create mods I strongly recommend Bytecode Viewer under CFR decompiler, you can also use the companion plugin for **Bytecode Viewer** – **Export Modified Only**. This plugin allows you to export only the classes you have modified during a reverse‑engineering session, directly as a zip file ready to be dropped into the mod loader.

- **Repository:** [ExportModifiedOnly](https://github.com/DDDrag0/ExportModifiedOnly-Bytecode-Viewer-Plugin)
- **Installation:**
  1. Download the JAR: Grab the latest `ExportModifiedOnly.jar` from the [Releases](https://github.com/DDDrag0/ExportModifiedOnly-Bytecode-Viewer-Plugin/releases) page.
  2. In BCV go to Plugin → Open Plugin... → It should open a file explorer, select the `ExportModifiedOnly.jar`.
  3. Click Run so that it is active 
- **Usage:**  
  1. Open a jar/class in Bytecode Viewer.  
  2. Run the plugin (first time captures original state).  
  3. Modify your classes and save the workspace.  
  4. Run the plugin again – a zip containing only the modified classes is created in the same directory as your last save.

This plugin is maintained separately and is distributed under the GNU General Public License v3.0, the same license as Bytecode Viewer.

---

## Upcoming Features

The following enhancements are planned for future releases:

- **Persistent mod state** – the loader will remember which mods are enabled/disabled between sessions.
- **Support for additional structures** – allow mods that modify not only `ObjectFlags` but also tiles, entities, or other game components.
- **In‑app mod information** – display a short description, author, and version for each mod (via a `mod.json` file inside the zip).
- **Mod conflict detection** – warn if two mods try to modify the same class.

Your feedback and contributions are always welcome!

---

## License

This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for the full text.

You are free to use, modify, and distribute this software, provided that the original copyright and permission notice are included.

---

## Acknowledgments

- **Rayvolution** – the developer of *Rise to Ruins*, for his open attitude toward modding and the helpful community.
- **Bytecode Viewer** – the indispensable reverse‑engineering tool that made this project possible.

---

*Un ringraziamento speciale a DeepSeek (assistente AI) ed Anthony per il supporto tecnico, i consigli sull'architettura del software e l'aiuto nella risoluzione di numerosi problemi durante lo sviluppo del mod loader.*

*This mod loader was developed as part of a bachelor’s thesis in Computer Science at the University of Salerno.*
