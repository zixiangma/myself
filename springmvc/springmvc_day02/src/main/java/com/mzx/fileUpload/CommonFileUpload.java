package com.mzx.fileUpload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class CommonFileUpload {
    @RequestMapping("/commonFileLoad")
    public String fileUpload(HttpServletRequest request){
        try {
            String realPath = request.getRealPath("/files");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if(!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    fileName = UUID.randomUUID().toString() + fileName;
                    fileItem.write(new File(realPath,fileName));
                }
                fileItem.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
