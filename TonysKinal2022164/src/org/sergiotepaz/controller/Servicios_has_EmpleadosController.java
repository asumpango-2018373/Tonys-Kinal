package org.sergiotepaz.controller;

import com.jfoenix.controls.JFXTimePicker;
import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.sergiotepaz.bean.Empleado;
import org.sergiotepaz.bean.Producto;
import org.sergiotepaz.bean.Servicio;
import org.sergiotepaz.bean.Servicios_has_Empleados;
import org.sergiotepaz.db.Conexion;
import org.sergiotepaz.main.Principal;
import org.sergiotepaz.report.GenerarReporte;

public class Servicios_has_EmpleadosController implements Initializable {
    
    private Principal escenarioPrincipal;
    private enum operaciones {
        NINGUNO, GUARDAR, ACTUALIZAR, ELIMINAR
    }
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Servicios_has_Empleados> listaServicios_has_Empleados;
    private ObservableList<Servicio> listaServicio;
    private ObservableList<Empleado> listaEmpleado;
    private DatePicker fecha;
    
    @FXML
    private TextField txtServicio_CodigoServicio;
    @FXML
    private TextField txtLugarEvento;
    @FXML
    private ComboBox cmbCodigoServicio;
    @FXML
    private ComboBox cmbCodigoEmpleado;
    @FXML
    private TableView tblServicios_has_Empleados;
    @FXML
    private TableColumn colServicio_CodigoServicio;
    @FXML
    private TableColumn colCodigoServicio;
    @FXML
    private TableColumn colCodigoEmpleado;
    @FXML
    private TableColumn colFechaEvento;
    @FXML
    private TableColumn colHoraEvento;
    @FXML
    private TableColumn colLugarEvento;    
    @FXML    
    private Button btnNuevo;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private Button btnEliminar;
    @FXML
    private ImageView imgEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private ImageView imgEditar;
    @FXML
    private Button btnReporte;
    @FXML
    private ImageView imgReporte;
    @FXML
    private GridPane grpFecha;
    @FXML
    private JFXTimePicker jfxHoraEvento;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fecha = new DatePicker(Locale.ENGLISH);
        fecha.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        fecha.getCalendarView().todayButtonTextProperty().set("Today");
        fecha.getCalendarView().setShowWeeks(false);
        fecha.getStylesheets().add("/org/sergiotepaz/resource/TonysKinal.css");
        grpFecha.add(fecha, 1, 1);
        cmbCodigoServicio.setItems(getServicio());
        cmbCodigoEmpleado.setItems(getEmpleado());
        cargarDatos();
        desactivarControles();
        colServicio_CodigoServicio.setCellValueFactory(new PropertyValueFactory<Servicios_has_Empleados, Integer>("Servicios_codigoServicio"));
        colCodigoServicio.setCellValueFactory(new PropertyValueFactory<Servicios_has_Empleados, Integer>("codigoServicio"));
        colCodigoEmpleado.setCellValueFactory(new PropertyValueFactory<Servicios_has_Empleados, Integer>("codigoEmpleado"));
        colFechaEvento.setCellValueFactory(new PropertyValueFactory<Servicios_has_Empleados, Date>("fechaEvento"));
        colHoraEvento.setCellValueFactory(new PropertyValueFactory<Servicios_has_Empleados, Time>("horaEvento"));
        colLugarEvento.setCellValueFactory(new PropertyValueFactory<Servicios_has_Empleados, String>("lugarEvento"));
    }

    public void cargarDatos() {
        tblServicios_has_Empleados.setItems(getServicios_has_Empleados());
    }
    
    public ObservableList<Servicios_has_Empleados> getServicios_has_Empleados() {
        ArrayList<Servicios_has_Empleados> lista = new ArrayList<Servicios_has_Empleados>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarServicios_has_Empleados()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {                
                lista.add(new Servicios_has_Empleados(resultado.getInt("Servicios_codigoServicio"),
                resultado.getInt("codigoServicio"),
                resultado.getInt("codigoEmpleado"),
                resultado.getDate("fechaEvento"),
                resultado.getTime("horaEvento"),
                resultado.getString("lugarEvento")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaServicios_has_Empleados = FXCollections.observableArrayList(lista);
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
    
    public Empleado buscarEmpleado(int codigoEmpleado) {
        Empleado resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarEmpleado(?)");
            procedimiento.setInt(1, codigoEmpleado);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {                
                resultado = new Empleado(registro.getInt("codigoEmpleado"),
                        registro.getInt("numeroEmpleado"),
                        registro.getString("apellidosEmpleado"),
                        registro.getString("nombresEmpleado"),
                        registro.getString("direccionEmpleado"),
                        registro.getString("telefonoContacto"),
                        registro.getString("gradoCocinero"),
                        registro.getInt("codigoTipoEmpleado"));
            }
        } catch (Exception e) {
        }
        return resultado;
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
    
    public ObservableList<Empleado> getEmpleado() {
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarEmpleados()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {                
                lista.add(new Empleado(resultado.getInt("codigoEmpleado"),
                        resultado.getInt("numeroEmpleado"),
                        resultado.getString("apellidosEmpleado"),
                        resultado.getString("nombresEmpleado"),
                        resultado.getString("direccionEmpleado"),
                        resultado.getString("telefonoContacto"),
                        resultado.getString("gradoCocinero"),
                        resultado.getInt("codigoTipoEmpleado")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpleado = FXCollections.observableArrayList(lista);
    }
    
    public void seleccionarElemento() {
        if (tipoDeOperacion == operaciones.GUARDAR) {
            limpiarControles();
        } else {
            if (tblServicios_has_Empleados.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un elemento existente");
            } else {
                txtServicio_CodigoServicio.setText(String.valueOf(((Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem()).getServicios_codigoServicio()));
                cmbCodigoServicio.getSelectionModel().select(buscarServicio(((Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem()).getCodigoServicio()));
                cmbCodigoEmpleado.getSelectionModel().select(buscarEmpleado(((Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado()));
                fecha.selectedDateProperty().set(((Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem()).getFechaEvento());
                jfxHoraEvento.setValue(LocalTime.parse(String.valueOf(((Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem()).getHoraEvento())));
                txtLugarEvento.setText(((Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem()).getLugarEvento());
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
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/guardar.png"));
                imgEliminar.setImage(new Image("/org/sergiotepaz/image/cancelar.png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;
                
            case GUARDAR:
                if ((txtServicio_CodigoServicio.getText() == null) || (cmbCodigoServicio.getValue() == "") || (cmbCodigoEmpleado.getValue() == "") || (fecha.getSelectedDate() == null) || (jfxHoraEvento.getValue() ==  null) || (txtLugarEvento.getText() == null)) {
                    JOptionPane.showMessageDialog(null, "Por favor llene todas las casillas");
                } else {
                    guardar();
                    limpiarControles();
                    desactivarControles();
                    btnNuevo.setText("Nuevo");
                    btnEliminar.setText("Eliminar");
                    btnEditar.setDisable(false);
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
        Servicios_has_Empleados registro = new Servicios_has_Empleados();
        registro.setServicios_codigoServicio(Integer.parseInt(txtServicio_CodigoServicio.getText()));
        registro.setCodigoServicio(((Servicio) cmbCodigoServicio.getSelectionModel().getSelectedItem()).getCodigoServicio());
        registro.setCodigoEmpleado(((Empleado) cmbCodigoEmpleado.getSelectionModel().getSelectedItem()).getCodigoEmpleado());
        registro.setFechaEvento(fecha.getSelectedDate());
        registro.setHoraEvento(java.sql.Time.valueOf(jfxHoraEvento.getValue()));
        registro.setLugarEvento(txtLugarEvento.getText());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarServicios_has_Empleados(?,?,?,?,?,?)");
            procedimiento.setInt(1, registro.getServicios_codigoServicio());
            procedimiento.setInt(2, registro.getCodigoServicio());
            procedimiento.setInt(3, registro.getCodigoEmpleado());
            procedimiento.setDate(4, new java.sql.Date(registro.getFechaEvento().getTime()));
            procedimiento.setTime(5, registro.getHoraEvento());
            procedimiento.setString(6, registro.getLugarEvento());
            procedimiento.execute();
            listaServicios_has_Empleados.add(registro);
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
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgNuevo.setImage(new Image("/org/sergiotepaz/image/agregar.png"));
                imgEliminar.setImage(new Image("/org/sergiotepaz/image/eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
        
        default:
            if (tblServicios_has_Empleados.getSelectionModel().getSelectedItem() != null) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el elemento?", "Eliminar Servicio_has_Empleado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    try {
                        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarServicios_has_Empleados(?)");
                        procedimiento.setInt(1, ((Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem()).getServicios_codigoServicio());
                        procedimiento.execute();
                        listaServicios_has_Empleados.remove(tblServicios_has_Empleados.getSelectionModel().getSelectedIndex());
                        limpiarControles();
                        tblServicios_has_Empleados.getSelectionModel().clearSelection();
                        cmbCodigoEmpleado.setValue("");
                        cmbCodigoServicio.setValue("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (respuesta == JOptionPane.NO_OPTION) {
                    limpiarControles();
                    tblServicios_has_Empleados.getSelectionModel().clearSelection();
                    cmbCodigoEmpleado.setValue("");
                    cmbCodigoServicio.setValue("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
            }
        }
    }
    
    public void editar() {
        switch (tipoDeOperacion) {
            case NINGUNO:
                if (tblServicios_has_Empleados.getSelectionModel().getSelectedItem() != null) {
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnEditar.setText("Guardar");
                    btnReporte.setText("Cancelar");
                    imgEditar.setImage(new Image("/org/sergiotepaz/image/guardar.png"));
                    imgReporte.setImage(new Image("/org/sergiotepaz/image/cancelar.png"));
                    activarControles();
                    cmbCodigoEmpleado.setDisable(true);
                    cmbCodigoServicio.setDisable(true);
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
                    break;
            
            case ACTUALIZAR:
                  actualizar();
                  desactivarControles();
                  limpiarControles();
                  cmbCodigoEmpleado.setValue("");
                  cmbCodigoServicio.setValue("");
                  btnNuevo.setDisable(false);
                  btnEliminar.setDisable(false);
                  btnEditar.setText("Editar");
                  btnReporte.setText("Reporte");
                  imgEditar.setImage(new Image("/org/sergiotepaz/image/editar.png"));
                  imgReporte.setImage(new Image("/org/sergiotepaz/image/reportar.png"));
                  cargarDatos();
                  tipoDeOperacion = operaciones.NINGUNO;
                  break;
        }
    }
    
    public void actualizar() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarServicios_has_Empleados(?,?,?,?)");
            Servicios_has_Empleados registro = (Servicios_has_Empleados) tblServicios_has_Empleados.getSelectionModel().getSelectedItem();
            registro.setFechaEvento(fecha.getSelectedDate());
            registro.setHoraEvento(java.sql.Time.valueOf(jfxHoraEvento.getValue()));
            registro.setLugarEvento(txtLugarEvento.getText());
            procedimiento.setInt(1, registro.getServicios_codigoServicio());
            procedimiento.setDate(2, new java.sql.Date(registro.getFechaEvento().getTime()));
            procedimiento.setTime(3, registro.getHoraEvento());
            procedimiento.setString(4, registro.getLugarEvento());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void reporte() {
        switch (tipoDeOperacion) {
            case NINGUNO:
                imprimirReporte();
                break;
            
            case ACTUALIZAR:
                limpiarControles();
                desactivarControles();
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/sergiotepaz/image/editar.png"));
                imgReporte.setImage(new Image("/org/sergiotepaz/image/reportar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                tblServicios_has_Empleados.getSelectionModel().clearSelection();
                cmbCodigoEmpleado.setValue("");
                cmbCodigoServicio.setValue("");
                break;
        }
    }
    
    public void imprimirReporte() {
        Map parametros = new HashMap();
        parametros.put("Servicios_codigoServicio", null);
        GenerarReporte.mostrarReporte("ReporteServicio_has_Empleados.jasper", "Reporte Servicio - has - Empleados", parametros);
    }
    
    public void desactivarControles() {
        txtServicio_CodigoServicio.setEditable(false);
        cmbCodigoServicio.setDisable(true);
        cmbCodigoEmpleado.setDisable(true);
        fecha.setDisable(true);
        jfxHoraEvento.setDisable(true);
        txtLugarEvento.setEditable(false);
    }
    
    public void activarControles() {
        txtServicio_CodigoServicio.setEditable(true);
        cmbCodigoServicio.setDisable(false);
        cmbCodigoEmpleado.setDisable(false);
        fecha.setDisable(false);
        jfxHoraEvento.setDisable(false);
        txtLugarEvento.setEditable(true);
    }
    
    public void limpiarControles() {
        txtServicio_CodigoServicio.clear();
        cmbCodigoServicio.setValue("");
        cmbCodigoEmpleado.setValue("");
        fecha.selectedDateProperty().set(null);
        jfxHoraEvento.setValue(null);
        txtLugarEvento.clear();
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
