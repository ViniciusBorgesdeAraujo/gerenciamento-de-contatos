public class Telefone {
    public enum Tipo{
        CELULAR, FIXO;
    }
    
    private String ddd;
    private String numero;
    private Tipo tipo;

    public Telefone(String ddd, String numero, Telefone.Tipo tipo) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo + " (" + ddd + ") " + numero;
    }
}
