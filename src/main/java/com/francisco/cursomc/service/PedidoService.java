package com.francisco.cursomc.service;

import com.francisco.cursomc.model.Categoria;
import com.francisco.cursomc.model.Pedido;
import com.francisco.cursomc.repositories.CategoriaRepository;
import com.francisco.cursomc.repositories.PedidoRepository;
import com.francisco.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()
        ));
    }
}
