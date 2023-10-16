import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
  mavenCentral()
  maven("https://jitpack.io")
}
plugins {
  application
  kotlin("jvm") version "1.9.20-RC"
  id("org.graalvm.buildtools.native") version "0.9.27"
}
dependencies {
  implementation("com.google.iot.cbor:cbor:0.01.02")
  implementation("com.google.protobuf:protobuf-java:3.24.3")
  implementation("com.github.ajalt.mordant:mordant:2.1.0")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
  implementation("org.apache.parquet:parquet-avro:1.13.1")
  implementation("org.apache.hadoop:hadoop-client:3.3.6")
  implementation("ch.qos.logback:logback-classic:1.4.11")
  implementation("com.github.ajalt.clikt:clikt:4.2.0")
  testImplementation("com.google.truth:truth:1.1.3")
  testImplementation("io.mockk:mockk:1.13.4")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "21"
  kotlinOptions.freeCompilerArgs += listOf(
    "-Xvalue-classes",
    "-opt-in=kotlin.ExperimentalStdlibApi",
    "-opt-in=kotlin.time.ExperimentalTime"
  )
}
tasks.withType<JavaCompile> {
  sourceCompatibility = "21"
  targetCompatibility = "21"
}
tasks.test {
  useJUnitPlatform()
  jvmArgs("--enable-preview")
}

application {
  mainClass.set("app.example.AppKt")
  applicationDefaultJvmArgs += "--enable-preview"
}
tasks.installDist {
  dependsOn(tasks.test)
}

graalvmNative {
  binaries.all {
    resources.autodetect()
  }
  toolchainDetection.set(false)
}
tasks.nativeCompile {
  options.get().buildArgs("--no-fallback")
  dependsOn(tasks.test)
}