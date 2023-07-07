package org.sergiotepaz.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.sergiotepaz.bean.Plato;
import org.sergiotepaz.bean.Servicio;
import org.sergiotepaz.bean.Servicios_has_Platos;
import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.main.Principal;

public class Servicios_has_PlatosController implements Initializable {

    private Principal escenarioPrincipal;
    private enum operaciones {
        NINGUNO, GUARDAR, ACTUALIZAR, ELIMINAR
    }
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Servicios_has_Platos> listaServicios_has_Platos;
    private ObservableList<Plato> listaPlato;
    private ObservableList<Servicio> listaServicio;
    
    @FXML
    private TextField txtServicios_codigoServicio;
    @FXML
    private ComboBox cmbCodigoPlato;
    @FXML
    private ComboBox cmbCodigoServicio;
    @FXML
    private TableView tblServicios_has_Platos;
    @FXML 
    private TableColumn colServicios_codigoServicio;
    @FXML
    private TableColumn colCodigoPlato;
    @FXML
    private TableColumn colCodigoServicio;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnEliminar;
    @FXML 
    private Button btnEditar;
    @FXML 
    private Button btnReporte;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private ImageView imgEliminar;
    @FXML 
    private ImageView imgEditar;
    @FXML
    private ImageView imgReporte;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        desactivarControles();
        cmbCodigoPlato.setItems(getPlato());
        cmbCodigoServicio.setItems(getServicio());
        colServicios_codigoServicio.setCellValueFactory(new PropertyValueFactory<Servicios_has_Platos, Integer>("Servicios_codigoServicio"));
        colCodigoPlato.setCellValueFactory(new PropertyValueFactory<Servicios_has_Platos, Integer>("codigoPlato"));
        colCodigoServicio.setCellValueFactory(new PropertyValueFactory<Servicios_has_Platos, Integer>("codigoServicio"));
    }
    
    public void cargarDatos() {
        tblServicios_has_Platos.setItems(getServicios_has_Platos());
    }
    
    public ObservableList<Servicios_has_Platos> getServicios_has_Platos() {
        ArrayList<Servicios_has_Platos> lista = new ArrayList<Servicios_has_Platos>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarServicios_has_Platos()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {                
                lista.add(new Servicios_has_Platos(resultado.getInt("Servicios_codigoServicio"),
                resultado.getInt("codigoPlato"),
                resultado.getInt("codigoServicio")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaServicios_has_Platos = FXCollections.observableArrayList(lista);
    }
    
    public Plato buscarPlato(int codigoPlato) {
        Plato resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarPlato(?)");
            procedimiento.setInt(1, codigoPlato);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {                
                resultado = new Plato(registro.getInt("codigoPlato"),
                registro.getInt("cantidad"),
                registro.getString("nombrePlato"),
                registro.getString("descripcionPlato"),
                registro.getDouble("precioPlato"),
                registro.getInt("codigoTipoPlato"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public Servicio buscarServicio(int codigoServicio) {
        Servicio resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarServicio(?)");
            procedimiento.setInt(1, codigoServicio);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {                
                resultado = new Servicio(registro.getInt("codigoServicio"),
                registro.getDate("fechaServicio"),
                registro.getString("tipoServicio"),
                registro.getTime("horaServicio"),
                registro.getString("lugarServicio"),
                registro.getString("telefonoContacto"),
                registro.getInt("codigoEmpresa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList<Plato> getPlato() {
        ArrayList<Plato> lista = new ArrayList<Plato>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarPlatos()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {                
                lista.add(new Plato(resultado.getInt("codigoPlato"),
                        resultado.getInt("cantidad"),
                        resultado.getString("nombrePlato"),
                        resultado.getString("descripcionPlato"),
                        resultado.getDouble("precioPlato"),
                        resultado.getInt("codigoTipoPlato")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPlato = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Servicio> getServicio() {
        ArrayList<Servicio> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarServicios()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {                
                lista.add(new Servicio(resultado.getInt("codigoServicio"),
                resultado.getDate("fechaServicio"),
                resultado.getString("tipoServicio"),
                resultado.getTime("horaServicio"),
                resultado.getString("lugarServicio"),
                resultado.getString("telefonoContacto"),
                resultado.getInt("codigoEmpresa")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaServicio = FXCollections.observableArrayList(lista);
    }
    
    public void seleccionarElemento() {
        if (tipoDeOperacion == operaciones.GUARDAR) {
            limpiarControles();
        } else {
            if (tblServicios_has_Platos.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un elemento existente");
            } else {
                txtServicios_codigoServicio.setText(String.valueOf(((Servicios_has_Platos) tblServicios_has_Platos.getSelectionModel().getSelectedItem()).getServicios_codigoServicio()));
                cmbCodigoPlato.getSelectionModel().select(buscarPlato(((Servicios_has_Platos) tblServicios_has_Platos.getSelectionModel().getSelectedItem()).getCodigoPlato()));
                cmbCodigoServicio.getSelectionModel().select(buscarServicio(((Servicios_has_Platos) tblServicios_has_Platos.getSelectionModel().getSelectedItem()).getCodigoServicio()));
            }
        }
    }
    
    public void nuevo() {
        switch(tipoDeOperacion) {
            case NINGUNO:
                limpiarControles();
                activarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnReporte.setDisable(true);
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/guardar.png"));
                imgEliminar.setImage(new Image("/org/sergiotepaz/image/cancelar.png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;
            
            case GUARDAR:
                if ((txtServicios_codigoServicio.getText() == null) || (cmbCodigoPlato.getValue() == "") || (cmbCodigoServicio.getValue() == "")) {
                    JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
                } else {
                    guardar();
                    limpiarControles();
                    desactivarControles();
                    btnNuevo.setText("Nuevo");
                    btnEliminar.setText("Eliminar");
                    btnReporte.setDisable(false);
                    imgNuevo.setImage(new Image("/org/sergiotepaz/image/agregar.png"));
                    imgEliminar.setImage(new Image("/org/sergiotepaz/image/eliminar.png"));
                    tipoDeOperacion = operaciones.NINGUNO;
                    cargarDatos();
                    break;
                }
        }
    }
    
    public void guardar() {
        Servicios_has_Platos registro = new Servicios_has_Platos();
        registro.setServicios_codigoServicio(Integer.parseInt(txtServicios_codigoServicio.getText()));
        registro.setCodigoPlato(((Plato) cmbCodigoPlato.getSelectionModel().getSelectedItem()).getCodigoPlato());
        registro.setCodigoServicio(((Servicio) cmbCodigoServicio.getSelectionModel().getSelectedItem()).getCodigoServicio());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarServicios_has_Platos(?,?,?)");
            procedimiento.setInt(1, registro.getServicios_codigoServicio());
            procedimiento.setInt(2, registro.getCodigoPlato());
            procedimiento.setInt(3, registro.getCodigoServicio());
            procedimiento.execute();
            listaServicios_has_Platos.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar() {
        switch (tipoDeOperacion) {
            case GUARDAR:
                limpiarControles();
                desactivarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/agregar.png"));
                imgEliminar.setImage(new Image("/org/sergiotepaz/image/eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
    
    public void desactivarControles() {
        txtServicios_codigoServicio.setEditable(false);
        cmbCodigoPlato.setDisable(true);
        cmbCodigoServicio.setDisable(true);
    }
    
    public void activarControles() {
        txtServicios_codigoServicio.setEditable(true);
        cmbCodigoPlato.setDisable(false);
        cmbCodigoServicio.setDisable(false);
    }
    
    public void limpiarControles() {
        txtServicios_codigoServicio.clear();
        cmbCodigoPlato.setValue("");
        cmbCodigoServicio.setValue("");
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void menuPrincipal() {
        escenarioPrincipal.menuPrincipal();
    }
}
