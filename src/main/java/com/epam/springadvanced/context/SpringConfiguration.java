package com.epam.springadvanced.context;

import com.epam.springadvanced.domain.enums.Rating;
import com.epam.springadvanced.utils.converter.StringToEnumConverterFactory;
import com.epam.springadvanced.utils.editor.CustomPropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Import({AspectConfiguration.class,
        DataConfiguration.class,
        AuditoriumConfiguration.class
})
@PropertySource("classpath:data/auditorium1.properties")
@PropertySource("classpath:data/auditorium2.properties")
@PropertySource("classpath:data/auditorium3.properties")
@ComponentScan({"com.epam.springadvanced.service", "com.epam.springadvanced.utils"})
public class SpringConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setPropertyEditorRegistrars(
                new PropertyEditorRegistrar[]{customPropertyEditorRegistrar()});
        return customEditorConfigurer;
    }

    @Bean
    public PropertyEditorRegistrar customPropertyEditorRegistrar() {
        return new CustomPropertyEditorRegistrar();
    }

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean conversionServiceFactory = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        // add custom converters here
        converters.add(stringToEnumConverterFactory().getConverter(Rating.class));
        conversionServiceFactory.setConverters(converters);
        return conversionServiceFactory.getObject();
    }

    @Bean
    public FormattingConversionService formattingConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyyMMdd"));
        registrar.registerFormatters(conversionService);
        return conversionService;
    }

    @Bean
    public StringToEnumConverterFactory stringToEnumConverterFactory() {
        return new StringToEnumConverterFactory();
    }

}
