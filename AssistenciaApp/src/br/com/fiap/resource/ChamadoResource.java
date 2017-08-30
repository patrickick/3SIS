package br.com.fiap.resource;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.dao.ChamadoDAO;
import br.com.fiap.dao.impl.ChamadoDAOImpl;
import br.com.fiap.entity.Chamado;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.EntityManagerFactorySingleton;

@Path("/chamado")
public class ChamadoResource {

	private EntityManagerFactory fabrica= EntityManagerFactorySingleton.getInstance();
	
	// /rest/Chamado/{codigo} DELETE
	@DELETE
	@Path("/{id}")
	public void remover(@PathParam("id") int codigo){
		EntityManager em = fabrica.createEntityManager();
		ChamadoDAO dao = new ChamadoDAOImpl(em);
		try {
			dao.remover(codigo);
			dao.salvar();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	// /rest/Chamado/{codigo} PUT
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") int codigo, Chamado Chamado){
		EntityManager em = fabrica.createEntityManager();
		ChamadoDAO dao = new ChamadoDAOImpl(em);
		Chamado.setCodigo(codigo);
		dao.alterar(Chamado);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
		}
		return Response.ok().build(); // HTTP Status 200 ok
	}
	
	// /rest/Chamado/{codigo} GET
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Chamado buscar(@PathParam("id") int codigo){
		EntityManager em = fabrica.createEntityManager();
		ChamadoDAO dao = new ChamadoDAOImpl(em);
		Chamado Chamado = dao.pesquisar(codigo);
		em.close();
		return Chamado;
	}
	
	// /rest/Chamado GET
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chamado> listar(){
		EntityManager em = fabrica.createEntityManager();
		ChamadoDAO dao = new ChamadoDAOImpl(em);
		List<Chamado> lista = dao.listar();
		em.close();
		return lista;
	}
	
	// /rest/Chamado POST
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Chamado Chamado, @Context UriInfo uriInfo){
		EntityManager em = fabrica.createEntityManager();
		ChamadoDAO dao = new ChamadoDAOImpl(em);
		Chamado.setData(Calendar.getInstance());
		try {
			dao.cadastrar(Chamado);
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		UriBuilder url = UriBuilder.fromPath(uriInfo.getPath());
		url.path(String.valueOf(Chamado.getCodigo()));
		return Response.created(url.build()).build();
	}
	
	@GET
	@Path("/funcionario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chamado> buscarPorCodFuncionario(@PathParam("id") int codigo){
		EntityManager em = fabrica.createEntityManager();
		ChamadoDAO dao = new ChamadoDAOImpl(em);
		List<Chamado> lista = dao.buscarPorCodFuncionario(codigo);
		em.close();
		return lista;
	}
	
}




