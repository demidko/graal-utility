FROM gradle:jdk20 as builder
WORKDIR /project
COPY src ./src
COPY build.gradle.kts ./build.gradle.kts
RUN gradle clean installDist

FROM eclipse-temurin as backend
WORKDIR /root
COPY --from=builder /project/build/install/project/ ./app/
ENTRYPOINT ["/root/app/bin/app"]