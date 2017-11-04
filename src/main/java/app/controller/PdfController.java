package app.controller;

import app.services.PdfFileCreator;
import app.config.PdfFileRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import javax.servlet.http.HttpServletResponse;
 
@RestController
class PdfController {
 
    private final PdfFileCreator pdfFileCreator;
 
    @Autowired
    PdfController(PdfFileCreator pdfFileCreator) {
        this.pdfFileCreator = pdfFileCreator;
    }
 
    @RequestMapping(value = "/api/pdf", method = RequestMethod.POST)
    void createPdf(@RequestBody PdfFileRequest fileRequest, HttpServletResponse response) {
        pdfFileCreator.writePdfToResponse(fileRequest, response);
    }
}




// import app.models.Toy;
// import app.models.Command;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import java.util.HashMap;
// import java.util.Map;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import org.springframework.web.bind.ServletRequestUtils;
// import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import java.util.List;
// import org.springframework.ui.ModelMap;



// @Controller
// public class PdfController {

// 	@RequestMapping(value="/pdfView")
// 	public ModelAndView createPdf(@ModelAttribute("toys")List<Toy> toys){
		
		
// 		return new ModelAndView("pdfView","toys",toys);
// 	}	
// }

