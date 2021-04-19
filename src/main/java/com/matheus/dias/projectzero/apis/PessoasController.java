package com.matheus.dias.projectzero.apis;

import com.matheus.dias.projectzero.repositories.PessoasRepository;
import com.matheus.dias.projectzero.models.Pessoas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.matheus.dias.projectzero.models.QPessoas.pessoas;

@RestController
@RequestMapping("/pessoas")
@Transactional(propagation = Propagation.SUPPORTS)
public class PessoasController {

    @Autowired
    private PessoasRepository repository;

    @GetMapping
    public List<Pessoas> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Pessoas> findOne(@PathVariable("id") Long id) {
        Optional<Pessoas> pessoa;
        try {
            return new ResponseEntity<Pessoas>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public Pessoas create(@RequestBody Pessoas pessoa) {
        if (repository.exists(pessoas.cpf.eq(pessoa.getCpf()))) {
            throw new ValidationException("O CPF informado já existe na base de dados");
        }
        pessoa.setAudDhCriacao(LocalDateTime.now());
        return repository.save(pessoa);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Pessoas pessoa) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(pessoa.getNome());
                    record.setEmail(pessoa.getEmail());
                    record.setCpf(pessoa.getCpf());
                    record.setDtNascimento(pessoa.getDtNascimento());
                    record.setNacionalidade(pessoa.getNacionalidade());
                    record.setNaturalidade(pessoa.getNaturalidade());
                    record.setAudDhAlteracao(LocalDateTime.now());
                    record.updateVersion();

                    Pessoas updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Pessoas> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
