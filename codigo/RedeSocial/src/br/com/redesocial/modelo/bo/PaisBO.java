package br.com.redesocial.modelo.bo;

import br.com.redesocial.modelo.dao.PaisDAO;
import br.com.redesocial.modelo.dto.Pais;

/**
 * Define as regras de negócio para os usuários
 * @author Ronneesley Moura Teles
 * @since 27/07/2017
 */
public class PaisBO extends BOCRUDBase<Pais, PaisDAO> {
    @Override
    protected PaisDAO instanciarDAO() {
        return new PaisDAO();
    }

    @Override
    protected void validar(Pais dto) throws Exception {
        //Validações
        if (dto.getNome().trim().equals("")) throw new Exception("Preencha o nome do país");
    }
    
}
