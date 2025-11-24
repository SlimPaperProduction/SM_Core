# SM_Core

SM_Core is a modular **framework for Paper plugins** that provides centralized managers for common tasks such as configuration, permissions, messaging, logging, tasks, menus, and more.  
It is designed to reduce boilerplate code and make plugin development faster, cleaner, and more consistent.

---

## ✨ Features

- **ModuleRegistry** – register and track modules with version and status
- **PermissionManager** – unified permission checks via LuckPerms
- **MessageManager** – consistent messages with prefixes, colors, and placeholders
- **ErrorHandler** – centralized logging with timestamps and module names
- **TaskManager** – schedule one‑time and repeating tasks via Bukkit scheduler
- **MenuManager** – inventory‑based GUIs with filler items and buttons
- **PlayerDataManager** – flexible key/value storage for player data
- **EconomyManager** – Vault integration for balances, deposits, and withdrawals
- **EventBus** – decoupled communication between modules

---

## 📦 Installation

1. Download the latest release of **SM_Core** from [GitHub Releases](https://github.com/SlimPaperProduction/SM_Core/releases).
2. Place the JAR file into your Paper server’s `plugins/` folder.
3. Start the server — SM_Core will initialize automatically.

---

## 🔌 Usage

Add **SM_Core** as a dependency in your plugin’s `plugin.yml`:

```yaml
depend: [SM_Core]
```

Access managers from your plugin:

```java
SM_Core core = SM_Core.getInstance();

// Example: check permissions
if (!core.getPermissionManager().hasPermission(player, "myplugin.use")) {
    core.getMessageManager().send(player, "System", "&cNo permission!");
    return;
}

// Example: log info
core.getErrorHandler().logInfo("MyPlugin", "Player joined: " + player.getName());

```

🧑‍💻 Example Plugins

```
BankingPlugin – demonstrates use of DatabaseManager, PermissionManager, CacheManager, and MessageManager

EconomyManager – integrates Vault for player balances

MenuManager – builds interactive GUIs with buttons and filler items
```

🤝 Contributing
```
Contributions are welcome!

Fork the repository

Create a feature branch (git checkout -b feature/my-feature)

Commit changes (git commit -m "Add my feature")

Push to your fork (git push origin feature/my-feature)

Open a Pull Request

Please follow the coding style and document new features with Javadoc.
```

📜 License
```
This project is licensed under the MIT License — see the LICENSE file for details.
```

🌐 Community
```
Issues: GitHub Issues

Discussions: Use the GitHub Discussions tab to ask questions or share ideas

Releases: GitHub Releases
```


🚀 With SM_Core you can focus on building features, not boilerplate.
Code
