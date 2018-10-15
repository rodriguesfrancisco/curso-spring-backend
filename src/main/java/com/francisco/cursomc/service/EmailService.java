package com.francisco.cursomc.service;

import com.francisco.cursomc.model.Cliente;
import com.francisco.cursomc.model.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
