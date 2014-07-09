package mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import util.FacesContextUtil;
import entity.Cliente;
import entity.Horarios;
import entity.LoginC;
import entity.Usuario;


@ManagedBean(name="cliMB")
public class ClienteMb {
	private List<Cliente> clientes;
	private EntityManager entityManager;
	private Cliente cliente;
	private LoginC login;


	@PostConstruct
	private void init() {
		login = new LoginC();
		cliente = new Cliente();
		entityManager = FacesContextUtil.getEntityManager();
	}
	
	
	
	public List<Cliente> getclientes() {
		if (clientes == null) {
			Query query = entityManager.createQuery(
					"SELECT c FROM Cliente c", Cliente.class);
			clientes = query.getResultList();
		}
		return clientes;
	}
	public void setclientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	

	
	public String salvar(){
		
		 entityManager.merge(cliente);
		 entityManager.merge(login);
		 
		 return "index";
		
	}
	

	public LoginC getLogin() {
		return login;
	}



	public void setLogin(LoginC login) {
		this.login = login;
	}

	public Cliente getcliente() {
		return cliente;
	}

	public void setcliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String editar(Long id){
		cliente = entityManager.find(Cliente.class, id);
		
		return "/clientesform";
	}

	public String excluir(Long id){
	Cliente cliente = entityManager.find(Cliente.class, id);
		entityManager.remove(cliente);
		entityManager.remove(login);
		clientes = null;
		login = null;
		return "listarClientes";
		
		
		
		
		
	}

	public Cliente buscarclientePorId(Long id) {
		
		return entityManager.find(Cliente.class, id);
	}
}



