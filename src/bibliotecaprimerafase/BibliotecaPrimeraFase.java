/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecaprimerafase;

import sv.edu.udb.Data.modelos.Categoria;
import sv.edu.udb.Data.modelos.Libro;
import sv.edu.udb.Services.CatalogosService;

/**
 *
 * @author Douglas
 */
public class BibliotecaPrimeraFase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CatalogosService catalogosService = new CatalogosService();
        
        
        //Prueba de eliminacion de Categorias
        catalogosService.eliminarTodasCategorias();
        
        //Prueba de insercion de categorias
        Categoria categoriaPrimera = new Categoria(){};
        categoriaPrimera.nombre = "Cienas naturales";
        categoriaPrimera.descripcion = "Categorias de las Ciencas Naturales en basica";
        
        Categoria categoriaSegunda = new Categoria();
        categoriaSegunda.nombre = "Lenguaje";
        categoriaSegunda.descripcion = "Categoria que trata acerca de la materia Lenguaje";
        
        
        long primerCodigo = catalogosService.insertarCategoria(categoriaPrimera);
        long segundaCategoria = catalogosService.insertarCategoria(categoriaSegunda);
    }
    
}
