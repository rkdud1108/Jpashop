package jpabook.jpashop.controller;

import jpabook.jpashop.domain.File.Files;
import jpabook.jpashop.service.FilesService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

@Controller
public class MungController {

    @Autowired
    FilesService filesService;

    @RequestMapping("mung/insert")
    public String Insert(){
        return "mung/insert";
    }

    @RequestMapping("mung/fileinsert")
    public String fileinsert(HttpServletRequest request, @RequestParam MultipartFile files) throws Exception{
        Files file =new Files();

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();//파일 확장자
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:/Users/GY/Desktop/study/jpashop/src/main/resources/static/images/";

        do{
            destinationFileName = RandomStringUtils.randomAlphabetic(32)+"."+sourceFileNameExtension;
            destinationFile = new File(fileUrl+destinationFileName);
        }while(destinationFile.exists());

        destinationFile.getParentFile().mkdirs();//디렉토리 만들고
        files.transferTo(destinationFile);//파일 저장

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileurl(fileUrl);
        filesService.save(file);
        return "redirect:/mung/insert";
    }
}
