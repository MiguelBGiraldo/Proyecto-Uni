package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.services.interfaces.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryServicioImpl implements CloudinaryService {


    private Cloudinary cloudinary;

    public CloudinaryServicioImpl(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name","ddphnjvqw");
        config.put("api_key","344586599725385");
        config.put("api_secret", "O-G5w6Yjijio2OwjMp1o3qmxfcg");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen (File file, String carpeta) throws Exception{

        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",String.format("uniquindio/proyecto/%s", carpeta)));

    }

    @Override
    public Map eliminarImagen (String idImagen) throws Exception{
        return cloudinary.uploader().destroy(idImagen,ObjectUtils.emptyMap());
    }

    public File convertir(MultipartFile imagen) throws IOException{

        File file = File.createTempFile(imagen.getOriginalFilename(),null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
