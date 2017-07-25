[![Download](https://api.bintray.com/packages/tues/sbt-plugins/sbt-runfast/images/download.svg)](https://bintray.com/tues/sbt-plugins/sbt-runfast/_latestVersion)
[![Build Status](https://travis-ci.org/tues/sbt-runfast.svg?branch=master)](https://travis-ci.org/tues/sbt-runfast)

# sbt-runfast

Generates a shell script which lets you easily run your main class without SBT (much faster than `sbt run`!).

## Usage

Currently, there are no JARs available, so you need to build the plugin yourself:

``` Shell
sbt publishLocal
```

When you have the JAR, you can add the following line to the `project/plugins.sbt` inside your project, but I recommend adding it to your global `~/.sbt/0.13/plugins/plugins.sbt`:

``` Scala
addSbtPlugin("pl.tues" % "sbt-runfast" % "0.1-SNAPSHOT")
```

Next, run:

``` Shell
sbt runfastGenerate
```

inside your project. It will generate a script called `runfast.sh` in your `target/` directory. Running your program now is straightforward:

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

## Testing

Run `scripted` for [sbt script tests](http://www.scala-sbt.org/0.13/docs/Testing-sbt-plugins.html).
