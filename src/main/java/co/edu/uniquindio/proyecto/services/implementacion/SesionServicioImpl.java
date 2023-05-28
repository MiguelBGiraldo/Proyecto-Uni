package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.sesion.SesionDTO;
import co.edu.uniquindio.proyecto.dto.token.TokenDTO;
import co.edu.uniquindio.proyecto.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.proyecto.seguridad.servicios.JwtService;
import co.edu.uniquindio.proyecto.seguridad.servicios.UserDetailsServiceImpl;
import co.edu.uniquindio.proyecto.services.interfaces.SesionService;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SesionServicioImpl implements SesionService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public TokenDTO login(SesionDTO sesionDto){

        System.out.println("Email: " + sesionDto.getEmail() + "  Password:" + sesionDto.getPassword());

        //Se verifica el estado del usuario


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sesionDto.getEmail(),
                        sesionDto.getPassword()
                )
        );

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken  = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new TokenDTO(jwtToken,refreshToken);
    }

    @Override
    public void logout(){

    }

    @Override
    public TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception{

        String email = jwtService.extracUsername(tokenDTO.getRefreshToken());
        UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);
        if (jwtService.isTokenValid(tokenDTO.getRefreshToken(), user)) {
            String token = jwtService.generateToken(user);

            return new TokenDTO( token, tokenDTO.getRefreshToken() );
        }
        throw new Exception("Error construyendo el token");
    }

}
