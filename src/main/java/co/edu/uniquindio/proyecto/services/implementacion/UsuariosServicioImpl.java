package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioResponseDTO;
import co.edu.uniquindio.proyecto.modelo.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ProductoService;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServicioImpl implements UsuarioService {


    //Se inicializa la instancia automaticamente
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ModeradorRepo moderadorRepo;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public int CreateUser(UsuarioDTO usuarioDTO) throws Exception {

        Usuario buscado = usuarioRepo.buscarUsuario(usuarioDTO.getEmail());

        if(buscado!=null){
            throw new Exception("El correo "+usuarioDTO.getEmail()+" ya está en uso");
        }

        Usuario usuario = convertir(usuarioDTO);
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        System.out.println(usuario);

        if(moderadorRepo.count() == 0){
            Moderador moderador = new Moderador();
            moderador.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
            moderador.setNombre("SuperMan");
            moderador.setEmail("MiguelAngelBG0202@gmail.com");
            moderador.setCedula(1234567891);
            moderadorRepo.save(moderador);
        }

        return usuarioRepo.save( usuario ).getCedula();

    }

    @Override
    public UsuarioResponseDTO updateUser(int codigoUsuario,UsuarioDTO usuarioDTO) throws Exception {
        /**
         * TODO Validar que el correo no se repita
         */

        Usuario usuarioPrevio =  obtener(codigoUsuario);

        System.out.println(usuarioPrevio);

        System.out.println("Por aquiiiiiii");

        Usuario usuario = convertir(usuarioDTO);
        usuario.setCedula(codigoUsuario);
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setEstado(usuarioPrevio.getEstado());

        return convertirToDTO(usuarioRepo.save(usuario) );
    }



    @Override
    public UsuarioResponseDTO getUser(int codigoUsuario) throws Exception {

       return this.convertir(obtener(codigoUsuario));
    }

    public Usuario obtener(int codigoUsuario) throws Exception{
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);

        if(usuario.isEmpty() ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

        return usuario.get();
    }

    private  UsuarioResponseDTO convertir(Usuario usuario){
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(usuario.getCedula(),usuario.getNombre(),usuario.getEmail(),usuario.getTelefono(),usuario.getPassword());

        return usuarioDTO;
    }

    @Override
    public Boolean deleteUser(int codigoUsuario) throws Exception{

       // validarExiste(codigoUsuario);

        Usuario buscado = usuarioRepo.buscarUsuarioByCedula(codigoUsuario);

        if(buscado==null){
            throw new Exception("El usuario no existe");
        }

        buscado.setEstado(EstadoUsuario.INACTIVO);

      //  Usuario usuario = convertir(buscado);
         usuarioRepo.save(buscado).getCedula();

         return true;

        //usuarioRepo.deleteById(codigoUsuario);
    }

    private Usuario convertir(UsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario();
        usuario.setNombre( usuarioDTO.getNombre() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setCedula(usuarioDTO.getCedula());
        usuario.setDireccion( usuarioDTO.getDireccion() );
        usuario.setTelefono( usuarioDTO.getTelefono() );
        usuario.setPassword( usuarioDTO.getPassword() );

        return usuario;
    }

    private void validarExiste(int codigoUsuario) throws Exception{
        boolean existe = usuarioRepo.existsById(codigoUsuario);

        if( !existe ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

    }

    private UsuarioResponseDTO convertirToDTO(Usuario usuario){

        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getPassword()
        );

        return usuarioDTO;
    }

    public List<UsuarioResponseDTO> listarUsuarios (){

        List<Usuario> lista =  usuarioRepo.findAll(PageRequest.of(0,10)).toList();
        List<UsuarioResponseDTO> respuesta = new ArrayList<>();
        for (Usuario p : lista) {
            respuesta.add(convertir(p));
        }
        return respuesta;

    }

    public String obtenerCorreoPorID(int usuario){
        return usuarioRepo.obtenerCorreo(usuario);
    }


}
