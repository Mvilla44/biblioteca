public class Book {

    private final int id;
    private final String titulo;
    private final String autor;
    private final int año;

    public Book (int id ,String titulo, String autor, int año) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.año = año;
    }

    public void mostrarInformacion() {
        System.out.println("ID: " + getId());
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Autor: " + getAutor());
        System.out.println("Año: " + getAño());
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public int getAño() {
        return año;
    }
}
