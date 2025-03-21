package com.mwu.service;

import com.mwu.model.EmailDetails;

public interface EmailService {
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);

}
