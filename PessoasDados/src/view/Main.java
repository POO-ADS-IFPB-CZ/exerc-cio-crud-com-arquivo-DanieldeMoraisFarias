package view;
import dao.PessoaDao;
import model.Pessoa;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        PessoaDao pessoaDao = new PessoaDao();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 para salvar uma pessoa \n 2 para listar todas as pessoas \n 3 para deletar uma pessoa \n 4 para sair\n");
            int escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1:
                    System.out.println("Insira o nome do pessoa: ");
                    String nome = sc.nextLine();
                    System.out.println("Insira o email do pessoa: ");
                    String email = sc.nextLine();
                    Pessoa pessoa = new Pessoa(nome, email);
                    pessoaDao.salvarPessoa(pessoa);
                    break;
                case 2:
                    pessoaDao.listarPessoas();
                    break;
                case 3:
                    System.out.println("Insira o nome da pessoa");
                    String nomePessoa = sc.nextLine();
                    System.out.println("Insira o email do pessoa: ");
                    String emailPessoa = sc.nextLine();
                    Pessoa pessoaDeletada = new Pessoa(nomePessoa, emailPessoa);
                    pessoaDao.removerPessoa(pessoaDeletada);
                    break;
                case 4:
                    return;
            }
        }
    }
}