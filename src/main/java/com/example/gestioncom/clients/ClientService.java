package com.example.gestioncom.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements ClientInterface {
    
    @Autowired
    private ClientRepository repo;

    public Client login(String email, String password) throws Exception {
        List<Client> loginPossibilities = findByEmailAndPassword(email, password);
        if(loginPossibilities.size() > 0) {
            throw new Exception("Login failed");
        } else {
            return loginPossibilities.get(0);
        }
    }

    public Client signup(String email, String password, String nom, String prenom) throws Exception  {
        List<Client> loginPossibilities = findByEmailAndPassword(email, password);
        if(loginPossibilities.size() > 0) {
            throw new Exception("Can't sing up, already has an account");
        } else {
            Client curClient = new Client(email, password, nom, prenom);
            repo.save(curClient);
            return curClient;
        }
    }

    public List<Client> findByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public <S extends Client> S save(S client) {
        repo.save(client);
        return client;
    }

    @Override
    public <S extends Client> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public Optional<Client> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public Iterable<Client> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Iterable<Client> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void delete(Client entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteAll(Iterable<? extends Client> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }
}
