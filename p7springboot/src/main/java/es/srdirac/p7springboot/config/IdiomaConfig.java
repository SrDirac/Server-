package es.srdirac.p7springboot.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// Aqui se configura el idioma de la web
@Configuration
public class IdiomaConfig implements WebMvcConfigurer {

    // Guarda el idioma elegido en la sesión, asi aunque cambies de pagina
    // se queda el mismo. Si todavia no se ha elegido ninguno sale en español
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.of("es"));
        return resolver;
    }

    // Lee ?lang=es o ?lang=en de la URL y cambia el idioma
    // (los enlaces ES y EN de la cabecera son los que añaden ese parametro)
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    // Activa el interceptor en todas las peticiones
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
