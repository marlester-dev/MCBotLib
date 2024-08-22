[![MCBotLib](https://github.com/user-attachments/assets/6b9d0abe-651c-42d2-9d4c-e714dfbbe9f7)]()  
[![](https://jitpack.io/v/marlester-dev/MCBotLib.svg)](https://jitpack.io/#marlester-dev/MCBotLib)  
[MC 1.20.6 version](https://github.com/marlester-dev/MCBotLib-1.20.6)

This is a very simple library to add bots to Minecraft servers.  
It uses [MCProtocolLib](https://github.com/GeyserMC/MCProtocolLib).

Adding the library with Gradle:  
**Groovy DSL:**
```groovy
repositories {
    maven {
        url 'https://jitpack.io'
    }
    maven {
        url 'https://repo.opencollab.dev/maven-releases/'
    }
}

dependencies {
    implementation 'com.github.marlester-dev:MCBotLib:version_here'
}
```
**Kotlin DSL:**
```kotlin
repositories {
    maven {
        url = uri("https://jitpack.io")
    }
    maven {
        url = uri("https://repo.opencollab.dev/maven-releases/")
    }
}

dependencies {
    implementation("com.github.marlester-dev:MCBotLib:version_here")
}
```
Please see the latest version number on the [releases page](https://github.com/marlester-dev/MCBotLib/releases/latest).

##### Creating a simple bot with the nick "Steve" and letting it join a server with the ip of 6.113.148.5 and port 22605:
```java
Bot bot = new Bot("Steve", "6.113.148.5", 22605, BotSettings.builder().build());
bot.connect();
```

[Adding authentication details to the bot](https://github.com/marlester-dev/MCBotLib/wiki/Adding-auth-details)  
[Adding proxy to the bot](https://github.com/marlester-dev/MCBotLib/wiki/Adding-proxy)  
[Getting the Session and Protocol of the bot](https://github.com/marlester-dev/MCBotLib/wiki/Getting-the-Session-and-Protocol-of-the-bot)

For more please see the javadoc and MCProtocolLib's wiki.  
Don't be shy to ask questions in the issues tab.  
You may visit #mcprotocollib channel in [Geyser's discord server](https://discord.gg/geysermc), if you have questions about how MCProtocolLib functions.

A lot of code was taken from [SoulFire](https://github.com/AlexProgrammerDE/SoulFire).
