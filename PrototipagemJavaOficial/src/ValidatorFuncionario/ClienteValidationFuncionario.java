package ValidatorFuncionario;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import DAO.ClienteDAO;
import VO.ClienteVO;
import View.CadastroClienteView;
import View.VendasView;
import ViewFuncionarios.CadastroClienteViewFuncionario;

public class ClienteValidationFuncionario {
	ValidarNome validarNome = new ValidarNome();
	ValidarCnpj validarCnpj = new ValidarCnpj();
	ValidarCpf validarCpf = new ValidarCpf();
	ValidarEmail validarEmail = new ValidarEmail();
	ValidarCep validarCep = new ValidarCep();
	ValidarRua validarRua = new ValidarRua();
	ValidarNumero validarNumero = new ValidarNumero();
	ValidarNextel validarNextel = new ValidarNextel();
	ValidarTelefone validarTelefone = new ValidarTelefone();
	ValidarCelular validarCelular = new ValidarCelular();
	ValidarPagamento validarPagamento = new ValidarPagamento();
	ValidarCodigo validarCodigo = new ValidarCodigo();

	public boolean validarDados(ClienteVO cVO) throws Exception {
		boolean retorno = true;
		boolean retornoCodigo = validaCodigoCliente(cVO.getCodigo());
		boolean retornoNome = validarNome.validaNomeCliente(cVO.getNome());
		boolean retornoEmail = validarEmail.validaEmailCliente(cVO.getEmail());
		boolean retornoTelefone = validarTelefone.validaTelefoneCliente(cVO.getFixo());
		boolean retornoNextel = validarNextel.validaNextelCliente(cVO.getNextel());
		boolean retornoCelular = validarCelular.validaCelularCliente(cVO.getCel());
		boolean retornoCnpj = validarCnpj.validaCnpjCliente(cVO.getCnpj());
		boolean retornoCpf = validarCpf.validaCpfCliente(cVO.getCpf());
		//boolean retornoCep = validarCep.validaCepCliente(cVO.getCep());
		boolean retornoPagamento = validarPagamento.validaPagamentoCliente(cVO.getFormaDePagamento());
		boolean retornoRua = validarRua.validaRuaCliente(cVO.getRua());
		boolean retornoNumero = validarNumero.validaNumeroCliente(cVO.getNumero());

		if (retornoCodigo && retornoNome && retornoEmail && retornoTelefone && retornoNextel && retornoCelular
				&& retornoCnpj && retornoCpf && retornoPagamento && /*retornoCep &&*/ retornoRua && retornoNumero ) {
			retorno = true;
		} else {
			retorno = false;
			throw new Exception("Um dos retornos � falso");
		}
		return retorno;
	}

	public boolean validaCodigoCliente(int codigo) throws Exception {

		boolean retorno = true;
		CadastroClienteViewFuncionario.lblId.setForeground(Color.BLACK);
		if (codigo == 0) {
			retorno = false;
			CadastroClienteViewFuncionario.lblId.setForeground(Color.RED);
		} else if (codigo < 0) {
			JOptionPane.showMessageDialog(null, "Digite um c�digo v�lido", "Inv�lido", JOptionPane.WARNING_MESSAGE);
			CadastroClienteViewFuncionario.lblId.setForeground(Color.BLACK);
			return false;
		}else {
			CadastroClienteViewFuncionario.lblId.setForeground(Color.BLACK);
			try {
				ClienteDAO clienteDAO = new ClienteDAO();
				boolean retornoCodigo_Cliente = clienteDAO.verificaExistenciaCodigo_Cliente(codigo);
				if (retornoCodigo_Cliente == false) {
					retornoCodigo_Cliente = true;
					retorno = true;
				} else {
					retornoCodigo_Cliente = false;
					retorno = false;
					JOptionPane.showMessageDialog(null, "C�digo do Cliente j� Castrado", "C�digo Inv�lido",
							JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public boolean validaCodigoClienteVenda(int codigo) {

		boolean retorno = true;
		if (codigo <= 0) {
			retorno = false;
			VendasView.txtCodigoCli.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else if (codigo < 0) {
			JOptionPane.showMessageDialog(null, "Digite um c�digo v�lido", "Inv�lido", JOptionPane.WARNING_MESSAGE);
			CadastroClienteViewFuncionario.lblId.setForeground(Color.BLACK);
			return false;
		}else {
			VendasView.txtCodigoCli.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			try {
				ClienteDAO clienteDAO = new ClienteDAO();
				boolean retornoCodigo_Cliente = clienteDAO.verificaExistenciaCodigo_Cliente(codigo);
				if (retornoCodigo_Cliente) {
					retornoCodigo_Cliente = true;
					retorno = true;
				} else {
					JOptionPane.showMessageDialog(null, "Cliente n�o existe", "C�digo Inv�lido",
							JOptionPane.WARNING_MESSAGE);
					retorno = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
}
