package mb;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.LoginC;
import util.FacesContextUtil;


@SessionScoped
@ManagedBean
public class LoginCMB {

	
	private LoginC usuarioLogado;
	private LoginC usuarioForm;

	@PostConstruct
	private void init() {
		usuarioForm = new LoginC();
	}

	private EntityManager getEntityManager() {
		return FacesContextUtil.getEntityManager();
	}
	
	public String autenticar() {
		Query query = getEntityManager().createQuery(
				"SELECT l FROM LoginC l where l.nome = ?", LoginC.class);
		query.setParameter(1, getUsuarioForm().getNome());

		try {
			LoginC usuarioBanco = (LoginC) query.getSingleResult();

			if (usuarioBanco.getNome().equalsIgnoreCase(getUsuarioForm().getNome())
					&& usuarioBanco.getSenha().equals(getUsuarioForm().getSenha())) {
				
				usuarioLogado = usuarioBanco;
				
				return "/index.xhtml?faces-redirect=true";
				
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

	public LoginC getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(LoginC usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public LoginC getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(LoginC usuarioForm) {
		this.usuarioForm = usuarioForm;
	}
}
