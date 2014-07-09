package mb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.FacesContextUtil;
import entity.Cliente;
import entity.Horarios;







@ManagedBean(name="horMB")
public class HorarioMb {

	private List<Horarios> horarios;
	private EntityManager entityManager;
	private Horarios horario;
	private Date data;

	
	@PostConstruct
	private void init() {
		horario = new Horarios();
		entityManager = FacesContextUtil.getEntityManager();
	}
	
	public List<Horarios> getHorarios() {
		if (horarios == null) {
			Query query = entityManager.createQuery(
					"SELECT h FROM Horarios h", Horarios.class);
			horarios = query.getResultList();
		}
		return horarios;
	}
	
	public void setHorarios(List<Horarios> horarios) {
		this.horarios = horarios;
	}



	public Horarios getHorario() {
		return horario;
	}

	public void setHorario(Horarios horario) {
		this.horario = horario;
	}

	public String salvar() {
		entityManager.merge(horario);

		return "listarHorarios";
	}
	
	public String salvartwo() {
		entityManager.merge(horario);

		return "listarhorario";
	}
	
	public String editar(Long id){
		horario = entityManager.find(Horarios.class, id);
		
		return "horariosform";
	}

	public String excluir(Long id){
		Horarios horario = entityManager.find(Horarios.class, id);
		entityManager.remove(horario);
		horarios = null;
		return "listarHorarios";
	}
	
	public String buscarData (){
		Query query = entityManager.createQuery(
				"SELECT h FROM Horarios h where h.data =? ", Horarios.class);
		query.setParameter(1,data);
		horarios = query.getResultList();
		
		
		return "listarHorarios";
		
	}
	
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	

	
}
