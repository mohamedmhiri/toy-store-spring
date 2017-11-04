package app.controller;

import app.config.PdfFileRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
 
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
@Controller
class HtmlToPdfController {
 
    private final RestTemplate restTemplate;
 
    @Autowired
    HtmlToPdfController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
 
    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    void createPdfFromHTML(HttpServletResponse response) {
        PdfFileRequest fileRequest = new PdfFileRequest();
        fileRequest.setFileName("bill.pdf");
        fileRequest.setSourceHtmlUrl("file:///home/mohamed/Documents/development/code/site/src/main/resources/templates/bill.html");
 
        byte[] pdfFile = restTemplate.postForObject("http://localhost:8080/api/pdf", 
                fileRequest, 
                byte[].class
        );
        writePdfFileToResponse(pdfFile, "bill.pdf", response);
    }
 
    private void writePdfFileToResponse(byte[] pdfFile, 
                                        String fileName, 
                                        HttpServletResponse response) {
        try (InputStream in = new ByteArrayInputStream(pdfFile)) {
            OutputStream out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
 
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        }
        catch (IOException ex) {
            throw new RuntimeException("Error occurred when creating PDF file", ex);
        }
    }
        @RequestMapping(value = "/bill", method = RequestMethod.GET)
        public String getPdf(){
                return "bill";
        }
}