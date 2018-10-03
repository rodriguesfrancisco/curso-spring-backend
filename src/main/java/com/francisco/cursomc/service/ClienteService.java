package com.francisco.cursomc.service;

import com.francisco.cursomc.model.Cliente;
import com.francisco.cursomc.repositories.ClienteRepository;
import com.francisco.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
}
