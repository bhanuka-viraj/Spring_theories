# Spring Properties Files and Resource Bundles

This note explains how Spring handles **properties files** and **resource bundles**, including their definitions, purposes, configuration, and how to access them in Spring beans. It focuses on their use within a Spring application, typically stored in the `src/main/resources` folder.

## 1. Properties Files
- **Definition**: Text files with a `.properties` extension (e.g., `application.properties`, `custom.properties`) containing key-value pairs for application configuration.
- **Purpose**: Store configuration settings such as database URLs, server ports, API keys, or other application-specific parameters.
- **Location**: Placed in `src/main/resources` (or subdirectories) to be accessible on the classpath.
- **Format**:
  - Key-value pairs separated by `=` or `:`, e.g.:
    ```
    db.url=jdbc:mysql://localhost:3306/db
    server.port=8080
    app.apiKey=secret-key
    ```
- **Types**:
  - **application.properties**: Default configuration file for Spring Boot applications, automatically loaded by Spring Boot.
  - **Custom Properties Files**: Additional files (e.g., `custom.properties`) loaded explicitly via `@PropertySource` or other mechanisms.
- **Purpose in Spring**:
  - Provide environment-specific or application-wide settings.
  - Support externalized configuration to avoid hardcoding values.
  - Enable profile-specific configurations (e.g., `application-dev.properties`, `application-prod.properties`).
- **Use Case**: Configuring database connections, server settings, or feature toggles.
- **Note**: Properties files are simple, human-readable, and widely used in Spring for configuration.

### Accessing Properties Files in Spring Beans
Properties from `application.properties` or custom `.properties` files can be accessed in Spring beans using several methods:

- **Using `@Value`**:
  - Injects a property value directly into a bean field or method parameter using `${property.key}` syntax.
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
    - Use `:` to specify default values (e.g., `default` if `db.url` is missing).
    - Works with `application.properties` or custom properties files loaded via `@PropertySource`.

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
            String apiKey = env.getProperty("app.apiKey");
            System.out.println("Database URL: " + dbUrl);
            System.out.println("API Key: " + apiKey);
        }
    }
    ```
  - **Notes**:
    - Accesses properties from `application.properties` or custom files.
    - Provides fallback values via `getProperty(key, defaultValue)`.

- **Using `@ConfigurationProperties`**:
  - Maps a group of properties with a common prefix to a Java object for structured access.
  - Example:
    ```java
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;

    @Component
    @ConfigurationProperties(prefix = "app")
    public class AppConfig {
        private String apiKey;
        private String endpoint;

        // Getters and setters
        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public String getEndpoint() { return endpoint; }
        public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

        public void printConfig() {
            System.out.println("API Key: " + apiKey);
            System.out.println("Endpoint: " + endpoint);
        }
    }
    ```
  - `application.properties`:
    ```
    app.apiKey=secret-key
    app.endpoint=https://api.example.com
    ```
  - **Notes**:
    - Requires `spring-boot` dependency for auto-binding.
    - Ideal for grouping related properties.

- **Loading Custom Properties Files**:
  - Use `@PropertySource` to load additional `.properties` files.
  - Example:
    ```java
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.PropertySource;
    import org.springframework.beans.factory.annotation.Value;

    @Configuration
    @PropertySource("classpath:custom.properties")
    public class AppConfig {
        @Value("${custom.key}")
        private String customKey;

        public void printCustomConfig() {
            System.out.println("Custom Key: " + customKey);
        }
    }
    ```
  - `custom.properties` (in `src/main/resources`):
    ```
    custom.key=value
    ```

## 2. Resource Bundles
- **Definition**: A set of `.properties` files (e.g., `messages.properties`, `messages_en_US.properties`) used primarily for internationalization (i18n) and localization, containing key-value pairs for messages or static configuration.
- **Purpose**: Provide locale-specific strings (e.g., translations) or static configuration data for an application.
- **Location**: Stored in `src/main/resources`, with a base name (e.g., `messages`) and optional locale suffixes (e.g., `messages_fr.properties` for French).
- **Format**:
  - Key-value pairs, similar to properties files, but designed for i18n.
  - Example:
    - `messages.properties` (default):
      ```
      welcome.message=Welcome to the app!
      error.message=An error occurred
      ```
    - `messages_fr.properties` (French):
      ```
      welcome.message=Bienvenue dans l'application !
      error.message=Une erreur s'est produite
      ```
- **Purpose in Spring**:
  - Manage localized messages or static configuration for multilingual applications.
  - Unlike general properties files, resource bundles are optimized for i18n, supporting locale-specific variants.
- **Configuration**:
  - Define a `ResourceBundleMessageSource` bean to manage resource bundles.
  - Example:
    ```java
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.support.ResourceBundleMessageSource;

    @Configuration
    public class AppConfig {
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setBasename("messages"); // Looks for messages.properties, messages_fr.properties, etc.
            source.setDefaultEncoding("UTF-8");
            return source;
        }
    }
    ```
- **Use Case**: Multilingual applications (e.g., displaying "Welcome" in English, French, etc.) or static message configuration.
- **Note**: Resource bundles are typically used for i18n, not for environment variables or dynamic configuration like `application.properties`.

### Accessing Resource Bundles in Spring Beans
Resource bundles are accessed in Spring beans primarily through the `MessageSource` interface, which resolves messages based on keys and locales.

- **Using `MessageSource`**:
  - Inject the `MessageSource` bean to retrieve localized messages.
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

        public void printMessages() {
            String welcome = messageSource.getMessage("welcome.message", null, Locale.US);
            String welcomeFr = messageSource.getMessage("welcome.message", null, Locale.FRENCH);
            System.out.println("English: " + welcome);
            System.out.println("French: " + welcomeFr);
        }
    }
    ```
  - **Output** (based on `messages.properties` and `messages_fr.properties`):
    ```
    English: Welcome to the app!
    French: Bienvenue dans l'application !
    ```
  - **Notes**:
    - Requires a `MessageSource` bean configured with the base name (e.g., `messages`).
    - Supports parameterized messages:
      ```java
      String message = messageSource.getMessage("greeting.message", new Object[]{"User"}, Locale.US);
      ```
      - `messages.properties`:
        ```
        greeting.message=Hello, {0}!
        ```

- **Using `@Value` with ResourceBundle (Less Common)**:
  - Access resource bundle messages directly via `@Value` with a `ResourceBundle`.
  - Example:
    ```java
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;
    import java.util.ResourceBundle;

    @Component
    public class MessageBean {
        @Value("#{T(java.util.ResourceBundle).getBundle('messages').getString('welcome.message')}")
        private String welcomeMessage;

        public void printMessage() {
            System.out.println("Welcome: " + welcomeMessage);
        }
    }
    ```
  - **Notes**:
    - Requires SpEL (Spring Expression Language) to access the `ResourceBundle`.
    - Less common than `MessageSource`; primarily used for simple, non-localized access.

## Best Practices
- **Properties Files**:
  - Use `application.properties` for default Spring Boot configuration.
  - Use custom `.properties` files with `@PropertySource` for additional settings.
  - Prefer `@ConfigurationProperties` for grouped properties to improve type safety and readability.
  - Use profiles (e.g., `application-dev.properties`) for environment-specific settings.
  - Avoid hardcoding values; externalize configuration to properties files.
- **Resource Bundles**:
  - Use for internationalization (i18n) and localized messages.
  - Configure `ResourceBundleMessageSource` with a base name (e.g., `messages`).
  - Support multiple locales with files like `messages_en.properties`, `messages_fr.properties`.
  - Use `MessageSource` for dynamic message resolution in beans.
- **Access in Beans**:
  - Use `@Value` for simple property injection.
  - Use `Environment` for programmatic property access.
  - Use `@ConfigurationProperties` for structured configuration.
  - Use `MessageSource` for resource bundle messages.
- **File Management**:
  - Store `application.properties`, custom `.properties`, and resource bundles (e.g., `messages.properties`) in `src/main/resources`.
  - Ensure proper encoding (e.g., UTF-8) for resource bundles to support special characters.
- **Debugging**:
  - Enable `logging.level.org.springframework.core.env=DEBUG` to trace property resolution.
  - Enable `logging.level.org.springframework.context=DEBUG` to debug `MessageSource` behavior.

## Example: Combining Properties Files and Resource Bundles
- **Files in `src/main/resources`**:
  - `application.properties`:
    ```
    db.url=jdbc:mysql://localhost:3306/db
    app.apiKey=secret-key
    ```
  - `custom.properties`:
    ```
    custom.key=value
    ```
  - `messages.properties`:
    ```
    welcome.message=Welcome to the app!
    ```
  - `messages_fr.properties`:
    ```
    welcome.message=Bienvenue dans l'application !
    ```

- **Bean Example**:
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
      private String apiKey;

      @Value("${db.url:jdbc:mysql://localhost:3306/default}")
      private String dbUrl;

      @Value("${custom.key}")
      private String customKey;

      @Autowired
      private Environment env;

      @Autowired
      private MessageSource messageSource;

      // Getters and setters for apiKey
      public String getApiKey() { return apiKey; }
      public void setApiKey(String apiKey) { this.apiKey = apiKey; }

      public void printConfig() {
          System.out.println("Application Property (db.url): " + dbUrl);
          System.out.println("Custom Property (custom.key): " + customKey);
          System.out.println("Configuration Property (app.apiKey): " + apiKey);
          System.out.println("Environment Property (db.url): " + env.getProperty("db.url"));
          System.out.println("Resource Bundle (welcome.message): " + 
              messageSource.getMessage("welcome.message", null, Locale.US));
      }
  }
  ```

- **Configuration**:
  ```java
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.PropertySource;
  import org.springframework.context.support.ResourceBundleMessageSource;

  @Configuration
  @PropertySource("classpath:custom.properties")
  public class AppConfig {
      @Bean
      public MessageSource messageSource() {
          ResourceBundleMessageSource source = new ResourceBundleMessageSource();
          source.setBasename("messages");
          source.setDefaultEncoding("UTF-8");
          return source;
      }
  }
  ```

- **Output**:
  ```
  Application Property (db.url): jdbc:mysql://localhost:3306/db
  Custom Property (custom.key): value
  Configuration Property (app.apiKey): secret-key
  Environment Property (db.url): jdbc:mysql://localhost:3306/db
  Resource Bundle (welcome.message): Welcome to the app!
  ```

## Conclusion
- **Properties Files**: Use `application.properties` for default configuration and custom `.properties` files for additional settings. Access via `@Value`, `Environment`, or `@ConfigurationProperties` in beans.
- **Resource Bundles**: Use for internationalization with `messages.properties` and locale-specific variants. Access via `MessageSource` for localized messages or `@Value` with SpEL for static access.
- **Placement**: Store both in `src/main/resources` for classpath access.
- **Best Approach**: Use `@ConfigurationProperties` for structured properties, `MessageSource` for i18n, and profiles for environment-specific configurations to ensure flexibility and maintainability in Spring applications.