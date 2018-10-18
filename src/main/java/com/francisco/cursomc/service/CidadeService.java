package com.francisco.cursomc.service;

import com.francisco.cursomc.model.Cidade;
import com.francisco.cursomc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findByEstado(Integer estadoId){
        List<Cidade> list = cidadeRepository.findCidades(estadoId);
        for(Cidade x : list){
            System.out.println(x.getId());
            System.out.println(x.getNome());
        }
        return list;
    }
}
