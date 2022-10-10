package br.com.alura.spring.data.orm;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unidades_de_trabalho")
public class UnidadeDeTrabalho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String endereco;
	
	@ManyToMany(mappedBy = "unidadeDeTrabalhos", fetch = FetchType.EAGER)
	List<Funcionario> funcionarios;
	
	public UnidadeDeTrabalho(String descricao, String endereco) {
		this.descricao = descricao;
		this.endereco = endereco;
		
	}
	
	public void adicionar(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
	}
	


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}


	
}
