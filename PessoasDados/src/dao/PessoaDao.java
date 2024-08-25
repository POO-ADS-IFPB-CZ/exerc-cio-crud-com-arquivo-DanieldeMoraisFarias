package dao;

import model.Pessoa;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDao {
    private File arquivo;
    private Set<Pessoa> pessoas;

    public PessoaDao() {
        arquivo = new File("pessoa.dat");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo");
            }
        }
        pessoas = new HashSet<>();
        carregarDados();
    }
    private void carregarDados() {
        if (arquivo.length() == 0) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            pessoas = (Set<Pessoa>) ois.readObject();
        } catch (FileNotFoundException e) {
            pessoas = new HashSet<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(pessoas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void salvarPessoa(Pessoa pessoa ){
        if(pessoas.add(pessoa)){
            salvarDados();
            System.out.println("Pessoa Salvo com sucesso");
        }
        else{
            System.out.printf("Pessoa já cadastrada");
        }
    }
    public void listarPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("Lista de Pessoas:");
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa);
            }
        }
    }
    public void removerPessoa(Pessoa pessoa){
        if(pessoas.remove(pessoa)){
            salvarDados();
            System.out.println("Pessoa removida com sucesso");
        }
        else{
            System.out.println("Pessoa nao removida");
        }
    }
}
