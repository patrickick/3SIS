package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Chamado;

public interface ChamadoDAO extends GenericDAO<Chamado, Integer> {
	
	List<Chamado> buscarPorCodFuncionario(int codigo);

}