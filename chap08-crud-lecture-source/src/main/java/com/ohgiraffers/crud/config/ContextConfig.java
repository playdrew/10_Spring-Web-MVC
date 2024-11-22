package com.ohgiraffers.crud.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

// BeanFactory == IoC 컨테이너 == ApplicationContext
@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud") // 이런 식으로 스캔범위를 넓힌다.
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class) // Mapper 라는 어노테이션은 basePackages 에서 스캔
public class ContextConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        /* comment. message.properties 파일을 자바 객체 형식으로
        *           읽어들일 수 있게 만든다.
        * */
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        // classpath: -> src/main/resource, src/main/java 를 의미한다. 클래스패스가 해당하는 경로를 디폴트로 인식해줍니다.
        // message_ko_KR.properties 를 인식할 수 있게끔 _en_US / _kr_KO 는 무시됩니다.
        // 브라우저를 들어간 것이면 브라우저의 정보를 내포합니다. 미국에서 들어오면 _en_US 파일로 보고 한국이면 _kr_KO 파일만 봐요
        // setBasename 은 거의 고정값
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        /* 제공하지 않는 언어로 요청 시에 사용할 메세지 설정 */
        // 불어 프로퍼티파일을 만들지 않으면 한국어로 응답을 하겠다는 것이에여 디폴트로
        Locale.setDefault(Locale.KOREA);

        return source;
    }

}
