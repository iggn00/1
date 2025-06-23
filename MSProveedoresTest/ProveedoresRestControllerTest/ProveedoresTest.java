package com.perfulandia.proyecto_perfulandia_web.MSProveedoresTest.ProveedoresRestControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorEntities.Proveedor;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorEntities.ProductoProv;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorServices.ProveedorServiceImpl;
import com.perfulandia.proyecto_perfulandia_web.MSProveedores.ProveedorServices.ProductoProvServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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
public class ProveedoresTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProveedorServiceImpl proveedorServiceImpl;

    @MockitoBean
    private ProductoProvServiceImpl productoProvServiceImpl;



    @Test
    public void verProveedoresTest() throws Exception {
        Proveedor proveedor = new Proveedor(1L, "Juan", "Pérez", "Calle Falsa", 123, "Santiago", "Chilena", 987654321, "juan@mail.com", "1234", "web.com", LocalTime.of(9,0), LocalTime.of(18,0));
        List<Proveedor> lista = Arrays.asList(proveedor);
        when(proveedorServiceImpl.findByAll()).thenReturn(lista);
        mockMvc.perform(get("/api/proveedor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnProveedorTest() throws Exception {
        Proveedor proveedor = new Proveedor(1L, "Juan", "Pérez", "Calle Falsa", 123, "Santiago", "Chilena", 987654321, "juan@mail.com", "1234", "web.com", LocalTime.of(9,0), LocalTime.of(18,0));
        when(proveedorServiceImpl.findById(1L)).thenReturn(Optional.of(proveedor));
        mockMvc.perform(get("/api/proveedor/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void proveedorNoExisteTest() throws Exception {
        when(proveedorServiceImpl.findById(100L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/proveedor/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProveedorTest() throws Exception {
        Proveedor proveedor = new Proveedor(null, "Juan", "Pérez", "Calle Falsa", 123, "Santiago", "Chilena", 987654321, "juan@mail.com", "1234", "web.com", LocalTime.of(9,0), LocalTime.of(18,0));
        Proveedor proveedorGuardado = new Proveedor(2L, "Juan", "Pérez", "Calle Falsa", 123, "Santiago", "Chilena", 987654321, "juan@mail.com", "1234", "web.com", LocalTime.of(9,0), LocalTime.of(18,0));
        when(proveedorServiceImpl.save(any(Proveedor.class))).thenReturn(proveedorGuardado);
        mockMvc.perform(post("/api/proveedor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor)))
                .andExpect(status().isCreated());
    }

    @Test
    public void modificarProveedorTest() throws Exception {
        Proveedor proveedorActualizado = new Proveedor(1L, "Juan", "Pérez", "Nueva Calle", 456, "Providencia", "Chilena", 987654321, "juan@mail.com", "1234", "web.com", LocalTime.of(10,0), LocalTime.of(19,0));
        when(proveedorServiceImpl.findById(1L)).thenReturn(Optional.of(proveedorActualizado));
        when(proveedorServiceImpl.save(any(Proveedor.class))).thenReturn(proveedorActualizado);
        mockMvc.perform(put("/api/proveedor/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedorActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarProveedorTest() throws Exception {
        Proveedor proveedor = new Proveedor(1L, "Juan", "Pérez", "Calle Falsa", 123, "Santiago", "Chilena", 987654321, "juan@mail.com", "1234", "web.com", LocalTime.of(9,0), LocalTime.of(18,0));
        when(proveedorServiceImpl.delete(any(Proveedor.class))).thenReturn(Optional.of(proveedor));
        mockMvc.perform(delete("/api/proveedor/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor)))
                .andExpect(status().isOk());
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