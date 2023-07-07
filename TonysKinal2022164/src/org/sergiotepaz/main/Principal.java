/*
    Programador: Sergio Eduardo Tepaz Vela
    Carné: 2022164
    Codigo Técnico: IN5AV
    Fecha de cración: 28-03-2023
    Fecha de modificación: 
        28-03-2023
        11-04-2023
        12-04-2023
 */
package org.sergiotepaz.main;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.sergiotepaz.controller.EmpleadoController;
import org.sergiotepaz.controller.EmpresaController;
import org.sergiotepaz.controller.LoginController;
import org.sergiotepaz.controller.MenuPrincipalController;
import org.sergiotepaz.controller.PlatoController;
import org.sergiotepaz.controller.PresupuestoController;
import org.sergiotepaz.controller.ProductoController;
import org.sergiotepaz.controller.Productos_has_PlatosController;
import org.sergiotepaz.controller.ProgramadorController;
import org.sergiotepaz.controller.ServicioController;
import org.sergiotepaz.controller.Servicios_has_EmpleadosController;
import org.sergiotepaz.controller.Servicios_has_PlatosController;
import org.sergiotepaz.controller.TipoEmpleadoController;
import org.sergiotepaz.controller.TipoPlatoController;
import org.sergiotepaz.controller.UsuarioController;

public class Principal extends Application {
    private final String PAQUETE_VISTA = "/org/sergiotepaz/view/";
    private Stage escenarioPrincipal;
    private Scene escena;
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        escenarioPrincipal.setTitle("Tony´s Kinal 2023");
        escenarioPrincipal.getIcons().add(new Image("/org/sergiotepaz/image/logo.png"));
        
        // Parent root = FXMLLoader.load(getClass().getResource("/org/sergiotepaz/view/MenuPrincipalView.fxml"));
        // Scene escena = new Scene(root);
        // escenarioPrincipal.setScene(escena);
        ventanaLogin();
        escenarioPrincipal.show();
        
    }
    
    public void menuPrincipal() {
        try{
            MenuPrincipalController menu = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml",552,552);
            menu.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaProgramador() {
        try {
            ProgramadorController progra = (ProgramadorController)cambiarEscena("ProgramadorView.fxml",717,535);
            progra.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpresa() {
        try {
            EmpresaController empresaController = (EmpresaController)cambiarEscena("EmpresaView.fxml",800,750);
            empresaController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaProducto() {
        try {
            ProductoController productoController = (ProductoController)cambiarEscena("ProductoView.fxml",800,700);
            productoController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaTipoEmpleado() {
        try {
            TipoEmpleadoController tipoEmpleadoController = (TipoEmpleadoController)cambiarEscena("TipoEmpleadoView.fxml",800,700);
            tipoEmpleadoController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaTipoPlato() {
        try {
            TipoPlatoController tipoPlatoController = (TipoPlatoController)cambiarEscena("TipoPlatoView.fxml",800,700);
            tipoPlatoController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaPresupuesto() {
        try {
            PresupuestoController presupuestoController = (PresupuestoController)cambiarEscena("PresupuestoView.fxml",800,700);
            presupuestoController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpleado() {
        try {
            EmpleadoController empleadoController = (EmpleadoController) cambiarEscena("EmpleadoView.fxml", 1400, 700);
            empleadoController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaPlato() {
        try {
            PlatoController platoController = (PlatoController) cambiarEscena("PlatoView.fxml", 1400, 700);
            platoController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaLogin() {
        try {
            LoginController loginController = (LoginController) cambiarEscena("LoginView.fxml", 700, 500);
            loginController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaUsuario() {
        try {
            UsuarioController usuarioController = (UsuarioController) cambiarEscena("UsuarioView.fxml", 800, 700);
            usuarioController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaServicio() {
        try {
            ServicioController servicioController = (ServicioController) cambiarEscena("ServicioView.fxml", 1400, 700);
            servicioController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaProductos_has_Platos() {
        try {
            Productos_has_PlatosController productos_has_PlatosController  = (Productos_has_PlatosController) cambiarEscena("Productos_has_PlatosView.fxml", 800, 700);
            productos_has_PlatosController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaServicios_has_Platos() {
        try {
            Servicios_has_PlatosController servicios_has_PlatosController = (Servicios_has_PlatosController) cambiarEscena("Servicios_has_PlatosView.fxml", 800, 700);
            servicios_has_PlatosController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ventanaServicios_has_Empleados() {
        try {
            Servicios_has_EmpleadosController servicios_has_EmpleadosController = (Servicios_has_EmpleadosController) cambiarEscena("Servicios_has_EmpleadosView.fxml", 1100, 700);
            servicios_has_EmpleadosController.setEscenarioPrincipal(this);
            escenarioPrincipal.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception {
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA + fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA + fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo), ancho ,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        
        return resultado;
    }
   
}
