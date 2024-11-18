package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    /* comment. yml 파일에 작성한 value 를 읽어들이기 위한 준비 */
    private ResourceLoader resourceLoader;

    @PostMapping("single-file")
    public String singleFile(@RequestParam MultipartFile singleFile , String description , Model model) throws IOException {

        /* comment. @RequestParam 어노테이션은 우리가 요청한 데이터를 받아올 수 있게
        *           하는 어노테이션이다.
        *           하지만, 매개변수명이 일치한다면 생략이 가능하다. */
        System.out.println("singleFile = " + singleFile);
        System.out.println("description = " + description);

        /* Index. 1. 파일을 저장할 위치 설정 */
        Resource resource = resourceLoader.getResource("classpath:static/img/single");

        String filePath = null;

        // 위에서 지정한 파일 저장 위치가 존재하지 않는다면
        if(!resource.exists()){
            String root = "src/main/resources/static/img/single";
            // 경로를 집어넣은 파일 객체 생성
            File file = new File(root);

            // make directory 의 약자
            // 우리가 root 에 지정한 경로대로 디렉토리를 만들어 준다.
            
            file.mkdirs();
            
            // 만든 폴더의 경로를 filePath 변수에 담아주기
            filePath = file.getAbsolutePath();
        } else{
            // 디렉토리가 만들어진 적이 있다면
            filePath = resourceLoader.getResource("classpath:static/img/single")
                    .getFile().getAbsolutePath();
        }
        
        /* Index. 2. 파일 데이터 받아오고, 저장할 경로를 가져왔다면
        *            데이터 변경처리 시작(가공) 
        * */
        
        // 원본 파일명 담기
        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);
        // 확장자 제거 ( spring.jpg 에서 .jpg 를 날려버린다 )
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);
        // 파일에 고유한 이름부여
        // 고유 번호를 부여해주는 UUID 클래스
        // UUID는 - - - 이런 식인데 대쉬(-)를 공백처리한다.
        // 그리고 뒤에다 확장자 붙여줌
        String savedName = UUID.randomUUID().toString().replace("-","") + ext;
        System.out.println("savedName = " + savedName);

        // spring.png
        // ext <- 확장자 제거
        // spring , png
        // adada-adsa-adas 고유한 문자 생김 
        // - 대쉬는 저장할 필요 없으므로 빈문자열로 치환
        
        /* Index. 3. 고유한 파일 식별번호 및 파일 저장경로 생성완료 
        *            이제 파일을 저장경로에 저장
        * */
        
        // 전달받은 파일 객체를 변환
        singleFile.transferTo(new File(filePath + "/" + savedName));

        model.addAttribute("message","파일 업로드 성공!!");
        model.addAttribute("img","static/img/single/" + savedName);

        return "result";
    }

    @PostMapping("multi-file")
    public String multiFile(@RequestParam List<MultipartFile> multiFile , String description , Model model) throws IOException {


        /* Index. 1. 파일을 저장할 위치 설정 */
        Resource resource = resourceLoader.getResource("classpath:static/img/multi");

        String filePath = null;

        if(!resource.exists()){
            String root = "src/main/resources/static/img/multi";
            File file = new File(root);

            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else{
            filePath = resourceLoader.getResource("classpath:static/img/multi")
                    .getFile().getAbsolutePath();
        }

        /* Index. 멀티 파일 변경 처리 */
        List<FileDTO> files = new ArrayList<>();
        List<String> savedFiles = new ArrayList<>();

        for(MultipartFile file : multiFile){
            String originFileName = file.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-","")+ext;

            files.add(new FileDTO(originFileName,savedName,filePath,description));
            file.transferTo(new File(filePath+"/"+savedName));
            savedFiles.add("static/img/multi/"+savedName);
        }

        model.addAttribute("message","파일 업로드 성공!!");
        model.addAttribute("imgs",savedFiles);

        return "result";
    }
}
