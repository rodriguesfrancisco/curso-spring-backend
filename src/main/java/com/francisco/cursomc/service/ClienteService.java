package com.francisco.cursomc.service;

import com.francisco.cursomc.dto.ClienteDTO;
import com.francisco.cursomc.model.Cliente;
import com.francisco.cursomc.model.Cliente;
import com.francisco.cursomc.repositories.ClienteRepository;
import com.francisco.cursomc.service.exceptions.DataIntegrityException;
import com.francisco.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + "Tipo: " + Cliente.class.getName()
        ));
    }

    public Cliente update(Cliente obj){
        Cliente novoObj = find(obj.getId());
        updateData(novoObj, obj);
        return clienteRepository.save(novoObj);
    }

    public void delete(Integer id){
        find(id);
        try {
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
        }
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente novoObj, Cliente obj){
        novoObj.setNome(obj.getNome());
        novoObj.setEmail(obj.getEmail());
    }
}
