package com.mzx.fileUpload;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileToAnother {

    @RequestMapping("fileToAnother")
    public String fileToAnother(MultipartFile file) throws IOException {
        String anotherPath = "http://localhost:8090/test/files/";
        String fileName = file.getOriginalFilename();
        String filePath = anotherPath + fileName;

        Client client = Client.create();
        WebResource resource = client.resource(filePath);
        resource.put(file.getBytes());
        return "success";


    }
}
