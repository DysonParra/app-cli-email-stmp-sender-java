/*
 * @fileoverview    {EmailSenderService}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.smtp;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * TODO: Description of {@code EmailSenderService}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public class EmailSenderService {

    /**
     * TODO: Description of {@code sendEmail}.
     *
     */
    public void sendEmail() {
        try {
            String email = "dysontilano@gmail.com";
            String pass = "MY_PASSWORD";
            String smtp = "smtp.gmail.com";
            String receiver = "dyson.parra@udea.edu.co";
            Properties props = new Properties();

            // Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", smtp);

            // TLS si esta disponible
            props.setProperty("mail.smtp.starttls.enable", "true");

            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", "587");

            // Nombre del usuario
            props.setProperty("mail.smtp.user", email);

            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            // Para obtener un log de salida mas extenso
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress(email));

            // A quien va dirigido
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            message.setSubject("Asunto de prueba");
            message.setText("Mensaje de prueba");
            Transport t = session.getTransport("smtp");

            // Aqui usuario y password de gmail
            t.connect(email, pass);
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
