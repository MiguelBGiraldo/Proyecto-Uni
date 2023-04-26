package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.sesion.SesionDTO;
import co.edu.uniquindio.proyecto.dto.token.TokenDTO;

public interface SesionService {

    TokenDTO login(SesionDTO sesionDto);

    void logout();
}
