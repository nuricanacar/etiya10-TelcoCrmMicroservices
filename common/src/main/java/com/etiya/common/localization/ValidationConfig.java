package com.etiya.common.localization;

import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    // Bu metot, Spring'in kullanacağı varsayılan Validator bean'ini override eder.
    @Bean
    public Validator validator(LocalizedMessageInterpolator localizedMessageInterpolator) {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();

        // İşte sihirli satır:
        // "Mesajları çözerken benim verdiğim bu interpolator'ı kullan."
        factoryBean.setMessageInterpolator(localizedMessageInterpolator);

        return factoryBean;
    }
}