# Spring and Environment Variables

This note explains how Spring handles environment variables and related concepts, including system variables, Java properties, resource bundles, and other mechanisms for managing configuration in a Spring application. It also covers how to access these configurations within Spring beans.

## 1. System Variables (Environment Variables)
- **Definition**: Key-value pairs defined at the operating system level, accessible to Java applications via `System.getenv()`.
- **Purpose**: Provide configuration external to the application, often used for environment-specific settings (e.g., database URLs, API keys).
- **Setting System Variables**:
  - **Windows**: `set DATABASE_URL=jdbc:mysql://localhost:3306/db`
  - **Linux/macOS**: `export DATABASE_URL=jdbc:mysql://localhost:3306/db`
- **Use Case**: Sensitive data (e.g., credentials) or environment-specific settings (e.g., production vs. staging).
- **Note**: System variables are global to the system and take precedence over other property sources unless overridden.

## 2. Java Properties (System Properties)
- **Definition**: Key-value pairs set within the JVM using `System.getProperty()` and `System.setProperty()`, often passed via command-line arguments (`-Dkey=value`).
- **Purpose**: Configure application behavior at runtime, typically for JVM-specific settings.
- **Setting Java Properties**:
  - Command line: `java -Ddb.host=localhost -jar app.jar`
  - Programmatically: `System.setProperty("db.host", "localhost");`
- **Use Case**: Application-specific settings like logging levels or feature flags.
- **Note**: Java properties override system variables in Spring’s `Environment` unless customized.

## 3. Resource Bundle
- **Definition**: A set of `.properties` files (e.g., `messages.properties`) used for internationalization (i18n) and localized configuration.
- **Purpose**: Manage localized strings or configuration data, not typically for environment variables but for application messages.
- **Configuration**:
  - Define a `ResourceBundleMessageSource` bean:
    ```java
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }
    ```
  - Example `messages.properties`:
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
  - Use Case: Override properties at runtime.

## 7. Accessing Configurations in Spring Beans
Spring beans can access system variables, Java properties, resource bundles, and other configurations using several methods. Below are the primary approaches:

- **Using `@Value`**:
  - Injects properties directly into bean fields or method parameters using `${property.key}` syntax.
  - Supports system variables, Java properties, and `application.properties`/`application.yml`.
  - Example:
    ```java
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    @Component
    public class ConfigBean {
        @Value("${db.url:jdbc:mysql://localhost:3306/default}")
        private String dbUrl;

        @Value("${server.port:8080}")
        private int serverPort;

        public void printConfig() {
            System.out.println("Database URL: " + dbUrl);
            System.out.println("Server Port: " + serverPort);
        }
    }
    ```
  - **Notes**:
    - Use `:` to provide default values (e.g., `default-url` if `db.url` is missing).
    - Works with `application.properties`, system variables, or Java properties.
    - Requires `spring-context` dependency.

- **Using `Environment`**:
  - Injects the `Environment` interface to access properties programmatically.
  - Example:
    ```java
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.env.Environment;
    import org.springframework.stereotype.Component;

    @Component
    public class ConfigBean {
        @Autowired
        private Environment env;

        public void printConfig() {
            String dbUrl = env.getProperty("db.url", "jdbc:mysql://localhost:3306/default");
            String dbHost = env.getProperty("db.host");
            System.out.println("Database URL: " + dbUrl);
            System.out.println("DB Host: " + dbHost);
        }
    }
    ```
  - **Notes**:
    - Accesses system variables (`DATABASE_URL`), Java properties (`db.host`), and `application.properties`.
    - Use `getProperty(key, defaultValue)` for fallback values.
    - Useful for dynamic or conditional property access.

- **Using `MessageSource` for Resource Bundles**:
  - Access localized messages from resource bundles (e.g., `messages.properties`).
  - Example:
    ```java
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.MessageSource;
    import org.springframework.stereotype.Component;
    import java.util.Locale;

    @Component
    public class MessageBean {
        @Autowired
        private MessageSource messageSource;

        public void printMessage() {
            String message = messageSource.getMessage("welcome.message", null, Locale.US);
            System.out.println("Message: " + message);
        }
    }
    ```
  - `messages.properties`:
    ```
    welcome.message=Welcome to the app!
    ```
  - **Notes**:
    - Requires a `MessageSource` bean (e.g., `ResourceBundleMessageSource`).
    - Ideal for i18n; not typically used for environment variables.

- **Using `@ConfigurationProperties`**:
  - Maps properties with a common prefix to a Java object for structured access.
  - Example:
    ```java
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;

    @Component
    @ConfigurationProperties(prefix = "db")
    public class DbConfig {
        private String url;
        private String host;

        // Getters and setters
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }

        public void printConfig() {
            System.out.println("DB URL: " + url);
            System.out.println("DB Host: " + host);
        }
    }
    ```
  - `application.properties`:
    ```
    db.url=jdbc:mysql://localhost:3306/db
    db.host=localhost
    ```
  - **Notes**:
    - Requires `spring-boot` dependency for auto-binding.
    - Ideal for grouping related properties.
    - Can be used with system variables or Java properties by matching the prefix.

- **Accessing Command-Line Arguments**:
  - Command-line arguments (e.g., `--db.url=value`) are treated as properties.
  - Example:
    ```java
    @Component
    public class ConfigBean {
        @Value("${db.url}")
        private String dbUrl;

        public void printConfig() {
            System.out.println("Command-line DB URL: " + dbUrl);
        }
    }
    ```
  - Run: `java -jar app.jar --db.url=jdbc:mysql://localhost:3306/db`

## Best Practices
- **Use `@Value` for Simple Properties**: Ideal for single properties like `db.url` or `server.port`.
- **Use `Environment` for Flexibility**: Programmatic access for dynamic or conditional logic.
- **Use `@ConfigurationProperties` for Structured Config**: Group related properties into a single object.
- **Use `MessageSource` for i18n**: Access resource bundles for localized messages.
- **Prioritize External Configuration**: Use system variables or Java properties for sensitive or environment-specific settings.
- **Leverage Profiles**: Organize configurations by environment (e.g., `application-dev.properties`).
- **Secure Sensitive Data**: Store credentials in system variables or secure vaults (e.g., Spring Cloud Vault).
- **Avoid Hardcoding**: Use `application.properties` or external sources instead of hardcoded values.
- **Debugging**: Enable `logging.level.org.springframework.core.env=DEBUG` to trace property resolution.

## Example: Combining Mechanisms in a Bean
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
@ConfigurationProperties(prefix = "app")
public class ConfigBean {
    private String apiKey; // From ConfigurationProperties

    @Value("${db.url:jdbc:mysql://localhost:3306/default}")
    private String dbUrl;

    @Autowired
    private Environment env;

    @Autowired
    private MessageSource messageSource;

    // Getters and setters for apiKey
    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public void printConfig() {
        System.out.println("System Env (DATABASE_URL): " + env.getProperty("DATABASE_URL"));
        System.out.println("Java Property (db.host): " + env.getProperty("db.host"));
        System.out.println("Application Property (db.url): " + dbUrl);
        System.out.println("Configuration Property (app.apiKey): " + apiKey);
        System.out.println("Resource Bundle Message: " + 
            messageSource.getMessage("welcome.message", null, Locale.US));
    }
}
```

- **Files**:
  - `application.properties`:
    ```
    db.url=jdbc:mysql://localhost:3306/db
    app.apiKey=secret-key
    ```
  - `messages.properties`:
    ```
    welcome.message=Welcome to the app!
    ```

## Conclusion
Spring provides a robust set of tools for managing environment variables and configuration:
- **System Variables**: OS-level, accessed via `System.getenv()` or `Environment`.
- **Java Properties**: JVM-level, set via `-D` or `System.setProperty()`.
- **Resource Bundles**: For i18n and localized messages via `MessageSource`.
- **Environment Abstraction**: Unifies access to all property sources.
- **Application Properties**: Default configuration files for Spring apps.
- **Other Tools**: `@PropertySource`, profiles, Spring Cloud Config, and command-line arguments enhance flexibility.
- **Access in Beans**: Use `@Value` for single properties, `Environment` for programmatic access, `@ConfigurationProperties` for structured config, and `MessageSource` for resource bundles.
Use these mechanisms based on your application’s needs, prioritizing external configuration for scalability and security.