package rica_api.publicaciones;

import java.util.List;

import org.springframework.stereotype.Service;

import rica_api.compartido.LimiteAnualExcedidoException;
import rica_api.compartido.RecursoNoEncontradoException;
import rica_api.investigadores.InvestigadorRepository;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final InvestigadorRepository investigadorRepository;
    private final LimitePublicacionesAnualesService limitePublicacionesService;


    public PublicacionService(PublicacionRepository publicacionRepository,
                               InvestigadorRepository investigadorRepository,
                                LimitePublicacionesAnualesService limitePublicacionesAnualesService) {
        this.publicacionRepository = publicacionRepository;
        this.investigadorRepository = investigadorRepository;
        this.limitePublicacionesService = limitePublicacionesAnualesService;
    }

    public Publicacion registrar(Publicacion publicacion) {
        if (!investigadorRepository.existsByCorreoInstitucional_Valor(publicacion.getInvestigadorCorreo())) {
            throw new RecursoNoEncontradoException(
                    "No existe un investigador con correo " + publicacion.getInvestigadorCorreo());
        }
        if (!limitePublicacionesService.puedeRegistrar(investigadorRepository.findByCorreoInstitucional_Valor(publicacion.getInvestigadorCorreo()), publicacion)) {
            throw new LimiteAnualExcedidoException(
                "El investigador " + publicacion.getInvestigadorCorreo() + "ya tiene 5 publicaciones registradas este año"
            );
        }
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> listarPorInvestigador(String investigadorCorreo) {
        return publicacionRepository.findByInvestigadorCorreo(investigadorCorreo);
    }

    public Publicacion buscarPorId(String id) {
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una publicación con id " + id));
    }
}
