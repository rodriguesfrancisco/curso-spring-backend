package com.francisco.cursomc.service;

import com.francisco.cursomc.dto.EstadoDTO;
import com.francisco.cursomc.model.Estado;
import com.francisco.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll(){
        return estadoRepository.findAllByOrderByNome();
    }
}
