[![Download](https://api.bintray.com/packages/tues/sbt-plugins/sbt-runfast/images/download.svg)](https://bintray.com/tues/sbt-plugins/sbt-runfast/_latestVersion)
[![Build Status](https://travis-ci.org/tues/sbt-runfast.svg?branch=master)](https://travis-ci.org/tues/sbt-runfast)
[![Join the chat at https://gitter.im/sbt-runfast/Lobby](https://badges.gitter.im/sbt-runfast/Lobby.svg)](https://gitter.im/sbt-runfast/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# sbt-runfast

Generates a shell script which lets you easily run your main class without SBT (much faster than `sbt run`!).

## Usage

The plugin is hosted in [sbt-plugin-releases repository](https://bintray.com/sbt/sbt-plugin-releases) so you can simply add the following line to your global `~/.sbt/0.13/plugins/plugins.sbt` to make it available in all of your projects:

``` Scala
addSbtPlugin("pl.tues" % "sbt-runfast" % "0.1")
```

Alternatively, you can enable it per-project by adding the above line to `<your-project>/project/plugins.sbt`.

Next, run:

``` Shell
sbt runfastGenerate
```

inside your project. It will generate a script called `runfast.sh` in the `target/` directory. Running your program now is straightforward:

``` Shell
target/runfast.sh
```

The script uses absolute paths, so you can run it from anywhere:

``` Shell
/path/to/your/project/target/runfast.sh
```

You can even copy it somewhere else and run from there.

The script passes all arguments to your program. This was actually the reason I wrote the plugin, as I wanted to use Bash's temporary files as arguments to my program:

``` Shell
target/runfast.sh foo <(grep bar baz | head)
```

Speed is a nice side-effect.

## Requirements

This plugin requires `sbt 0.13.5+`, `/bin/bash` and `chmod`. I might add support for other platforms in the future, but I don't need it myself, so contributions are welcome.

## How does it work?

The plugin simply generates a file `target/runfast.sh` containing something like:

``` Shell
#!/bin/bash

java -cp '<fullClasspath>' '<mainClass>'
```

Really simple, but saves a lot of time!

## Contributing

Got an idea how to improve the code, tests or docs? All contributions are welcome!

### Building your own version

It's really simple, just run:

``` Shell
sbt publishLocal
```

and the plugin will be published to your local repository (e.g. `~/.ivy2/local/`). You can then change the version in your `plugins.sbt`:

``` Scala
addSbtPlugin("pl.tues" % "sbt-runfast" % "0.2-SNAPSHOT")
```

### Testing

Run `sbt scripted` for [sbt script tests](http://www.scala-sbt.org/0.13/docs/Testing-sbt-plugins.html).

### Questions?

Feel free to ask [in sbt-runfast Gitter room](https://gitter.im/sbt-runfast/Lobby)!
