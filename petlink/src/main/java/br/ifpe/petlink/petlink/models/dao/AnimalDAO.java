package br.ifpe.petlink.petlink.models.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.ifpe.petlink.petlink.models.Animal;

@Repository
public class AnimalDAO {
    private final JdbcTemplate jdbcTemplate;

    public AnimalDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(Animal animal) {
        String sql = "INSERT INTO animal (nome, raca, idade, dono_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, animal.getNome(), animal.getRaca(), animal.getIdade(), animal.getDono().getId());
    }

    public List<Animal> listAll() {
        String sql = "SELECT * FROM animal";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Animal animal = new Animal();
            animal.setId(rs.getLong("id"));
            animal.setNome(rs.getString("nome"));
            animal.setRaca(rs.getString("raca"));
            animal.setIdade(rs.getInt("idade"));
            return animal;
        });
    }

    public List<Animal> listByNome(String nome) {
        String sql = "SELECT * FROM animal WHERE nome LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + nome + "%"}, (rs, rowNum) -> {
            Animal animal = new Animal();
            animal.setId(rs.getLong("id"));
            animal.setNome(rs.getString("nome"));
            animal.setRaca(rs.getString("raca"));
            animal.setIdade(rs.getInt("idade"));
            return animal;
        });
    }

    // Método para buscar Animal por ID
    public Animal findById(Long id) {
        String sql = "SELECT * FROM animal WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Animal animal = new Animal();
            animal.setId(rs.getLong("id"));
            animal.setNome(rs.getString("nome"));
            animal.setRaca(rs.getString("raca"));
            animal.setIdade(rs.getInt("idade"));
            return animal;
        });
    }

    // Método de atualização
    public int update(Animal animal) {
        String sql = "UPDATE animal SET nome = ?, raca = ?, idade = ?, dono_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, animal.getNome(), animal.getRaca(), animal.getIdade(), animal.getDono().getId(), animal.getId());
    }

    // Método de exclusão
    public int delete(Long id) {
        String sql = "DELETE FROM animal WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
