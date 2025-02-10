package br.ifpe.petlink.petlink.spring_boot;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifpe.petlink.petlink.models.Dono;
import br.ifpe.petlink.petlink.models.dao.DonoDAO;

@Controller
@RequestMapping("/donos")
public class DonoController {

    private final DonoDAO donoDAO;

    public DonoController(DonoDAO donoDAO) {
        this.donoDAO = donoDAO;
    }

    @GetMapping("/cadastro")
    public String abreFormulario(Model model) {
        model.addAttribute("dono", new Dono());
        return "cadDono";
    }

    @PostMapping("/cadastro")
    public String insereDono(@ModelAttribute Dono dono, Model model) {
        donoDAO.create(dono);
        model.addAttribute("mensagem", "Dono cadastrado com sucesso!");
        return "redirect:/donos/listar";
    }

    @GetMapping("/listar")
    public String listarDonos(@RequestParam(required = false) String nome, Model model) {
        List<Dono> donos = (nome != null && !nome.isEmpty()) ? donoDAO.listByNome(nome) : donoDAO.listAll();
        model.addAttribute("donos", donos);
        return "listarDonos";
    }

    @GetMapping("/editar/{id}")
    public String editarDono(@PathVariable Long id, Model model) {
        Dono dono = donoDAO.findById(id);
        model.addAttribute("dono", dono);
        return "cadDono";
    }

    @PostMapping("/editar")
    public String atualizarDono(@ModelAttribute Dono dono) {
        donoDAO.update(dono);
        return "redirect:/donos/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarDono(@PathVariable Long id) {
        donoDAO.delete(id);
        return "redirect:/donos/listar";
    }
}