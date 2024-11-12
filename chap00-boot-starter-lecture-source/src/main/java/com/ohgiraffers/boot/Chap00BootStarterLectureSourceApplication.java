package com.ohgiraffers.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// SpringBootApplication 은 우리가 손을 올려보니 컴포넌트들을 스캔하고 있습니다.
// 컴포넌트 스캔의 범위를 지정하지 않았으면 boot 이하의 폴더하위를 스캔합니다.
// 만약에 오지라퍼스에 갔으면 오지라퍼스하위를 스캔합니다.
// 어플리케이션은 자기 범위안을 스캔하는데 ContextConfig 에 스캔범위를 오지라퍼스범위를 지정해서 스캔했으니
// 오지라퍼스 하위에 테스트 빈도 스캔합니다.
@SpringBootApplication
public class Chap00BootStarterLectureSourceApplication {

    public static void main(String[] args) {
        
        // 컨테이너 생성 , 자기자신을 스캔할 정보를 run 의 정보에 넣어줌
        SpringApplication.run(Chap00BootStarterLectureSourceApplication.class, args);
    }
}
