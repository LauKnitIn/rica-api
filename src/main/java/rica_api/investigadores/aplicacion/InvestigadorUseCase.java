package rica_api.investigadores.aplicacion;

import java.util.List;

import rica_api.investigadores.dominio.Investigador;

public interface InvestigadorUseCase {
    List<Investigador> listarTodos();

    Investigador buscarPorId(Long id);

    Investigador registrar(String nombreCompleto, String correoInstitucional, String grupoInvestigacion);
}
