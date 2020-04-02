package com.mzx.fileUpload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class SpringFileUpload {

    @RequestMapping("/springFileUpload")
    public String springFileUpload(HttpServletRequest req, MultipartFile file) throws IOException {
        String realPath = req.getRealPath("/files");
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        fileName= UUID.randomUUID().toString() + fileName;
        file.transferTo(new File(filePath,fileName));
        return "success";
    }
}
