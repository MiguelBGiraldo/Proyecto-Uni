package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.Email.EmailDTO;

public interface EmailService {

    void enviarEmail(EmailDTO emailDTO) throws  Exception;
}
