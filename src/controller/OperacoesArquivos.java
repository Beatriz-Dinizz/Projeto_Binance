package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class OperacoesArquivos {

	public static void criaDirArq(String caminhoPasta, String nomeArquivo) {
		try {
			File pasta = new File(caminhoPasta);

			// CRIANDO PASTA CASO ELA NÃO EXISTA
			if (!pasta.exists()) {
				pasta.mkdirs();
			}

			// CRIANDO O ARQUIVO CASO ELE NÃO EXISTA
			File arquivo = new File(pasta, nomeArquivo);
			if (!arquivo.exists()) {
				arquivo.createNewFile();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao criar diretório ou arquivo: " + e.getMessage());
		}
	}

	// MÉTODO PARA ESCREVER CONTEÚDO NO ARQUIVO JSON
	public static void escreveArquivo(String caminhoPasta, String nomeArquivo, String conteudo) {
		try {
			File arquivo = new File(caminhoPasta, nomeArquivo);
			try (PrintWriter escrever = new PrintWriter(new FileWriter(arquivo))) {
				escrever.print(conteudo);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo: " + e.getMessage());
		}
	}

	// MÉTODO PARA LER O ARQUIVO JSON
	public static String lerArquivo(String caminhoPasta, String nomeArquivo) {
		StringBuilder conteudo = new StringBuilder();
		try {
			File arquivo = new File(caminhoPasta, nomeArquivo);
			try (BufferedReader ler = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)))) {
				String linha;
				while ((linha = ler.readLine()) != null) {
					conteudo.append(linha);
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo: " + e.getMessage());
		}
		return conteudo.toString();
	}
}
