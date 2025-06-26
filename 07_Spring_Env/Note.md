# Spring and Environment Variables

This note explains how Spring handles environment variables and related concepts, including system variables, Java properties, resource bundles, and other mechanisms for managing configuration in a Spring application.

## 1. System Variables (Environment Variables)
- **Definition**: Key-value pairs defined at the operating system level, accessible to Java applications via `System.getenv()`.
- **Purpose**: Provide configuration external to the application, often used for environment-specific settings (e.g., database URLs, API keys).
- **Access in Spring**:
  - Spring’s `Environment` abstraction allows access to system variables via `environment.getProperty("variableName")`.
  - Example: Accessing `DATABASE_URL`:
    ```java
    @Autowired
    private Environment env;

    public void accessSystemVariable() {
        String dbUrl = env.getProperty("DATABASE_URL");
        System.out.println("Database URL: " + dbUrl);
    }
    ```
- **Setting System Variables**:
  - **Windows**: `set DATABASE_URL=jdbc:mysql://localhost:3306/db`
  - **Linux/macOS**: `export DATABASE_URL=jdbc:mysql://localhost:3306/db`
- **Use Case**: Sensitive data (e.g., credentials) or environment-specific settings (e.g., production vs. staging).
- **Note**: System variables are global to the system and take precedence over other property sources unless overridden.

## 2. Java Properties (System Properties)
- **Definition**: Key-value pairs set within the JVM using `System.getProperty()` and `System.setProperty()`, often passed via command-line arguments (`-Dkey=value`).
- **Purpose**: Configure application behavior at runtime, typically for JVM-specific settings.
- **Access in Spring**:
  - Available via `Environment` using `environment.getProperty("key")`.
  - Example:
    ```java
    String dbHost = env.getProperty("db.host");
    System.out.println("DB Host: " + dbHost);
    ```
- **Setting Java Properties**:
  - Command line: `java -Ddb.host=localhost -jar app.jar`
  - Programmatically: `System.setProperty("db.host", "localhost");`
- **Use Case**: Application-specific settings like logging levels or feature flags.
- **Note**: Java properties override system variables in Spring’s `Environment` unless customized.

## 3. Resource Bundle
- **Definition**: A set of `.properties` files (e.g., `messages.properties`) used for internationalization (i18n) and localized configuration.
- **Purpose**: Manage localized strings or configuration data, not typically for environment variables but for application messages.
- **Access in Spring**:
  - Use `ResourceBundleMessageSource` or `@Value` with `ResourceBundle`.
  - Example:
    ```java
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Autowired
    private MessageSource messageSource;

    public void printMessage() {
        String message = messageSource.getMessage("welcome.message", null, Locale.US);
        System.out.println(message);
    }
    ```
  - `messages.properties`:
    ```
    welcome.message=Welcome to the app!
    ```
- **Use Case**: Multilingual applications or static configuration for messages.
- **Note**: Less common for environment variables; better suited for i18n.

## 4. Spring’s Environment Abstraction
- **Definition**: Spring’s `Environment` interface centralizes access to configuration properties from multiple sources (system variables, Java properties, property files, etc.).
- **Purpose**: Provides a unified way to access and manage configuration.
- **Property Sources** (in order of precedence, highest to lowest):
  - Command-line arguments (`--key=value`).
  - Java system properties (`-Dkey=value`).
  - System environment variables (`System.getenv()`).
  - `application.properties` or `application.yml` files.
  - Default properties (set via `DefaultPropertiesPropertySource`).
- **Access**:
  - Inject `Environment`:
    ```java
    @Autowired
    private Environment env;

    public String getDbUrl() {
        return env.getProperty("db.url", "default-url");
    }
    ```
  - Use `@Value`:
    ```java
    @Value("${db.url:default-url}")
    private String dbUrl;
    ```
- **Use Case**: Accessing environment-specific settings in a portable way.
- **Note**: Supports profiles (e.g., `application-dev.properties`) for environment-specific configuration.

## 5. Application Properties (`application.properties`/`application.yml`)
- **Definition**: Property files (e.g., `src/main/resources/application.properties`) for application-specific configuration.
- **Purpose**: Store configuration like database URLs, ports, or feature toggles.
- **Example**:
  - `application.properties`:
    ```
    db.url=jdbc:mysql://localhost:3306/db
    server.port=8080
    ```
  - `application.yml`:
    ```yaml
    db:
      url: jdbc:mysql://localhost:3306/db
    server:
      port: 8080
    ```
- **Access**:
  - Via `@Value`:
    ```java
    @Value("${db.url}")
    private String dbUrl;
    ```
  - Via `Environment`:
    ```java
    String dbUrl = env.getProperty("db.url");
    ```
- **Use Case**: Default or environment-specific configuration for Spring Boot or Spring apps.
- **Note**: Spring Boot auto-configures these files; use profiles (e.g., `application-prod.yml`) for different environments.

## 6. Other Related Mechanisms
- **@PropertySource**:
  - Specifies additional property files to load into the `Environment`.
  - Example:
    ```java
    @Configuration
    @PropertySource("classpath:custom.properties")
    public class AppConfig {
        @Value("${custom.key}")
        private String customKey;
    }
    ```
  - `custom.properties`:
    ```
    custom.key=value
    ```
  - Use Case: Load custom configuration files separate from `application.properties`.
- **Spring Profiles**:
  - Enable environment-specific configuration (e.g., `dev`, `prod`).
  - Example:
    ```java
    @Profile("dev")
    @Bean
    public DataSource devDataSource() {
        return new EmbeddedDatabaseBuilder().build();
    }
    ```
    - Activate: `spring.profiles.active=dev` (via system property, environment variable, or `application.properties`).
  - Use Case: Switch configurations based on deployment environment.
- **Spring Cloud Config**:
  - Centralizes configuration in a remote server (e.g., Git repository).
  - Example: `bootstrap.yml`:
    ```yaml
    spring:
      cloud:
        config:
          uri: http://config-server:8888
    ```
  - Use Case: Distributed systems with centralized configuration management.
- **Command-Line Arguments**:
  - Passed to the application (e.g., `java -jar app.jar --db.url=jdbc:mysql://localhost:3306/db`).
  - Accessible via `Environment` or `@Value`.
  - Use Case: Override properties at runtime.

## Best Practices
- **Use `Environment` or `@Value`**: Prefer these for accessing configuration to maintain portability.
- **Prioritize External Configuration**: Use system variables or Java properties for sensitive or environment-specific settings.
- **Leverage Profiles**: Organize configurations by environment (e.g., `application-dev.properties`, `application-prod.properties`).
- **Secure Sensitive Data**: Store credentials in system variables or secure vaults (e.g., Spring Cloud Vault).
- **Avoid Hardcoding**: Use `application.properties` or external sources instead of hardcoded values.
- **Debugging**: Enable `logging.level.org.springframework.core.env=DEBUG` to trace property resolution.

## Example: Combining Mechanisms
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:custom.properties")
public class AppConfig {
    @Autowired
    private Environment env;

    @Value("${db.url}")
    private String dbUrl;

    public void printConfig() {
        System.out.println("System Env: " + env.getProperty("DATABASE_URL"));
        System.out.println("Java Property: " + env.getProperty("db.host"));
        System.out.println("Application Property: " + dbUrl);
        System.out.println("Custom Property: " + env.getProperty("custom.key"));
    }
}
```

- **Files**:
  - `application.properties`:
    ```
    db.url=jdbc:mysql://localhost:3306/db
    ```
  - `custom.properties`:
    ```
    custom.key=value
    ```

## Conclusion
Spring provides a robust set of tools for managing environment variables and configuration:
- **System Variables**: OS-level, accessed via `System.getenv()` or `Environment`.
- **Java Properties**: JVM-level, set via `-D` or `System.setProperty()`.
- **Resource Bundles**: For i18n and localized messages.
- **Environment Abstraction**: Unifies access to all property sources.
- **Application Properties**: Default configuration files for Spring apps.
- **Other Tools**: `@PropertySource`, profiles, Spring Cloud Config, and command-line arguments enhance flexibility.
Use these mechanisms based on your application’s needs, prioritizing external configuration for scalability and security.