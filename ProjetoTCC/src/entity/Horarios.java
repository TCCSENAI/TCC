package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.ManyToAny;

@Entity
public class Horarios {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false,length=50)
	
	private Date data;
	private String hora;

	@ManyToOne
	private Quadra quadra;
	@ManyToOne
	private Cliente cliente;
	
	
	
	public Quadra getQuadra() {
		return quadra;
	}
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}

	
	
}
