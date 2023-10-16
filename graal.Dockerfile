FROM gradle:jdk21-graal as builder
WORKDIR /project
COPY src ./src
COPY build.gradle.kts ./build.gradle.kts
COPY settings.gradle.kts ./settings.gradle.kts
RUN gradle clean nativeCompile

FROM debian as utility
WORKDIR /root
COPY --from=builder /project/build/native/nativeCompile/project ./app
ENTRYPOINT ["/root/app"]