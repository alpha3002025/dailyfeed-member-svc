




# Troubleshoot
## org/springframework/security/core/userdetails/UserDetailsService.class] cannot be opened because it does not exist
application 을 local profile 로 Local WAS 로 구동시에 다음과 같은 에러가 날 수 있습니다.
```plain
org/springframework/security/core/userdetails/UserDetailsService.class] cannot be opened because it does not exist
```
<br/>

전체 로그는 다음과 같을 수 있습니다.
```plain
org.springframework.beans.factory.BeanDefinitionStoreException: I/O failure while processing configuration class [click.dailyfeed.member.config.security.userdetails.CustomUserDetailsServiceImpl]
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:286) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:218) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass(ConfigurationClassParser.java:354) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:281) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:204) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:172) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions(ConfigurationClassPostProcessor.java:418) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.postProcessBeanDefinitionRegistry(ConfigurationClassPostProcessor.java:290) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:349) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:118) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:791) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:609) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:318) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350) ~[spring-boot-3.5.5.jar:3.5.5]
	at click.dailyfeed.member.MemberApplication.main(MemberApplication.java:10) ~[main/:na]
Caused by: java.io.FileNotFoundException: class path resource [org/springframework/security/core/userdetails/UserDetailsService.class] cannot be opened because it does not exist
	at org.springframework.core.io.ClassPathResource.getInputStream(ClassPathResource.java:215) ~[spring-core-6.2.10.jar:6.2.10]
	at org.springframework.core.type.classreading.SimpleMetadataReader.getClassReader(SimpleMetadataReader.java:54) ~[spring-core-6.2.10.jar:6.2.10]
	at org.springframework.core.type.classreading.SimpleMetadataReader.<init>(SimpleMetadataReader.java:48) ~[spring-core-6.2.10.jar:6.2.10]
	at org.springframework.core.type.classreading.SimpleMetadataReaderFactory.getMetadataReader(SimpleMetadataReaderFactory.java:103) ~[spring-core-6.2.10.jar:6.2.10]
	at org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory.createMetadataReader(ConcurrentReferenceCachingMetadataReaderFactory.java:98) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory.getMetadataReader(ConcurrentReferenceCachingMetadataReaderFactory.java:85) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.core.type.classreading.SimpleMetadataReaderFactory.getMetadataReader(SimpleMetadataReaderFactory.java:81) ~[spring-core-6.2.10.jar:6.2.10]
	at org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory.getMetadataReader(ConcurrentReferenceCachingMetadataReaderFactory.java:75) ~[spring-boot-3.5.5.jar:3.5.5]
	at org.springframework.context.annotation.ConfigurationClassParser.asSourceClass(ConfigurationClassParser.java:711) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser$SourceClass.getInterfaces(ConfigurationClassParser.java:1060) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.processInterfaces(ConfigurationClassParser.java:441) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.processInterfaces(ConfigurationClassParser.java:449) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass(ConfigurationClassParser.java:385) ~[spring-context-6.2.10.jar:6.2.10]
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:281) ~[spring-context-6.2.10.jar:6.2.10]
	... 18 common frames omitted
```
<br/>


### Solution
이 경우 다음과 같이 의존성을 새로고침 해주시기 바랍니다.
```bash
./gradlew clean build --refresh-dependencies
```

