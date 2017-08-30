package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import br.com.fiap.dao.ChamadoDAO;
import br.com.fiap.entity.Chamado;

public class ChamadoDAOImpl extends GenericDAOImpl<Chamado, Integer> implements ChamadoDAO {

	public ChamadoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Chamado> buscarPorCodFuncionario(int codigo) {
		return em.createQuery("from Chamado c where c.codigoFuncionario = :c",Chamado.class)
				.setParameter("c", codigo).getResultList();
	}
	
}