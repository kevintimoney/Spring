To run the application use: 

"%JAVA_HOME%"\bin\java -jar AtmApp-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.4.RELEASE)

2021-02-21 19:41:03.376  INFO 5960 --- [           main] com.neueda.test.AtmAppApplication        : Starting AtmAppApplication v0.0.1-SNAPSHOT on LAPTOP-F9QSUF6N with PID 5960 (C:\Users\ktimo\Documents\workspace-spring-tool-suite-4-4.9.0.RELEASE\AtmApp\target\AtmApp-0.0.1-SNAPSHOT.jar started by ktimo in C:\Users\ktimo\Documents\workspace-spring-tool-suite-4-4.9.0.RELEASE\AtmApp\target)
2021-02-21 19:41:03.378  INFO 5960 --- [           main] com.neueda.test.AtmAppApplication        : No active profile set, falling back to default profiles: default
2021-02-21 19:41:04.186  INFO 5960 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data repositories in DEFAULT mode.
2021-02-21 19:41:04.262  INFO 5960 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 67ms. Found 2 repository interfaces.
2021-02-21 19:41:04.638  INFO 5960 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration' of type [org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration$$EnhancerBySpringCGLIB$$31a6027f] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-02-21 19:41:05.135  INFO 5960 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-02-21 19:41:05.165  INFO 5960 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-02-21 19:41:05.165  INFO 5960 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.17]
2021-02-21 19:41:05.252  INFO 5960 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-02-21 19:41:05.253  INFO 5960 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1827 ms
2021-02-21 19:41:05.684  INFO 5960 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-02-21 19:41:05.872  INFO 5960 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-02-21 19:41:05.928  INFO 5960 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [
        name: default
        ...]
2021-02-21 19:41:06.031  INFO 5960 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate Core {5.3.9.Final}
2021-02-21 19:41:06.033  INFO 5960 --- [           main] org.hibernate.cfg.Environment            : HHH000206: hibernate.properties not found
2021-02-21 19:41:06.222  INFO 5960 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
2021-02-21 19:41:06.549  INFO 5960 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2021-02-21 19:41:07.345  INFO 5960 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2021-02-21 19:41:07.810  INFO 5960 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-02-21 19:41:07.856  WARN 5960 --- [           main] aWebConfiguration$JpaWebMvcConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2021-02-21 19:41:08.109  INFO 5960 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 3 endpoint(s) beneath base path '/actuator'
2021-02-21 19:41:08.180  INFO 5960 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-02-21 19:41:08.182  INFO 5960 --- [           main] com.neueda.test.AtmAppApplication        : Started AtmAppApplication in 5.213 seconds (JVM running for 5.678)
2021-02-21 19:41:28.007  INFO 5960 --- [nio-8080-exec-3] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2021-02-21 19:41:28.008  INFO 5960 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2021-02-21 19:41:28.018  INFO 5960 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Completed initialization in 10 ms



To check your balance use a get request : http://localhost:8080/atm/balance/123456789/1234

To withdraw funds use a put request : http://localhost:8080/atm/withdraw/123456789/1234/5