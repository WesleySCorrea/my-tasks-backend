package com.wsc.my_tasks_backend.service.impl;

import com.wsc.my_tasks_backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import com.wsc.my_tasks_backend.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import com.wsc.my_tasks_backend.entity.PasswordResetToken;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(PasswordResetToken passwordResetToken) {

        User user = passwordResetToken.getUser();
        String to = user.getEmail();
        String name = user.getName();

        String subject = "Recuperação de senha - My-Tasks";

        String resetLink =
                "http://localhost:4200/reset-password/" +
                        passwordResetToken.getToken();

        String body = """
            Olá, %s!
    
            Recebemos uma solicitação para redefinir a senha da sua conta.
    
            Para criar uma nova senha, clique no link abaixo:
    
            %s
    
            Por motivos de segurança, este link expirará em 15 minutos
            ou será automaticamente invalidado caso você solicite um novo link de redefinição.
    
            Se você não solicitou a redefinição de senha, ignore este email.
            Nenhuma alteração será feita na sua conta.
    
            Este é um email automático, por favor não responda.
    
            Atenciosamente,
            Equipe MyTasks
            """.formatted(name, resetLink);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
