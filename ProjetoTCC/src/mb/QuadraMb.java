package mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Horarios;
import entity.Quadra;
import util.FacesContextUtil;



@ManagedBean(name="quadMB")
public class QuadraMb {


	private List<Quadra> quadras;
	private EntityManager entityManager;
	private Quadra quadra;
	


	@PostConstruct
	private void init() {
		quadra = new Quadra();
		entityManager = FacesContextUtil.getEntityManager();
	}
	
	public List<Quadra> getQuadras() {
		if (quadras == null) {
			Query query = entityManager.createQuery(
					"SELECT m FROM Quadra m", Quadra.class);
			quadras = query.getResultList();
		}
		return quadras;
	}

	public void setQuadras(List<Quadra> quadras) {
		this.quadras = quadras;
	}
	
	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	
	public String salvar(){
		entityManager.merge(quadra);
		
		return "listarQuadras";
	}
	

	public String editar(Long id){
		quadra = entityManager.find(Quadra.class, id);
		
		return "quadrasform";
	}

	public String excluir(Long id){
		Quadra quadra = entityManager.find(Quadra.class, id);
		entityManager.remove(quadra);
		quadras = null;
		return "listarQuadras";
		
		
		
		
		
	}

	public  Quadra buscarQuadraPorId(Long id) {
		
		return entityManager.find(Quadra.class, id);
	}
}
