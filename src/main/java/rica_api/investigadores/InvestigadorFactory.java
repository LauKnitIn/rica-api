package rica_api.investigadores;

import org.springframework.stereotype.Component;

@Component
public class InvestigadorFactory {
    private final InvestigadorRepository investigadorRepository;

    public InvestigadorFactory(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }      
    
    public Investigador crear(String nombreCompleto, String correoInstitucional, String grupoInvestigacion) {
        CorreoInstitucional correo = new CorreoInstitucional(correoInstitucional);
        if (investigadorRepository.existsByCorreoInstitucional_Valor(correo)) {
            throw new IllegalArgumentException("Ya existe un investigador con el correo " + correo);
        }
        return new Investigador(null, nombreCompleto, correo, grupoInvestigacion);
    }
}
