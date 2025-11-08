package edu.co.uniquindio.demo.CommandLineRunner;

import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.model.Cita;
import edu.co.uniquindio.demo.model.EspacioDisponible;
import edu.co.uniquindio.demo.repository.UsuarioRepository;
import edu.co.uniquindio.demo.repository.InmuebleRepository;
import edu.co.uniquindio.demo.repository.CitaRepository;
import edu.co.uniquindio.demo.repository.EspacioDisponibleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Configuration // 🔹 importante para que Spring reconozca este Bean al iniciar
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepository,
            InmuebleRepository inmuebleRepository,
            EspacioDisponibleRepository espacioRepository
    ) {
        return args -> {
            System.out.println("🚀 Iniciando carga de datos iniciales...");

            // 1️⃣ Crear usuario administrador si no existe
            if (usuarioRepository.findByCorreo("administrador@gmail.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setCorreo("administrador@gmail.com");
                admin.setTelefono("3206991690");
                admin.setContrasena("123456789");
                admin.setRol("admin");
                usuarioRepository.save(admin);
                System.out.println("✅ Usuario admin creado");
            } else {
                System.out.println("⚠️ El usuario admin ya existe.");
            }

            // 2️⃣ Crear propietario si no existe
            Optional<Usuario> existente = usuarioRepository.findByCorreo("propietario@gmail.com");

            Usuario propietario = existente.orElseGet(() -> {
                Usuario nuevo = new Usuario();
                nuevo.setNombre("Propietario");
                nuevo.setCorreo("propietario@gmail.com");
                nuevo.setTelefono("3206991690");
                nuevo.setContrasena("123456789");
                nuevo.setRol("propietario");
                Usuario guardado = usuarioRepository.save(nuevo);
                System.out.println("✅ Propietario creado: " + guardado.getId());
                return guardado;
            });

            // 3️⃣ Crear inmueble si no existe
            Optional<Inmueble> inmuebleExistente = inmuebleRepository.findByDireccion("Prueba");

            Inmueble inmueble = inmuebleExistente.orElseGet(() -> {
                Inmueble nuevo = new Inmueble();
                nuevo.setDireccion("Prueba");
                nuevo.setArea(120.5);
                nuevo.setCanon(2500000.0);
                nuevo.setAdministracionIncluida(true);
                nuevo.setValorAdministracion(300000.0);
                nuevo.setDescripcion("Apartamento en buen estado, 3 habitaciones, 2 baños");
                nuevo.setEstado("disponible");
                nuevo.setPropietarioId(propietario.getId());
                nuevo.setFotos(List.of("https://misfotos.com/foto1.jpg"));

                Inmueble guardado = inmuebleRepository.save(nuevo);
                System.out.println("✅ Inmueble creado: " + guardado.getId());
                return guardado;
            });

            Optional<EspacioDisponible> espacioExistente =
                espacioRepository.findByInmuebleIdAndFechaAndHoraInicio(
                    inmueble.getId(), "2025-11-05", "16:00"
                );

            espacioExistente.orElseGet(() -> {
                EspacioDisponible nuevo = new EspacioDisponible();
                nuevo.setPropietarioId(propietario.getId());
                nuevo.setInmuebleId(inmueble.getId());
                nuevo.setFecha("2025-11-05");
                nuevo.setHoraInicio("16:00");
                nuevo.setHoraFin("16:30");
                nuevo.setDisponible(true);

                EspacioDisponible guardado = espacioRepository.save(nuevo);
                System.out.println("✅ Espacio inicializado: " + guardado.getId());
                return guardado;
});

            System.out.println("✅ Carga de datos iniciales finalizada.");
        };
    }
}
