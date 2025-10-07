plugins {
    java
    id("org.springframework.boot") version "3.5.5" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("com.google.cloud.tools.jib") version "3.4.0"
}

allprojects {
    group = "click.dailyfeed"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.google.cloud.tools.jib")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // 라이브러리 프로젝트는 bootJar 비활성화
    if (name != "dailyfeed-member") {
        tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
            enabled = false
        }
        tasks.named<Jar>("jar") {
            enabled = true
        }
    }
}

project(":dailyfeed-member") {
    dependencies {
        implementation(project(":dailyfeed-kafka-support"))
    }

    tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = true
        archiveFileName.set("${project.name}.jar")
    }

    tasks.named<Jar>("jar") {
        enabled = false
    }

    jib {
        // Base 이미지 설정 (Java 17 기반)
        from {
            // Google의 distroless 이미지 사용 (인증 불필요)
            image = "gcr.io/distroless/java17-debian12"
        }

        // 타겟 이미지 설정
        to {
            tags = setOf("0.0.2", "latest")
            image = "alpha300uk/dailyfeed-member-svc"
        }

        // 컨테이너 설정
        container {
            // JVM 옵션
            jvmFlags = listOf(
                "-XX:+UseContainerSupport",
                "-XX:+UseG1GC",
                "-Xms512m",
                "-Xmx1024m",
                "-Dfile.encoding=UTF-8"
            )

            // 포트 설정
            ports = listOf("8080")

            // 레이블
            labels = mapOf(
                "maintainer" to "alpha300uk@gmail.com",
                "version" to project.version.toString(),
                "description" to "DailyFeed Member Service"
            )

            // 작업 디렉토리
            workingDirectory = "/app"

            // 사용자 설정 (보안을 위해 non-root 사용자 사용)
            user = "nonroot:nonroot"

            // 생성 시간 설정 (재현 가능한 빌드를 위해)
            creationTime.set("USE_CURRENT_TIMESTAMP")

            // 환경 변수 (기본값)
            environment = mapOf(
                "JAVA_TOOL_OPTIONS" to "-Djava.security.egd=file:/dev/./urandom"
            )
        }
    }
}