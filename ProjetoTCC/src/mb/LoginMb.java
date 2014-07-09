package mb;



import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import util.FacesContextUtil;
import entity.Usuario;


@SessionScoped
@ManagedBean
public class LoginMb {
	
	private Usuario usuarioLogado;
	private Usuario usuarioForm;

	@PostConstruct
	private void init() {
		usuarioForm = new Usuario();
	}

	private EntityManager getEntityManager() {
		return FacesContextUtil.getEntityManager();
	}
	
	public String autenticar() {
		Query query = getEntityManager().createQuery(
				"SELECT u FROM Usuario u where u.nome = ?", Usuario.class);
		query.setParameter(1, getUsuarioForm().getNome());

		try {
			Usuario usuarioBanco = (Usuario) query.getSingleResult();

			if (usuarioBanco.getNome().equalsIgnoreCase(getUsuarioForm().getNome())
					&& usuarioBanco.getSenha().equals(getUsuarioForm().getSenha())) {
				
				usuarioLogado = usuarioBanco;
				
				return "/admin/listarHorarios.xhtml?faces-redirect=true";
				
			}
		} catch (NoResultException exception) {
			System.out.println("Usuario não encontrado.");
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario e senha informados não conferem."));
		return "";
	}
	
	public String fechar(){
		usuarioLogado = null;
		return "/index.xhtml?faces-redirect=true";
	}

	public boolean estaLogado() {
		return (usuarioLogado != null);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(Usuario usuarioForm) {
		this.usuarioForm = usuarioForm;
	}
}
