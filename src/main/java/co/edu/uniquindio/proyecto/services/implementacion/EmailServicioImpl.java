package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.Email.EmailDTO;
import co.edu.uniquindio.proyecto.services.interfaces.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServicioImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public void enviarEmail(EmailDTO emailDTO) throws Exception{

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        helper.setSubject(emailDTO.getAsunto());
        helper.setText(emailDTO.getCuerpo(),true);
        helper.setTo(emailDTO.getDestinatario());
        helper.setFrom("mabernalg@uqvirtual.edu.co");

        javaMailSender.send(mensaje);

    }


}
