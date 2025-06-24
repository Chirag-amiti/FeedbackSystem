// package com.example.feedbackSystem.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.*;

// @Configuration
// public class CorsConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")
//                 .allowedOriginPatterns("http://localhost:3333")
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                 .allowedHeaders("*")
//                 .allowCredentials(true)
//                 .exposedHeaders("Authorization"); // optional: if you want to read headers on frontend
//     }
// }

// package com.example.feedbackSystem.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.*;

// @Configuration
// public class CorsConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         System.out.println("CorsConfig loaded");
//         registry.addMapping("/**")
//                 .allowedOriginPatterns("http://localhost:3333") // use allowedOriginPatterns (not allowedOrigins)
//                 .allowedMethods("*")
//                 .allowedHeaders("*")
//                 .allowCredentials(true)
//                 .exposedHeaders("Authorization"); // optional
//     }
// }
