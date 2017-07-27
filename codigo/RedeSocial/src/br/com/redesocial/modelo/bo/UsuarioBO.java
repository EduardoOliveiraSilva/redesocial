package br.com.redesocial.modelo.bo;

import br.com.redesocial.modelo.dao.UsuarioDAO;
import br.com.redesocial.modelo.dto.Usuario;

/**
 * Define as regras de neg�cio para os usu�rios
 * @author Ronneesley Moura Teles
 * @since 27/07/2017
 */
public class UsuarioBO {
    /**
     * Inseri um usu�rio no banco de dados verificando as regras necess�rias de inser��o
     * @param usuario DTO com dados preenchidos
     * @throws Exception 
     */
    public void inserir(Usuario usuario) throws Exception {
        //Valida��es
        if (usuario.getNome().trim().equals("")) throw new Exception("Preencha o nome do usu�rio");
        
        if (usuario.getEmail().trim().equals("")) throw new Exception("Preencha o e-mail do usu�rio");
        
        if (usuario.getSenha().trim().equals("")) throw new Exception("Preencha a senha do usu�rio");
        
        //Inser��o
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserir(usuario);
    }
}
