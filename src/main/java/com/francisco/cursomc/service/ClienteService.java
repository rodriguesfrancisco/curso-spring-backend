package com.francisco.cursomc.service;

import com.francisco.cursomc.dto.ClienteDTO;
import com.francisco.cursomc.dto.ClienteNewDTO;
import com.francisco.cursomc.model.Cidade;
import com.francisco.cursomc.model.Cliente;
import com.francisco.cursomc.model.Cliente;
import com.francisco.cursomc.model.Endereco;
import com.francisco.cursomc.model.enums.Perfil;
import com.francisco.cursomc.model.enums.TipoCliente;
import com.francisco.cursomc.repositories.ClienteRepository;
import com.francisco.cursomc.repositories.EnderecoRepository;
import com.francisco.cursomc.security.UserSS;
import com.francisco.cursomc.service.exceptions.AuthorizationException;
import com.francisco.cursomc.service.exceptions.DataIntegrityException;
import com.francisco.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Cliente find(Integer id){
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Perfil não autorizado!");
        }

        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + "Tipo: " + Cliente.class.getName()
        ));
    }

    public Cliente insert(Cliente obj){
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto){
        Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()), bCryptPasswordEncoder.encode(objDto.getSenha()));
        Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() != null){
            cliente.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3() != null){
            cliente.getTelefones().add(objDto.getTelefone3());
        }

        return cliente;
    }

    private void updateData(Cliente novoObj, Cliente obj){
        novoObj.setNome(obj.getNome());
        novoObj.setEmail(obj.getEmail());
    }
}
