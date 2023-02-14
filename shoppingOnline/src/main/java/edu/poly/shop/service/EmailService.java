package edu.poly.shop.service;

import edu.poly.shop.model.EmailDetails;

public interface EmailService {
	String sendEmail(EmailDetails details);
}
