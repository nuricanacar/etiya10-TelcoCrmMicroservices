package com.etiya.common.localization;

import jakarta.validation.MessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class LocalizedMessageInterpolator implements MessageInterpolator {

    private final MessageSource messageSource;

    public LocalizedMessageInterpolator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Spring'in LocalizationService'i enjekte etmesini sağlıyoruz.


    // Bu metot, bir validasyon hatası olduğunda Hibernate Validator tarafından çağrılır.
    @Override
    public String interpolate(String key, Context context) {
        // messageTemplate -> @NotBlank(message = "bu kısımdaki anahtar")
        // Bizim durumumuzda, "streetRequired" gibi bir key gelecek.
        // Gelen anahtarı doğrudan kendi servisimize paslıyoruz.
        //return localizationService.getMessage(messageTemplate);
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());

    }

    // Bu metot genellikle kullanılmaz ama interface gereği implemente edilmeli.
    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        // İstersek locale'i de kullanarak mesajı alabiliriz ama bizim servisimiz
        // zaten LocaleContextHolder'dan aldığı için direkt ana metodu çağırabiliriz.
        return interpolate(messageTemplate, context);
    }
}