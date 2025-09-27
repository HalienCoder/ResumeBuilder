package com.example.resume.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.resume.model.User;
import com.example.resume.model.WorkExpAtt;
import com.example.resume.repository.UserRepository;
import com.example.resume.repository.WorkExpRepo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class  IndexController {
	@Autowired
	private UserRepository UR ;
	@Autowired
	private WorkExpRepo WR;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@PostMapping("/register")
	public String createUser(@ModelAttribute User user, Model model) {
	    UR.save(user);
	    
	    model.addAttribute("ID", user.getID());
	    model.addAttribute("user",user);
	    return "exp";
	}

	
	@GetMapping("/exp")
	public String workExp() {
		return "exp";
	}
	@PostMapping("/exp")
	public String createExp(@ModelAttribute WorkExpAtt workExpAtt,Model model ) {
		System.out.print(workExpAtt.toString());
		System.out.print(workExpAtt.getRefID());
		int userID = workExpAtt.getRefID();
		User userObj = UR.getReferenceById(userID);
		System.out.print(userObj.toString());
		if (workExpAtt.getUser() != null) {
            UR.save(workExpAtt.getUser());
        } else {
        	System.out.println("user obj in workExpAttr is NULL");
        	workExpAtt.setUser(userObj);
        }
      WR.save(workExpAtt);
      model.addAttribute("ID",userObj.getID());
      model.addAttribute("user",userObj);
        return "exp";
    }
	
	@PostMapping("/download")
    public ResponseEntity<byte[]> generatePdf(@ModelAttribute("RefID") String refid, Model model) throws IOException {
    	int userId = Integer.parseInt(refid);
//    	System.out.println("user id in PDF generate is "+ userId);
    	User userObj = UR.getReferenceById(userId);
//    	System.out.println("user object is  "+ userObj.toString());
        // PDF generation logic
        ByteArrayOutputStream byteArrayOutputStream = generatePdfContent(userObj);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "resume.pdf");

        return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
    }

    // Method to generate PDF content based on user information
    private ByteArrayOutputStream generatePdfContent(User user) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                
            	// Set font and font size
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);

                // Add user information to the PDF content

                contentStream.beginText();
                contentStream.setLeading(14.5f); 
                contentStream.newLineAtOffset(20, 700);

                contentStream.showText("Resume");
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLine();
                contentStream.showText("Name: " + user.getName());
                contentStream.newLine();
                
                contentStream.showText("Email: " + user.getEmail());
                contentStream.newLine();
                contentStream.showText("Phone: " + user.getPhone());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("DOB: " + user.getDate());
                contentStream.newLine();
                contentStream.showText("Gender: " + user.getGender());
                contentStream.newLine();
                contentStream.showText("Address: " + user.getAddress());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.showText("Education");
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.showText("School/Institution: " + user.getSchool());
                contentStream.newLine();
                contentStream.showText("10th Marks: " + user.getTenthmark());
                contentStream.newLine();
                contentStream.showText("Year of Completion (10th): " + user.getYoctenth());
                contentStream.newLine();
                contentStream.showText("10+2 Marks: " + user.getTwelthmark());
                contentStream.newLine();
                contentStream.showText("Year of Completion (10+2th): " + user.getYocplustwo());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.showText("Work Experience");
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                List<WorkExpAtt> workExperiences = WR.findByUser(user);
                if (workExperiences != null && !workExperiences.isEmpty()) {
                    for (WorkExpAtt workExp : workExperiences) {
                        contentStream.newLine();
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                        contentStream.showText("Company: " + workExp.getCompanyname());
                        contentStream.newLine();
                        contentStream.setFont(PDType1Font.HELVETICA, 12);
                        contentStream.showText("Job Role: " + workExp.getJobrole());
                        contentStream.newLine();
                        contentStream.showText("Duration: " + workExp.getDuration());
                        contentStream.newLine();
                        contentStream.showText("Skills Gained: " + workExp.getSkillsgained());
                        contentStream.newLine();
                    }
                } else {
                	contentStream.showText("No Work Experience");
                }
                contentStream.endText();
            }
            

            document.save(byteArrayOutputStream);
        }
        return byteArrayOutputStream;
    }
}

