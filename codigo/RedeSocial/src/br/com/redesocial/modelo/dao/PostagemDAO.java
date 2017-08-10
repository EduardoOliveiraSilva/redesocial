package br.com.redesocial.modelo.dao;

import br.com.redesocial.modelo.dto.Postagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IgorRodrigues
 */
public class PostagemDAO extends DAOCRUDBase<Postagem> {
    @Override
    public void excluir(int id) throws Exception {
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("delete from postagens where id = ?");
        
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    @Override
    public void inserir(Postagem p) throws Exception {
        Connection conexao = getConexao();
        if (p.getDescricao().equals("")){
            throw new Exception("A descricao nao pode estar vazia!");
        }
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("insert into postagem (curtidas,descricao,data) values (?,?,?)");
        
        pstmt.getCurtidas(1,p.curtidas);
        pstmt.getDescricao(2,p.descricao);
        pstmt.getDate(3,p.data);
        pstmt.executeUpdate();
        
    }

    @Override
    public void alterar(Postagem p) throws Exception{
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("update postagens set curtidas = ?, descricao = ?, data = ?, usuario = ? where id = ?");
        
        pstmt.setInt(1, p.getCurtidas());
        pstmt.setString(2, p.getDescricao());
        pstmt.setDate(3, new java.sql.Date(p.getData().getTime()));
        pstmt.setInt(4, p.getUsuario().getId());
        pstmt.setInt(5, p.getId());
        
        pstmt.executeUpdate();
    }

    @Override
    public Postagem selecionar(int id) throws Exception {
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("select * from postagens where id = ?");
        
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();
        
        if(rs.next()){
            Postagem p = new Postagem();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            p.setId(rs.getInt("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setCurtidas(rs.getInt("curtidas"));
            p.setData(rs.getDate("data"));
            p.setUsuario(usuarioDAO.selecionar(rs.getInt("usuario")));
            
            return p;
        } else {
            return null;
        }
    }
    
    /**
    *
    * @author Paulo Henrique
    */
    @Override
    public List listar() throws Exception {
        Connection conexao = getConexao();
        
        PreparedStatement pstmt;
        pstmt = conexao.prepareStatement("select * from postagens order by data desc");
        ResultSet rs;
        rs = pstmt.executeQuery();
        
        List lista;
        lista = new ArrayList();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        while(rs.next()){
            Postagem p = new Postagem();
            p.setId(rs.getInt("id"));
            p.setCurtidas(rs.getInt("curtidas"));
            p.setDescricao(rs.getString("descricao"));
            p.setData(rs.getDate("data"));
            p.setUsuario(usuarioDAO.selecionar(rs.getInt("usuario")));
            lista.add(p);
        }
        
        return lista;
     
    }
}
