/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecaprimerafase;

import java.util.List;
import sv.edu.udb.Data.modelos.Categoria;
import sv.edu.udb.Data.modelos.Estante;
import sv.edu.udb.Data.modelos.Libro;
import sv.edu.udb.Data.modelos.Rol;
import sv.edu.udb.Data.modelos.Usuario;
import sv.edu.udb.Services.AdminService;
import sv.edu.udb.Services.CatalogosService;
import sv.edu.udb.Services.ItemsService;

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
        ItemsService itemsService = new ItemsService();
        
    }
    
}
