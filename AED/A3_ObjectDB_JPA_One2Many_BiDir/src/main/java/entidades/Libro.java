package entidades;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Libro {
	@Id
	private String isbn;
	private String titulo;
	private String autor;
	private Date fecha;
	private int precio;
	@ManyToOne
	private Persona prestadoA;

	public Libro() {
	}

	public Libro(String isbn) {
		this.isbn = isbn;
	}

	public Libro(String isbn, String titulo, String autor, Date fecha, int precio) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.fecha = fecha;
		this.precio = precio;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Persona getPrestadoA() {
		return prestadoA;
	}

	public void setPrestadoA(Persona prestadoA) {
		this.prestadoA = prestadoA;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", fecha=" + fecha + ", precio="
				+ precio + ", prestado=" + (prestadoA!=null?"SI":"NO") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (isbn == null) {
			return false;			
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}
}
