package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.sesion.SesionDTO;
import co.edu.uniquindio.proyecto.dto.token.TokenDTO;
import co.edu.uniquindio.proyecto.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.proyecto.seguridad.servicios.JwtService;
import co.edu.uniquindio.proyecto.services.interfaces.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SesionServicioImpl implements SesionService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public TokenDTO login(SesionDTO sesionDto){

        System.out.println("Email: " + sesionDto.getEmail() + "  Password:" + sesionDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sesionDto.getEmail(),
                        sesionDto.getPassword()
                )
        );
        System.out.println("Holaa");
        System.out.println("Llegue hasta aqui");

        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();



        String jwtToken  = jwtService.generateToken(user);

        return new TokenDTO(jwtToken);
    }

    @Override
    public void logout(){

    }

}
