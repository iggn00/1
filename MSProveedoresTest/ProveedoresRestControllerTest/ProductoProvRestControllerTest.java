package com.perfulandia.proyecto_perfulandia_web.MSProveedoresTest.ProveedoresRestControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorEntities.ProductoProv;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorServices.ProductoProvServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoProvRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ProductoProvServiceImpl productoProvServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verProductosProvTest() throws Exception {
        ProductoProv prod = new ProductoProv(1L, 1L, 1L, 1000, LocalTime.of(2,0), 10);
        List<ProductoProv> lista = Arrays.asList(prod);
        when(productoProvServiceImpl.findByAll()).thenReturn(lista);
        mockMvc.perform(get("/api/productoprov")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnProductoProvTest() throws Exception {
        ProductoProv prod = new ProductoProv(1L, 1L, 1L, 1000, LocalTime.of(2,0), 10);
        when(productoProvServiceImpl.findById(1L)).thenReturn(Optional.of(prod));
        mockMvc.perform(get("/api/productoprov/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void productoProvNoExisteTest() throws Exception {
        when(productoProvServiceImpl.findById(100L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/productoprov/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProductoProvTest() throws Exception {
        ProductoProv prod = new ProductoProv(null, 1L, 1L, 1000, LocalTime.of(2,0), 10);
        ProductoProv prodGuardado = new ProductoProv(2L, 1L, 1L, 1000, LocalTime.of(2,0), 10);
        when(productoProvServiceImpl.save(any(ProductoProv.class))).thenReturn(prodGuardado);
        mockMvc.perform(post("/api/productoprov")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prod)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modificarProductoProvTest() throws Exception {
        ProductoProv prodActualizado = new ProductoProv(1L, 2L, 2L, 2000, LocalTime.of(3,0), 20);
        when(productoProvServiceImpl.findById(1L)).thenReturn(Optional.of(prodActualizado));
        when(productoProvServiceImpl.save(any(ProductoProv.class))).thenReturn(prodActualizado);
        mockMvc.perform(put("/api/productoprov/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prodActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarProductoProvTest() throws Exception {
        ProductoProv prod = new ProductoProv(1L, 1L, 1L, 1000, LocalTime.of(2,0), 10);
        when(productoProvServiceImpl.delete(any(ProductoProv.class))).thenReturn(Optional.of(prod));
        mockMvc.perform(delete("/api/productoprov/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prod)))
                .andExpect(status().isOk());
    }
}
