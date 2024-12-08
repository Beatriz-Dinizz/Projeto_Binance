package controller;

import javax.swing.JOptionPane;

public class LastPriceController {

	public void exibirLastPrice(String caminhoPasta, String nomeArquivo, String symbol) {
		// LENDO O CONTEÚDO DO ARQUIVO JSON ATRAVÉS DO MÉTODO LERARQUIVO DA CLASSE OPERACOESARQUIVOS
		String conteudoArquivo = OperacoesArquivos.lerArquivo(caminhoPasta, nomeArquivo);
		if (conteudoArquivo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O arquivo está vazio!");
			return;
		}

		// SEPARANDO OS OBJETOS DO JSON PARA PODER REALIZAR A BUSCA
		String[] objetos = conteudoArquivo.split("\\}, \\{");
		boolean encontrou = false;
		boolean encontrouSymbol = false;

		// PROCURANDO PELO SYMBOL DIGITADO PARA EXIBIR O LAST PRICE ATRAVÉS DE UM FOR EACH
		for (String objeto : objetos) {
			objeto = objeto.replace("[", "").replace("]", "").replace("{", "").replace("}", ""); // REMOVENDO OS CARACTERES PARA FACILICAR O PROCESSAMENTO

			// SEPARANDO OS DADOS DO OBJETO EM PARES EM QUE CADA ELEMENTO TERÁ UMA CHAVE E VALOR (EX: "symbol":"ETHBTC" E "lastPrice":"0.03983000")
			String[] pares = objeto.split(",");
			String symbolAtual = null; 
			String lastPrice = null; 

			for (String par : pares) {
				
				// DIVIDINDO CADA PAR EM CHAVE E VALOR
				String[] chaveValor = par.split(":");
				if (chaveValor.length == 2) {
					String chave = chaveValor[0].trim().replace("\"", ""); 
					String valor = chaveValor[1].trim().replace("\"", "");

					if (chave.equals("symbol")) {
						if (symbol.toLowerCase().equals(valor.toLowerCase())) {
							symbolAtual = valor;
							encontrouSymbol = true;
						}
					} else if (chave.equals("lastPrice")) {
						if (encontrouSymbol) {
							lastPrice = valor;
							break;
						}
					}
				}
			}
			
			// EXIBINDO O RESULTADO PARA O USUÁRIO
			if (symbol.toLowerCase().equals(symbolAtual.toLowerCase())) {
				JOptionPane.showMessageDialog(null, "Symbol: " + symbolAtual + " Last Price: " + lastPrice);
				encontrou = true;
				break;
			}
		}
		if (!encontrou) {
			JOptionPane.showMessageDialog(null, "Symbol não encontrado no arquivo.");
		}
	}
}
