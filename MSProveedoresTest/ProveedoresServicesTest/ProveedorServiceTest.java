package com.perfulandia.proyecto_perfulandia_web.MSProveedoresTest.ProveedoresServicesTest;

import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorEntities.Proveedor;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorRepositories.ProveedorRepository;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorServices.ProveedorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorServiceImpl;

    private Proveedor proveedor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        proveedor = new Proveedor(1L, "Juan", "Pérez", "Calle Falsa", 123, "Santiago", "Chilena",
                987654321, "juan@mail.com", "1234", "web.com", LocalTime.of(9, 0), LocalTime.of(18, 0));
    }

    @Test
    public void findByAllTest() {
        List<Proveedor> lista = Arrays.asList(proveedor);
        when(proveedorRepository.findAll()).thenReturn(lista);
        List<Proveedor> resultado = proveedorServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(proveedorRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));
        Optional<Proveedor> resultado = proveedorServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombre());
        verify(proveedorRepository).findById(1L);
    }

    @Test
    public void saveTest() {
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);
        Proveedor resultado = proveedorServiceImpl.save(proveedor);
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(proveedorRepository).save(proveedor);
    }

    @Test
    public void deleteFuncionaTest() {
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));
        Optional<Proveedor> resultado = proveedorServiceImpl.delete(proveedor);
        assertTrue(resultado.isPresent());
        verify(proveedorRepository).delete(proveedor);
    }

    @Test
    public void deleteNoFuncionaTest() {
        when(proveedorRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Proveedor> resultado = proveedorServiceImpl.delete(proveedor);
        assertTrue(resultado.isEmpty());
        verify(proveedorRepository).findById(1L);
        verify(proveedorRepository, never()).delete(any(Proveedor.class));
    }
}
