package br.ifpe.petlink.petlink.models.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.ifpe.petlink.petlink.models.Dono;

@Repository
public class DonoDAO {
    private final JdbcTemplate jdbcTemplate;

    public DonoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(Dono dono) {
        String sql = "INSERT INTO dono (nome, cpf, endereco, telefone) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, dono.getNome(), dono.getCpf(), dono.getEndereco(), dono.getTelefone());
    }

    public List<Dono> listAll() {
        String sql = "SELECT * FROM dono";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Dono dono = new Dono();
            dono.setId(rs.getLong("id"));
            dono.setNome(rs.getString("nome"));
            dono.setCpf(rs.getString("cpf"));
            dono.setEndereco(rs.getString("endereco"));
            dono.setTelefone(rs.getString("telefone"));
            return dono;
        });
    }

    public List<Dono> listByNome(String nome) {
        String sql = "SELECT * FROM dono WHERE nome LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + nome + "%"}, (rs, rowNum) -> {
            Dono dono = new Dono();
            dono.setId(rs.getLong("id"));
            dono.setNome(rs.getString("nome"));
            dono.setCpf(rs.getString("cpf"));
            dono.setEndereco(rs.getString("endereco"));
            dono.setTelefone(rs.getString("telefone"));
            return dono;
        });
    }

    public Dono findById(Long id) {
        String sql = "SELECT * FROM dono WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Dono dono = new Dono();
            dono.setId(rs.getLong("id"));
            dono.setNome(rs.getString("nome"));
            dono.setCpf(rs.getString("cpf"));
            dono.setEndereco(rs.getString("endereco"));
            dono.setTelefone(rs.getString("telefone"));
            return dono;
        });
    }

    public int update(Dono dono) {
        String sql = "UPDATE dono SET nome = ?, cpf = ?, endereco = ?, telefone = ? WHERE id = ?";
        return jdbcTemplate.update(sql, dono.getNome(), dono.getCpf(), dono.getEndereco(), dono.getTelefone(), dono.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM dono WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}