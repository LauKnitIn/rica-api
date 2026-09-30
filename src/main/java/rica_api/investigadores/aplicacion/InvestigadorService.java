package rica_api.investigadores.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import rica_api.compartido.RecursoNoEncontradoException;
import rica_api.investigadores.dominio.CorreoDuplicadoException;
import rica_api.investigadores.dominio.Investigador;

@Service
public class InvestigadorService implements InvestigadorUseCase {
    private final RepositorioInvestigadores investigadorRepository;
    private final InvestigadorFactory investigadorFactory;
    

    public InvestigadorService(RepositorioInvestigadores investigadorRepository, InvestigadorFactory investigadorFactory) {
        this.investigadorRepository = investigadorRepository;
        this.investigadorFactory = investigadorFactory;
    }

    public List<Investigador> listarTodos() {
        return investigadorRepository.listarTodos();
    }

    public Investigador buscarPorId(Long id) {
        return investigadorRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un investigador con id " + id));
    }

    public Investigador registrar(String nombre, String correoInstitucional, String grupoInvestigacion) {
        Investigador investigador = investigadorFactory.crear(nombre, correoInstitucional, grupoInvestigacion);
        if (investigadorRepository.existeCorreo(correoInstitucional)) {
            throw new CorreoDuplicadoException(
                    "Ya existe un investigador registrado con el correo " + investigador.getCorreoInstitucional());
        }
        return investigadorRepository.guardar(investigador);
    }   
}
