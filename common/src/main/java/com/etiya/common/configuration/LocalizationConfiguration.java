package com.etiya.common.configuration;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

//Burada beanlerle uğraşacağız
@Configuration
public class LocalizationConfiguration {

    //Bundle message source tanımlmam lazımki dile göre sıınf

    @Bean
    public ResourceBundleMessageSource bundleMessageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    //Local olarak hangi dili kullancağını belirliyor
    @Bean
    public LocaleResolver localResolver(){
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver(); //Bu sınıf sen http headerdaki hangi accept language değerini seçtiysen ben buna setliyeceğim
        acceptHeaderLocaleResolver.setDefaultLocale(new Locale("tr"));
        return acceptHeaderLocaleResolver;
    }

    @Bean
    @Primary
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(bundleMessageSource());
        return bean;
    }



}
