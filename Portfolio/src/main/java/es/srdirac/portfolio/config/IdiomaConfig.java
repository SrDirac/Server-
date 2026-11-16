package es.srdirac.portfolio.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class IdiomaConfig implements WebMvcConfigurer {

    // Guarda el idioma elegido en una cookie del navegador y no en la sesión del servidor:
    // en Vercel el servidor se apaga tras unos minutos sin visitas y la sesión se perdería.
    // Spring busca este bean por el nombre "localeResolver".
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver("idioma");
        resolver.setDefaultLocale(Locale.forLanguageTag("es"));
        return resolver;
    }

    // Cambia el idioma cuando la URL lleva el parámetro ?lang=es o ?lang=en
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    // Registra el interceptor para que se aplique en todas las peticiones
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}