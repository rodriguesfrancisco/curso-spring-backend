package com.francisco.cursomc.repositories;

import com.francisco.cursomc.model.Categoria;
import com.francisco.cursomc.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
