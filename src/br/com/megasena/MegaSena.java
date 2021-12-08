package br.com.megasena;

import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class MegaSena {

	static int NUMERO_DEZENAS = 6;

	static String sorteioComputador = "";
	static String apostaUsuario = "";

	public static void main(String[] args) {

		int[] sorteio = sorteaSena();
		int[] aposta = new int[NUMERO_DEZENAS];

		for (int i = 0; i < NUMERO_DEZENAS; i++) {

			int nroAposta;
			boolean repetido = false;

			do {
		
				nroAposta = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a dezena " + (i + 1) + ": ",
						"Simulador Da Mega Sena", JOptionPane.PLAIN_MESSAGE));

				if (nroAposta <= 0) {

					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Número inválido, aposta cancelada!", "Simulador Da Mega Sena",
							JOptionPane.ERROR_MESSAGE);
					return;

				} else if (nroAposta > 60) {

					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Número inválido, aposta cancelada!", "Simulador Da Mega Sena",
							JOptionPane.ERROR_MESSAGE);
					return;

				}

				repetido = existeNumero(aposta, nroAposta);

				if (repetido) {

					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Ops, número repetido!", "Simulador Da Mega Sena",
							JOptionPane.ERROR_MESSAGE);
				}
			} while (repetido);
			aposta[i] = nroAposta;
		}

		for (int i = 0; i < aposta.length; i++) {

			apostaUsuario = "Aposta do usuário: " + " \n" + aposta[0] + " \n" + aposta[1] + " \n" + aposta[2] + " \n"
					+ aposta[3] + " \n" + aposta[4] + " \n" + aposta[5];
		}

		for (int i = 0; i < sorteio.length; i++) {

			sorteioComputador = "Números sorteados: " + " \n" + sorteio[0] + " \n" + sorteio[1] + " \n" + sorteio[2]
					+ " \n" + sorteio[3] + " \n" + sorteio[4] + " \n" + sorteio[5];
		}

		int nroAcertos = contaAcertos(sorteio, aposta);

		JOptionPane.showMessageDialog(null,
				apostaUsuario + "\n" + sorteioComputador + " \n Números de acertos: \n " + nroAcertos,
				"Simulador Da Mega Sena", JOptionPane.INFORMATION_MESSAGE);

		switch (nroAcertos) {

		case 4:
			JOptionPane.showMessageDialog(null, "Parabéns. Você acertou a quadra!", "Simulador Da Mega Sena",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "Parabéns. Você acertou a quina!", "Simulador Da Mega Sena",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "Parabéns. Você é campeão da MegaSena!", "Simulador Da Mega Sena",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Tente novamente!", "Simulador Da Mega Sena",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
	
	static int[] sorteaSena() {

		int[] resultado = new int[NUMERO_DEZENAS];

		for (int i = 0; i < NUMERO_DEZENAS; i++) {

			int sorteado;
			boolean repetido = false;

			do {
				sorteado = (int) (Math.random() * 60) + 1;
				repetido = existeNumero(resultado, sorteado);
			} while (repetido);
			resultado[i] = sorteado;
		}
		return resultado;
	}

	static int contaAcertos(int[] sorteio, int[] aposta) {

		int acertos = 0;

		for (int i = 0; i < NUMERO_DEZENAS; i++) {

			int nroAposta = aposta[i];

			if (existeNumero(sorteio, nroAposta)) {
				acertos++;
			}
		}
		return acertos;
	}

	static boolean existeNumero(int[] numeros, int n) {

		for (int i = 0; i < numeros.length; i++) {
			if (numeros[i] == n) {
				return true;
			}
		}
		return false;
	}
}