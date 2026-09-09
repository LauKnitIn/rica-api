package rica_api;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublicacionRepository extends MongoRepository<Publicacion, String>{
     
    List<Publicacion> findByInvestigadorCorreo(String investigadorCorreo);
}
