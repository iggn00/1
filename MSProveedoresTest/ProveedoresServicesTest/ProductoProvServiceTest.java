package com.perfulandia.proyecto_perfulandia_web.MSProveedoresTest.ProveedoresServicesTest;

import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorEntities.ProductoProv;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorRepositories.ProductoProvRepository;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorServices.ProductoProvServiceImpl;
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

public class ProductoProvServiceTest {

    @Mock
    private ProductoProvRepository productoProvRepository;

    @InjectMocks
    private ProductoProvServiceImpl productoProvServiceImpl;

    private ProductoProv productoProv;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productoProv = new ProductoProv(1L, 1L, 1L, 1000, LocalTime.of(2,0), 10);
    }

    @Test
    public void findByAllTest() {
        List<ProductoProv> lista = Arrays.asList(productoProv);
        when(productoProvRepository.findAll()).thenReturn(lista);
        List<ProductoProv> resultado = productoProvServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(productoProvRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(productoProvRepository.findById(1L)).thenReturn(Optional.of(productoProv));
        Optional<ProductoProv> resultado = productoProvServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(productoProvRepository).findById(1L);
    }

    @Test
    public void saveTest() {
        when(productoProvRepository.save(productoProv)).thenReturn(productoProv);
        ProductoProv resultado = productoProvServiceImpl.save(productoProv);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(productoProvRepository).save(productoProv);
    }

    @Test
    public void deleteFuncionaTest() {
        when(productoProvRepository.findById(1L)).thenReturn(Optional.of(productoProv));
        Optional<ProductoProv> resultado = productoProvServiceImpl.delete(productoProv);
        assertTrue(resultado.isPresent());
        verify(productoProvRepository).delete(productoProv);
    }

    @Test
    public void deleteNoFuncionaTest() {
        when(productoProvRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<ProductoProv> resultado = productoProvServiceImpl.delete(productoProv);
        assertTrue(resultado.isEmpty());
        verify(productoProvRepository).findById(1L);
        verify(productoProvRepository, never()).delete(any(ProductoProv.class));
    }
}
