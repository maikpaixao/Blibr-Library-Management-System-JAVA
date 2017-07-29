package br.ufrpe.blibr.dados;

import java.util.ArrayList;

import br.ufrpe.blibr.exception.ElementoNaoExistente;
import br.ufrpe.blibr.negocio.beans.Funcionario;
import br.ufrpe.blibr.negocio.beans.Usuario;

public class RepositorioFuncionario {
	
	private static RepositorioFuncionario instance;
	private ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

	public static synchronized RepositorioFuncionario getInstance(){
		if(instance == null){
			instance = new RepositorioFuncionario();
		}
		return instance;
	}
	
	public void adicionarFuncionario(Funcionario funcionario)throws Exception{
		try {
			if(funcionario!=null && !this.listaFuncionario.contains(funcionario)){
				this.listaFuncionario.add(funcionario);
			}else{
				throw new ElementoNaoExistente(funcionario);
			}
		} catch (ElementoNaoExistente e) {
			e.getObj();
		}
	}
	
	public ArrayList<Funcionario> listarFuncionario(){
		return this.listaFuncionario;
	}
	
	public Funcionario buscarFuncionario(Long codFuncionario)throws ElementoNaoExistente{
		Funcionario retorno = null;
		try {
			for(Funcionario funcionario: listaFuncionario){
				if(funcionario.getCodFuncionario() == codFuncionario){
					retorno =  funcionario;
				}else{
					throw new ElementoNaoExistente(buscarFuncionario(codFuncionario));
				}
			}
		} catch (ElementoNaoExistente e) {
			e.getObj();
		}
		return retorno;
	}
	
	public void editarFuncionario(Funcionario funcionario)throws Exception{
		try {
			for(Funcionario func: this.listaFuncionario){
				if (func.getNome().equals(funcionario.getNome()) && funcionario!=null){
					int indice = this.listaFuncionario.indexOf(func);
			        listaFuncionario.set(indice, funcionario);
			    }else{
			        throw new ElementoNaoExistente(funcionario);
			    }
			}
		} catch (ElementoNaoExistente e) {
			e.getObj();
		}
	}
	
	public void removerUsuario(Long codFuncionario)throws ElementoNaoExistente{
		try {
			for(Funcionario func: listaFuncionario){
				if(func.getCodFuncionario()==codFuncionario && codFuncionario!=null){
					this.listaFuncionario.remove(func);
					break;
				}else{
					throw new ElementoNaoExistente(buscarFuncionario(codFuncionario));
				}
			}
		}catch(ElementoNaoExistente e){
			e.getObj();
		}
	}
}