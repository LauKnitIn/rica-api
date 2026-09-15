package rica_api;

public class PublicacionMapper {
    private PublicacionMapper() {
    }

    public static Publicacion aEntidad(PublicacionRequest request) {
        Publicacion p = new Publicacion();
        p.setInvestigadorCorreo(request.getInvestigadorCorreo());
        p.setTitulo(request.getTitulo());
        p.setTipo(request.getTipo());
        p.setAnio(request.getAnio());
        p.setDetalles(request.getDetalles());
        return p;
    }

    public static PublicacionResponse aResponse(Publicacion publicacion) {
        return new PublicacionResponse(
                publicacion.getInvestigadorCorreo(),
                publicacion.getTitulo(),
                publicacion.getTipo(),
                publicacion.getAnio(),
                publicacion.getDetalles()
        );
    }
}
