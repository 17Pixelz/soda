package com.ebanking.controllers;
 
/*
 * @Author Debu Paul
 */
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ebanking.Metier.WalletService;
import com.ebanking.Metier.Imp.EmailService;
import com.ebanking.dao.WalletTransactionRepository;
import com.ebanking.models.EmailTemplate;
import com.ebanking.models.WaletTransaction;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/notification")
@Slf4j
public class EmailController {

	@Autowired
	private WalletService walletService;
	@Autowired
	private EmailService emailService;

	@Autowired
	private WalletTransactionRepository  walletTransactionRepository;


	
	@PostMapping(value="/textemail/{idTransaction}")
	public String sendEmail(@PathVariable(name="idTransaction") int idTransaction)  {
		EmailTemplate emailTemp=new EmailTemplate();
	WaletTransaction wt=walletTransactionRepository.findById(idTransaction).orElse(null);
	String nom=wt.getTransactionFromCompte().getClient().getNomclient();
	String prenom=wt.getTransactionFromCompte().getClient().getPrenomClient();
	String body ="Transfert form:"+nom+" "+prenom +" "+"\n"+
	"Date:"+wt.getDateT()+
	"\n"+"la somme de transfert:"+wt.getAmount()+""
			+ "code de transfert"+wt.getTransactionId();
	;
	System.out.println("totot"+ wt.getAmount());
	emailTemp.setBody(body);
	String email=wt.getB().getEmail();
	emailTemp.setSendTo(body);
	emailTemp.setSendTo(email);
	emailTemp.setSubject("Transfert Argent");
	
		try {
			log.info("Sending Simple Text Email....");
		
			emailService.sendTextEmail(emailTemp);
			return "Email Sent!";
		} catch (Exception ex) {
			return "Error in sending email: " + ex;
		}
	}
	
	
	@PostMapping(value="/attachemail",consumes = "multipart/form-data")
	public String sendEmailWithAttachment(@RequestPart(value = "file") MultipartFile file) {
		try {
			log.info("Sending Attachment Email....");
			emailService.sendEmailWithAttachment(file);
			return "Email Sent!";
		} catch (Exception ex) {
			return "Error in sending email: " + ex;
		}
	}
	

}
