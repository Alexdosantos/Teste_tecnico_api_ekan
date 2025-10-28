package com.teste_tecnico.ekan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teste_tecnico.ekan.entity.Cid;
import com.teste_tecnico.ekan.repository.CidRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CidService {

    private final CidRepository cidRepository;

    public Cid save(Cid cid) {
        return cidRepository.save(cid);
    }

    public List<Cid> findAll() {
        return cidRepository.findAll();
    }

    public Optional<Cid> findById(String id) {
        return cidRepository.findById(id);
    }

    public void delete(String id) {
        cidRepository.deleteById(id);
    }

    public Cid update(String id, Cid cid) {
        Cid cidUpdate = cidRepository.findById(id).get();
        cidUpdate.setName(cid.getName());
        return cidRepository.save(cidUpdate);
    }
}
