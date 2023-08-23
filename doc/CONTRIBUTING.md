## Programming Style

For programming in Java we use [Google style](https://google.github.io/styleguide/javaguide.html).

## Repository Structure

We focus on the "**Basic Folder Structure**" and for the most text files (such as documentation, README.file, etc.) we are going to use markdown file (.md).
[Learn more](https://medium.com/code-factory-berlin/github-repository-structure-best-practices-248e6effc405). 

## Naming Convention (branches and commits)

Suggested branches naming convetion: `task_number-task_name`.

Commit message naming convention: `task_number if implemented this commit will`.

To get task numbers, use: [Trello Card Numbers Plus](https://chrome.google.com/webstore/detail/trello-card-numbers-plus/ncibjlmfhjcjnphnpphgphbflpdpliei/related)

## API documentation

Swagger 3 (OpenAPI 3) is used for API documentation.

### Writing an API documentation
To create API documentation with OpenAPI you need Spring Annotations.
In your controller classes, use Spring annotations to define API endpoints and provide additional metadata for the OpenAPI documentation. Some commonly used annotations include:
- `@RestController: Indicate that the class is a controller for handling REST requests.`
- `@RequestMapping: Define the base path for the controller's endpoints.`
- `@GetMapping, @PostMapping, etc.: Define specific HTTP methods and paths for your endpoints.`
- `@Operation: Provide descriptions for your API operations.`
- `@Parameter: Describe parameters for your API methods.`
- `@ApiResponse: Define responses for different scenarios.`
- `@ApiResponses: Group multiple @ApiResponse annotations.`

Example:
```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user/{id}")
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Implementation
    }
}
```
Or an example from our project:

```java
@RestController
public class RegistrationService {
    private String invalidEmailMessage = "Invalid email address";
    private String invalidPasswordMessage = "Invalid password";
    private String alreadyRegisteredMessage = "This user already exists";
    private String validPasswordPattern = ".{8,}";
    private String validEmailPattern = "^[\\w+-]+(\\.[\\w+-]+)*[\\.]?[a-zA-Z0-9]@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    Logger log = LoggerFactory.getLogger(RegistrationService.class);

    /**
     * Handles /register requests by checking validity of provided data, registering
     * new users in the database and sending confirmation emails.
     * 
     * @param user User object defined in a request body
     * @return JSON response with registration token
     * @throws Exception
     */
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception {...}
```

To customize API documentation use `@Bean` and `@Configuration` annotations.
Example of creating a group and selecting the only endpoint we want to be documentated: 
```java
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/hello", "/echo")  // Specify your endpoint paths here
                .build();
    }
}
```
And an example of creating a group and specyfing java packages.
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springdoc.core.GroupedOpenApi;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public GroupedOpenApi customOpenApi() {
        return GroupedOpenApi.builder()
            .group("custom-api") // Define a group name for your APIs
            .packagesToScan("com.example.controller") // Package(s) containing your API controllers
            .build();
    }
}
```

### Reading an API documentation
To access API documentation turn the services online and go to the following path: `http://localhost:8080/swagger-ui.html`.

OpenAPI provides a feature which allows user to send POST, GET, DELETE and PUT methods and receive a response.
To access this feature, simply find desired endpoint in the documentation and click on it.
