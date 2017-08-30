package br.com.fiap.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TB_CHAMADO")
@SequenceGenerator(name="seqChamado",sequenceName="SQ_TB_CHAMADO",allocationSize=1)
public class Chamado {

	@Id
	@Column(name="CD_CHAMADO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqChamado")
	private int codigo;
	
	@Column(name="CD_FUNCIONARIO")
	private int codigoFuncionario;

	@Column(name="DT_CHAMADO")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@Column(name="IS_FINALIZADO")
	private boolean finalizado;
	
	@Column(name="DS_CHAMADO")
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
}