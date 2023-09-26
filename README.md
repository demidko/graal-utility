# graal-utility

The cross-platform native Kotlin command line tool template.

## Building jar file

You will need Java 20 or higher. To build the utility, run the command:

```shell
./gradlew clean installDist
```

You can also build the utility into native executable file or Docker image.

## Building native executable file

You will need GraalVM 20 and `native-image` pre-installed:

```shell
./gradlew clean nativeCompile
```

The executable file will be located at `./build/native/nativeCompile/`

## Building Docker image with jvm

```shell
docker build . -t jvm-app-image
```

## Building Docker image with native code

```shell
docker build --platform linux/amd64 . -f graal.Dockerfile -t native-app-image
```
