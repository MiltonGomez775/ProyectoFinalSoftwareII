package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Rol;
import edu.co.uniquindio.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}

