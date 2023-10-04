# SquirrelScout_Scouter

## Command Line

### Initial Setup

```sh
./dk dksdk.cmake.link
./dk dksdk.ninja.link
./dk dksdk.java.jdk.download NO_SYSTEM_PATH
./dk dksdk.gradle.download ALL NO_SYSTEM_PATH
./dk dksdk.android.ndk.download NO_SYSTEM_PATH

printf 'sdk.dir=.ci/local/share/android-sdk\ncmake.dir=.ci/cmake\n' > local.properties
```

You can verify parts of the setup are working by running:

```sh
./dk dksdk.gradle.run ARGS -q javaToolchains
```

where you will see something like:

```text
 + Options
     | Auto-detection:     Enabled
     | Auto-download:      Enabled

+ Eclipse Temurin JDK 17.0.6+10
     | Location:           /home/YOURNAME/source/SquirrelScout_Scouter/.ci/local/share/jdk
     | Language Version:   17
     | Vendor:             Eclipse Temurin
     | Architecture:       amd64
     | Is JDK:             true
     | Detected by:        Gradle property 'org.gradle.java.installations.paths'
```

### WSL 2 Graphics (Windows Only)

WSL 2 needs manual steps for graphics to be enabled. They are available at https://learn.microsoft.com/en-us/windows/wsl/tutorials/gui-apps, and you only need to follow some of its steps:

* `Install support for Linux GUI apps`
* `Run Linux GUI apps > Update the packages in your distribution`
* `Run Linux GUI apps > Install X11 apps`

A paid alternative is https://x410.dev/. If you use x410, then install fonts with https://x410.dev/cookbook/wsl/sharing-windows-fonts-with-wsl/, use the "Floating Desktop" mode, and run the following before any graphical applications like the Android Emulator:

```sh
export GDK_SCALE=1 DISPLAY=$(grep nameserver /etc/resolv.conf | awk '{print $2; exit;}'):0.0
```

### WSL 2 Android Emulator (Windows Only)

Inside Android Studio you can (and should) install a Virtual Device (aka. the Android Emulator) in the `Tools > Device Manager` menu. It run it within Android Studio requires some minor manual steps.

Follow the steps in https://serverfault.com/a/1115773

### Testing

```sh
./dk dksdk.gradle.run ARGS check
```

You should see a lot of output, but the end should look like:

```text
BUILD SUCCESSFUL in 1m 28s
43 actionable tasks: 43 executed
```
