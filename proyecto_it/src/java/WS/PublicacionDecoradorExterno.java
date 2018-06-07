package WS;

public class PublicacionDecoradorExterno extends PublicacionDecorado {

    private boolean suscripcion;

    public boolean isSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(boolean suscripcion) {
        this.suscripcion = suscripcion;
    }
    
}
