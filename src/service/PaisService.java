package service;

import dao.PaisDAO;
import model.Pais;

public class PaisService {
	private PaisDAO dao;
	
	public PaisService() {
		dao = new PaisDAO();
	}
	public int criar(Pais p){
		return dao.incluir(p);
	}
	public void atualizar(Pais p){ 
		dao.atualizar(p); 
	}
	public void excluir(Pais p){
		dao.excluir(p); 
	}
	public void excluir(int id){
		dao.excluir(new Pais(id));	
	}
	public Pais carregar(Pais p) {
		return dao.carregar(p);
	}
	public Pais carregar(int id) {
		return dao.carregar(new Pais(id));
	}
	
	

}
