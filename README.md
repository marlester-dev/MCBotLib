[![MCBotLib](https://github.com/user-attachments/assets/6b9d0abe-651c-42d2-9d4c-e714dfbbe9f7)]()

This is a very simple library to add bots to Minecraft servers.  
It uses [MCProtocolLib](https://github.com/GeyserMC/MCProtocolLib).

Adding the library with Gradle:  
**Groovy DSL:**
```groovy
repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    implementation 'com.github.marlester-dev:MCBotLib:1.0.0'
}
```
**Kotlin DSL:**
```kotlin
repositories {
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.marlester-dev:MCBotLib:1.0.0")
}
```

##### Creating a simple bot with the nick "Steve" and letting it join a server with the ip of 6.113.148.5 and port 22605:
```java
Bot bot = new Bot("Steve", "6.113.148.5", 22605, BotSettings.builder().build());
bot.connect();
```

[Adding authentication details to the bot](https://github.com/marlester-dev/MCBotLib/wiki/Adding-auth-details)  
[Adding proxy to the bot](https://github.com/marlester-dev/MCBotLib/wiki/Adding-proxy)

For more please see the javadoc and MCProtocolLib's wiki.  
Don't be shy to ask questions in the issues tab.  
You may ask questions on [Geyser's discord](https://discord.gg/geysermc) server and visit #mcprotocollib channel, if you have questions about how MCProtocolLib functions.
