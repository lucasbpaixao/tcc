package Validator;

import javax.swing.JOptionPane;

public class ValidarSenha {
	public boolean validarSenha(String senha) {

		if (senha.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Senha n�o pode ser vazio", "Inv�lido", JOptionPane.WARNING_MESSAGE);
			return false;
		}else {
			return true;
		}
	}
}
