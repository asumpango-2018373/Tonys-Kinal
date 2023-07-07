package org.sergiotepaz.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.sergiotepaz.main.Principal;

public class MenuPrincipalController implements Initializable {
    
    private Principal escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
    
    public void ventanaProgramador(){
        escenarioPrincipal.ventanaProgramador();
    }
    
    public void ventanaEmpresa() {
        escenarioPrincipal.ventanaEmpresa();
    }
    
    public void ventanaProducto() {
        escenarioPrincipal.ventanaProducto();
    }
    
    public void ventanaTipoEmpleado() {
        escenarioPrincipal.ventanaTipoEmpleado();
    }
    
    public void ventanaTipoPlato() {
        escenarioPrincipal.ventanaTipoPlato();
    }
    
    public void ventanaPresupuesto() {
        escenarioPrincipal.ventanaPresupuesto();
    }
    
    public void ventanaLogin() {
        escenarioPrincipal.ventanaLogin();
    }
    
    public void ventanaUsuario() {
        escenarioPrincipal.ventanaUsuario();
    }
    
    public void ventanaProductos_has_Platos() {
        escenarioPrincipal.ventanaProductos_has_Platos();
    }
    
    public void ventanaServicios_has_Platos() {
        escenarioPrincipal.ventanaServicios_has_Platos();
    }
    
    public void ventanaServicios_has_Empleados() {
        escenarioPrincipal.ventanaServicios_has_Empleados();
    }
}
