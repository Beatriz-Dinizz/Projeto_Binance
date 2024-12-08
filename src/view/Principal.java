package view;

import javax.swing.JOptionPane;
import controller.LastPriceController;
import controller.OperacoesArquivos;

public class Principal {
	public static void main(String[] args) {

		String caminhoPasta;
		
		// VERIFICANDO O SISTEMA OPERACIONAL DO USUÁRIO (WINDOWS OU LINUX) E DEFININDO O CAMINHO DO DIRETÓRIO PARA CADA CASO
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			caminhoPasta = "C:\\TEMP";
		} else {
			caminhoPasta = "/itmp";
		}
		
		// NOME DO ARQUIVO JSON QUE SERÁ CRIADO E MANIPULADO
		String nomeArquivo = "btc.json";

		try {
			// CRIANDO O DIRETÓRIO E O ARQUIVO SE NÃO EXISTIR ATRAVÉS DO MÉTODO CRIADIRARQ DA CLASSE OPERACOESARQUIVOS
			OperacoesArquivos.criaDirArq(caminhoPasta, nomeArquivo);

			// SALVANDO OS DADOS EM UM ARQUIVO JSON ATRAVÉS DO MÉTODO ESCREVEARQUIVO DA CLASSE OPERACOESARQUIVOS
			OperacoesArquivos.escreveArquivo(caminhoPasta, nomeArquivo, conteudoAPI);

			// SOLICITANDO SYMBOL PARA O USUÁRIO
			String symbol = JOptionPane.showInputDialog(null, "Digite o symbol que deseja consultar o LastPrice (ex: ETHBTC): ");		

			// ACESSANDO MÉTODO EXIBIRLASTPRICE DA CLASSE LASTPRICECONTROLLER PARA LER O ARQUIVO, BUSCAR PELO SYMBOL DIGITADO E MOSTRAR O LAST PRICE ASSOCIADO
			LastPriceController lastPrice = new LastPriceController();
			lastPrice.exibirLastPrice(caminhoPasta, nomeArquivo, symbol);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}
	}
}

	