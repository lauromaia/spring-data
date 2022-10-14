package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionario por nome");
			System.out.println("2 - Buscar funcionario por nome, mínimo salário e data de contratação");
			System.out.println("3 - Buscar funcionario por data de contratação");
			System.out.println("4 - Buscar funcionario e salario");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioPorNome(scanner);
				break;
			case 2:
				buscaFuncionarioPorNomeSalarioData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				buscaFuncionarioSalario();
				break;
			default:
				system = false;
				break;

			}

		}
	}

	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual a data de contratação?");
		String data = scanner.next();
		LocalDate dataContrato = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(dataContrato);
		list.forEach(System.out::println);
		
	}

	private void buscaFuncionarioPorNomeSalarioData(Scanner scanner) {
		System.out.println("Que nome deseja pesquisar?");
		String nome = scanner.next();
		System.out.println("Qual o salario informado?");
		Double salario = scanner.nextDouble();
		System.out.println("Qual a data de contratação?");
		String data = scanner.next();
		LocalDate dataContrato = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContatacao(nome, salario, dataContrato);
		list.forEach(System.out::println);
	}

	private void buscaFuncionarioPorNome(Scanner scanner) {
		System.out.println("Que nome deseja pesquisar?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario ID: " + f.getId() + " | nome: " + 
		f.getNome() + " | salario: " + f.getSalario() ));
	}
}
