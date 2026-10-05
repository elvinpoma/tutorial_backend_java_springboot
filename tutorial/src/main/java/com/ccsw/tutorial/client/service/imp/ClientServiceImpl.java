package com.ccsw.tutorial.client.service.imp;

import com.ccsw.tutorial.client.dto.ClientDto;
import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.repository.ClientRepository;
import com.ccsw.tutorial.client.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elportil
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(Long id) {
        return this.clientRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, ClientDto dto) throws Exception {
//
// Si el cliente recibido no tiene ID, se interpreta como una creación.
// Si ya existe en la base de datos, se lanzará un error.
// Si el cliente viene con un ID, se tratará como una modificación.
//
        if (id == null && clientRepository.existsByName(dto.getName())) {
            throw new Exception("The customer already exists!");
        }

        Client client;

        if (id == null) {
            client = new Client();
        } else {
            client = this.get(id);
        }

        client.setName(dto.getName());

        this.clientRepository.save(client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {
        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.clientRepository.deleteById(id);
    }
}
