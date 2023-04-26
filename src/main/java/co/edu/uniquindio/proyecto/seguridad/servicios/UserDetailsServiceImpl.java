package co.edu.uniquindio.proyecto.seguridad.servicios;


import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.seguridad.modelo.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepo clienteRepo;

    @Autowired
    private ModeradorRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Usuario cliente = clienteRepo.buscarUsuario(email);

        if(cliente == null){

            Moderador admin = adminRepo.buscarUsuario(email);

            if(admin == null)
                throw new UsernameNotFoundException("El usuario no existe");

            return UserDetailsImpl.build(admin);
        }else {
            return UserDetailsImpl.build(cliente);
        }
    }
}
