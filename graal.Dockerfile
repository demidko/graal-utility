FROM ghcr.io/graalvm/native-image-community as builder
WORKDIR /project
COPY gradle ./gradle
COPY gradlew ./gradlew
COPY src ./src
COPY build.gradle.kts ./build.gradle.kts
COPY settings.gradle.kts ./settings.gradle.kts
RUN microdnf install findutils
RUN ./gradlew clean nativeCompile

FROM debian as utility
WORKDIR /root
COPY --from=builder /project/build/native/nativeCompile/project ./app
ENTRYPOINT ["/root/app"]