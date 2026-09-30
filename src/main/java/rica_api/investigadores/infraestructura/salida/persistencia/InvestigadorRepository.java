package rica_api.investigadores.infraestructura.salida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import rica_api.investigadores.dominio.CorreoInstitucional;
import rica_api.investigadores.dominio.Investigador;


public interface InvestigadorRepository extends JpaRepository<Investigador,Long>{
    boolean existsByCorreoInstitucional_Valor(String correoInstitucional);
    Investigador findByCorreoInstitucional_Valor(CorreoInstitucional valor);
}
