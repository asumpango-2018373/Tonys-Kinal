/*
	Sergio Eduardo Tepaz Vela
    2022164
    IN5AV
    Fecha de creación 
		28/03/2023
	Fechas de modificaciones
		28/03/2023
*/

drop database if exists DBTonysKinal2023;
create database DBTonysKinal2023;

use DBTonysKinal2023;

create table Empresas (
	codigoEmpresa int not null auto_increment,
    nombreEmpresa varchar(150) not null,
    direccion varchar (150) not null,
    telefono varchar (8) not null,
    primary key PK_codigoEmpresa (codigoEmpresa)
);

create table TipoEmpleado (
	codigoTipoEmpleado int not null auto_increment,
    descripcion varchar (150),
	primary key PK_codigoTipoEmpleado (codigoTipoEmpleado)
);

create table TipoPlato(
	codigoTipoPlato int not null auto_increment,
    descripcion varchar (150) not null,
    primary key PK_codigoTipoPlato (codigoTipoPlato)
);

create table Productos(
	codigoProducto int not null auto_increment,
    nombreProducto varchar(150) not null,
    cantidadProducto int not null,
    primary key PK_codigoProducto (codigoProducto)
);

create table Empleados(
	codigoEmpleado int not null auto_increment,
    numeroEmpleado int not null,
	apellidosEmpleado varchar(150) not null,
    nombresEmpleado varchar(150) not null,
    direccionEmpleado varchar(150) not null,
    telefonoContacto varchar(8) not null,
    gradoCocinero varchar(50) not null,
    codigoTipoEmpleado int not null,
    primary key PK_codigoEmpleado (codigoEmpleado),
    constraint FK_Empleados_TipoEmpleado foreign key
		(codigoTipoEmpleado) references TipoEmpleado (codigoTipoEmpleado)
);

create table Servicios(
	codigoServicio int not null auto_increment,
    fechaServicio date not null,
    tipoServicio varchar(150) not null,
    horaServicio time not null,
    lugarServicio varchar (150) not null,
    telefonoContacto varchar(150),
    codigoEmpresa int not null,
	primary key PK_codigoServicio (codigoServicio),
    constraint FK_Servicios_Empresas foreign key 
		(codigoEmpresa) references Empresas (codigoEmpresa) 
);

create table Presupuesto(
	codigoPresupuesto int not null auto_increment,
    fechaSolicitud date not null,
    cantidadPresupuesto decimal(10,2) not null,
    codigoEmpresa int not null,
    primary key FK_codigoPresupuesto (codigoPresupuesto),
    constraint FK_Presupuesto_Empresas foreign key (codigoEmpresa) 
		references Empresas (codigoEmpresa)
);

create table Platos(
	codigoPlato int not null auto_increment,
    cantidad int not null,
    nombrePlato varchar(50) not null,
    descripcionPlato varchar(150) not null,
    precioPlato decimal (10,2) not null,
    codigoTipoPlato int not null,
    primary key FK_codigoPlato (codigoPlato),
    constraint FK_Platos_TipoPlato foreign key (codigoTipoPlato)
		references TipoPlato (codigoTipoPlato)
);

create table Productos_has_Platos(
	Productos_codigoProducto int not null,
    codigoPlato int not null,
    codigoProducto int not null,
    primary key PK_Productos_codigoProducto (Productos_codigoProducto),
    constraint FK_Productos_has_Platos_Platos foreign key (codigoPlato)
		references Platos (codigoPlato),
    constraint FK_Productos_has_Platos_Productos foreign key (codigoProducto)
		references Productos (codigoProducto)
);

create table Servicios_has_Platos(
	Servicios_codigoServicio int not null,
    codigoPlato int not null,
    codigoServicio int not null,
    primary key PK_Servicios_codigoServicio (Servicios_codigoServicio),
    constraint FK_Servicios_has_Platos_Servicios foreign key (codigoServicio)
		references Servicios (codigoServicio),
	constraint FK_FK_Servicios_has_Platos_Platos foreign key (codigoPlato)
		references Platos (codigoPlato)
);

create table Servicios_has_Empleados(
	Servicios_codigoServicio int not null,
    codigoServicio int not null,
    codigoEmpleado int not null,
    fechaEvento date not null,
    horaEvento time not null,
    lugarEvento varchar(150) not null,
    primary key PK_Servicios_codigoServicio (Servicios_codigoServicio),
    constraint FK_Servicios_has_Empleados_Servicios foreign key (codigoServicio)
		references Servicios (codigoServicio),
	constraint FK_Servicios_has_Empleados_Empleados foreign key (codigoEmpleado)
		references Empleados (codigoEmpleado)
);

create table Usuario(
	codigoUsuario int not null auto_increment,
    nombreUsuario varchar(100) not null,
    apellidoUsuario varchar(100) not null,
    usuarioLogin varchar(50) not null,
    contrasena varchar(50) not null,
    primary key PK_codigoUsuario (codigoUsuario)
);

create table Login(
	usuarioMaster varchar(50) not null,
    passwordLogin varchar(50) not null,
    primary key PK_usuarioMaster (usuarioMaster)
);


-- ----------------------------- Procedimientos Almacenados -----------------------------

-- ---------------- Empresas ----------------

-- Agregar Empresa
DELIMITER //
	create procedure sp_AgregarEmpresa (in nombreEmpresa varchar(150), direccion varchar(150), telefono varchar(8))
		begin
			insert into Empresas (nombreEmpresa, direccion, telefono)
				values (nombreEmpresa, direccion, telefono);
        end//
DELIMITER ;
call sp_AgregarEmpresa('Kinal','6a Avenida 13-54', '23877600');
call sp_AgregarEmpresa('Pepsi', 'Calzada Roosvelt 14-98','12345678');
call sp_AgregarEmpresa('Coca Cola', 'Calzada San Juan 26-76', '43562345');
call sp_AgregarEmpresa('Claro', '5ta calle 23-56', '34768723');
call sp_AgregarEmpresa('Tigo', '6ta calle 12-36', '23753698');
call sp_AgregarEmpresa('Cementos Progreso', '15 Avenida 18-01', '54672387');
call sp_AgregarEmpresa('Banco Promerica', '6a. avenida 0-35 zona 4', '76345626');
call sp_AgregarEmpresa('Banrural', 'Av Reforma 9-30 Z-9', '64578623');
call sp_AgregarEmpresa('Columbia', '27 Av. 6-40', '70341534');
call sp_AgregarEmpresa('Bantrab', 'Avenida Reforma 6-20 Zona 9', '52344323');

-- Editar Empresa
DELIMITER //
	create procedure sp_EditarEmpresa( in codEmpresa int, nomEmpresa varchar(150), dir varchar(150), tel varchar(8))
		begin
			update Empresas E
				set E.nombreEmpresa = nomEmpresa, E.direccion = dir, E.telefono = tel
					where E.codigoEmpresa = codEmpresa;
        end//
DELIMITER ;
-- call sp_EditarEmpresa(1,'Coca Cola','Calzada Roosvelt 14-98','12345678');

-- Eliminar Empresa
DELIMITER //
	create procedure sp_EliminarEmpresa(in codEmpresa int)
		begin
			delete from Empresas 
				where codigoEmpresa = codEmpresa;
        end//
DELIMITER ;
-- call sp_EliminarEmpresa(1);

-- Listar Empresas
DELIMITER //
	create procedure sp_ListarEmpresas()
		begin
			Select
				E.codigoEmpresa, 
                E.nombreEmpresa, 
                E.direccion, 
                E.telefono
                from Empresas E;
        end//
DELIMITER ;
-- call sp_ListarEmpresas();

-- Buscar Empresa
DELIMITER //
	create procedure sp_BuscarEmpresa(in codEmpresa int)
		begin
			Select
				E.codigoEmpresa, 
                E.nombreEmpresa, 
                E.direccion, 
                E.telefono
                from Empresas E
					where E.codigoEmpresa = codEmpresa;
        end//
DELIMITER ;

-- ---------------- Tipo Empleado ----------------

-- Agregar TipoEmpleado
DELIMITER //
	create procedure sp_AgregarTipoEmpleado(in descripcion varchar (150))
		begin
			insert into TipoEmpleado (descripcion)
				values (descripcion);
        end//
DELIMITER ;
call sp_AgregarTipoEmpleado('Cocinero');
call sp_AgregarTipoEmpleado('Jefe de marketing');
call sp_AgregarTipoEmpleado('Programador');
call sp_AgregarTipoEmpleado('Cocinero');
call sp_AgregarTipoEmpleado('Director Ejecutivo');
call sp_AgregarTipoEmpleado('Ejecutivo de ventas');
call sp_AgregarTipoEmpleado('Secreteria');
call sp_AgregarTipoEmpleado('Conductor');
call sp_AgregarTipoEmpleado('Supervisor');
call sp_AgregarTipoEmpleado('Mesero');

-- Editar TipoEmpleado
DELIMITER //
	create procedure sp_EditarTipoEmpleado(in codTipoEmpleado int, in descri varchar(150))
		begin
			update TipoEmpleado TE
				set TE.descripcion = descri
					where TE.codigoTipoEmpleado = codTipoEmpleado;
        end//
DELIMITER ;
-- call sp_EditarTipoEmpleado(1,'Cocinero');

-- Eliminar TipoEmpleado
DELIMITER //
	create procedure sp_EliminarTipoEmpleado(in codTipoEmpleado int)
		begin
			delete from TipoEmpleado 
				where codigoTipoEmpleado = codTipoEmpleado;
        end//
DELIMITER ;
-- call sp_EliminarTipoEmpleado(1);

-- Listar TipoEmpleado
DELIMITER //
	create procedure sp_ListarTipoEmpleado()
		begin
			Select
				TE.codigoTipoEmpleado, 
                TE.descripcion
                from TipoEmpleado TE;
        end//
DELIMITER ;
-- call sp_ListarTipoEmpleado();

-- Buscar TipoEmpleado
DELIMITER //
	create procedure sp_BuscarTipoEmpleado(in codTipoEmpleado int)
		begin
			Select
				TE.codigoTipoEmpleado, 
                TE.descripcion
                from TipoEmpleado TE
					where TE.codigoTipoEmpleado = codTipoEmpleado;
        end//
DELIMITER ;

-- ---------------- Tipo Plato ----------------

-- Agregar TipoPlato
DELIMITER //
	create procedure sp_AgregarTipoPlato(in descripcion varchar (150))
		begin
			insert into TipoPlato (descripcion)
				values (descripcion);
        end//
DELIMITER ;
call sp_AgregarTipoPlato('Almuerzo');
call sp_AgregarTipoPlato('Entrada');
call sp_AgregarTipoPlato('Plato Principal');
call sp_AgregarTipoPlato('Postre');
call sp_AgregarTipoPlato('Bebida');
call sp_AgregarTipoPlato('Complemento');
call sp_AgregarTipoPlato('Aderezo');
call sp_AgregarTipoPlato('Snack');
call sp_AgregarTipoPlato('Tipico');
call sp_AgregarTipoPlato('Desayuno');

-- Editar TipoPlato
DELIMITER //
	create procedure sp_EditarTipoPlato(in codTipoPlato int, in descri varchar(150))
		begin
			update TipoPlato TP
				set TP.descripcion = descri
					where TP.codigoTipoPlato = codTipoPlato;
        end//
DELIMITER ;
-- call sp_EditarTipoPlato(1,'Italiano');

-- Eliminar TipoPlato
DELIMITER //
	create procedure sp_EliminarTipoPlato(in codTipoPlato int)
		begin
			delete from TipoPlato
				where codigoTipoPlato = codTipoPlato;
        end//
DELIMITER ;
-- call sp_EliminarTipoPlato(1);

-- Listar TipoPlato
DELIMITER //
	create procedure sp_ListarTipoPlato()
		begin
			Select
				TP.codigoTipoPlato, 
                TP.descripcion
                from TipoPlato TP;
        end//
DELIMITER ;
-- call sp_ListarTipoPlato();

-- Buscar TipoPlato
DELIMITER //
	create procedure sp_BuscarTipoPlato(in codTipoPlato int)
		begin
			Select
				TP.codigoTipoPlato, 
                TP.descripcion
                from TipoPlato TP
					where TP.codigoTipoPlato = codTipoPlato;
        end//
DELIMITER ;

-- ---------------- Productos ----------------

-- Agregar Producto
DELIMITER //
	create procedure sp_AgregarProducto(in nombreProducto varchar(150), in cantidadProducto int)
		begin
			insert into Productos (nombreProducto, cantidadProducto)
				values (nombreProducto, cantidadProducto);
        end//
DELIMITER ;
call sp_AgregarProducto('Mesa', 20);
call sp_AgregarProducto('Silla', 100);
call sp_AgregarProducto('Toldo', 10);
call sp_AgregarProducto('Plato', 500);
call sp_AgregarProducto('Vaso', 1000);
call sp_AgregarProducto('Cuchara', 2000);
call sp_AgregarProducto('Tenedor', 2000);
call sp_AgregarProducto('Servilleta', 150);
call sp_AgregarProducto('Cuchillo', 2000);
call sp_AgregarProducto('Vehiculo', 10);

-- Editar Producto
DELIMITER //
	create procedure sp_EditarProducto(in codProducto int ,nombre varchar(150), in cant int)
		begin
			update Productos P 
				set P.nombreProducto = nombre, P.cantidadProducto = cant
					where P.codigoProducto = codProducto;
        end//
DELIMITER ;
-- call sp_EditarProducto(1, 'Silla', 10);

-- Eliminar Producto
DELIMITER //
	create procedure sp_EliminarProducto(in codProducto int)
		begin
			delete from Productos
				where codigoProducto = codProducto;
        end//
DELIMITER ;
-- call sp_EliminarProducto(1);

-- Listar Productos
DELIMITER //
	create procedure sp_ListarProductos()
		begin
			select
				   P.codigoProducto, 
				   P.nombreProducto, 
                   P.cantidadProducto
				   from Productos P;
        end//
DELIMITER ;
call sp_ListarProductos();

DELIMITER //
	create procedure sp_BuscarProducto(in codProducto int)
		begin
			select
				   P.codigoProducto, 
				   P.nombreProducto, 
                   P.cantidadProducto
				   from Productos P
						where P.codigoProducto = codProducto;
        end//
DELIMITER ;

-- ---------------- Empleados ----------------

-- Agregar Empleado
DELIMITER //
	create procedure sp_AgregarEmpleado (in numeroEmpleado int, apellidosEmpleado varchar (150), nombresEmpleado varchar(150), direccionEmpleado varchar (150), 
		telefonoContacto varchar(8), gradoCocinero varchar(50), codigoTipoEmpleado int)
		begin
			insert into Empleados (numeroEmpleado, apellidosEmpleado, nombresEmpleado, direccionEmpleado, telefonoContacto, gradoCocinero, codigoTipoEmpleado)
				values (numeroEmpleado, apellidosEmpleado, nombresEmpleado, direccionEmpleado, telefonoContacto, gradoCocinero, codigoTipoEmpleado);
        end//
DELIMITER ;
call sp_AgregarEmpleado (1,'Tepaz Vela','Sergio Eduardo','6ta calle 12-36','40605562','Jefe de cocina',1);
call sp_AgregarEmpleado (2,'Rodriguez Melgar','Claudio Alejandro','6a Avenida 1-17 Zona 10','84673251','Chef ejecutivo',1);
call sp_AgregarEmpleado (2,'Lopez Perez','Juan Fernando','5a Calle 3-36 Zona 1','22320000','Asistente de chef',1);
call sp_AgregarEmpleado (2,'Perez Lopez','Oscar Alberto','7a Avenida 11-11 Zona 9','21549870','Chef Partie',1);
call sp_AgregarEmpleado (2,'Morales Garcia','Juan Jose','4a Calle 7-46 Zona 1','73921684','Primer Cocinero',1);
call sp_AgregarEmpleado (2,'Ruiz Dominguez','Carlos Alberto','12 Calle 2-25 Zona 10','50382756','Segundo Cocinero',2);
call sp_AgregarEmpleado (2,'Alvarez Samayoa','Juan Roberto','4a Avenida A 16-20 Zona 14','96204138','Tercer Cocinero',1);
call sp_AgregarEmpleado (2,'Alvarez Salazar','Francisco Fernando','13 Calle A 10-30 Zona 1','37451982','Aprendiz de chef',1);
call sp_AgregarEmpleado (2,'Vasquez Garcia','Rodrigo Alejandro','3a Calle Antigua Guatemala','62847390','Aprendiz de chef',1);
call sp_AgregarEmpleado (2,'Lopez Cabrera','Daniel Antonio','Calle del Arco Antigua Guatemala','18573926','Aprendiz de chef',1);

-- Editar Empelado
DELIMITER //
	create procedure sp_EditarEmpleado (in codEmpleado int,in numEmpleado int, apellidos varchar (150), nombres varchar(150), direccion varchar (150), 
		telefono varchar(8), grado varchar(50))
        begin
			update Empleados E 
				set E.numeroEmpleado = numEmpleado, E.apellidosEmpleado = apellidos, E.nombresEmpleado = nombres, E.direccionEmpleado = direccion,
					E.telefonoContacto = telefono, E.gradoCocinero = grado
						where codigoEmpleado = codEmpleado;
        end//
DELIMITER ;
-- call sp_EditarEmpleado (1,2,'Tepaz Vela','Sergio Eduardo','6ta calle 12-36','40605562','Jefe de cocina');

-- Eliminar Empleado
DELIMITER //
	create procedure sp_EliminarEmpleado (in codEmpleado int)
		begin
			delete from Empleados
				where codigoEmpleado = codEmpleado;
        end//
DELIMITER ;
-- call sp_EliminarEmpleado (1);

-- Listar Empleados
DELIMITER //
	create procedure sp_ListarEmpleados()
		begin
			select	
					E.codigoEmpleado, 
					E.numeroEmpleado, 
                    E.apellidosEmpleado, 
                    E.nombresEmpleado, 
                    E.direccionEmpleado, 
                    E.telefonoContacto, 
                    E.gradoCocinero, 
                    E.codigoTipoEmpleado
					from Empleados E;
        end//
DELIMITER ;

-- Buscar Empleado
DELIMITER //
	create procedure sp_BuscarEmpleado(in codEmpleado int)
		begin
			select	
					E.codigoEmpleado, 
					E.numeroEmpleado, 
                    E.apellidosEmpleado, 
                    E.nombresEmpleado, 
                    E.direccionEmpleado, 
                    E.telefonoContacto, 
                    E.gradoCocinero, 
                    E.codigoTipoEmpleado
					from Empleados E
						where E.codigoEmpleado = codEmpleado;
        end//
DELIMITER ;

-- ---------------- Servicios ----------------

-- Agregar Servicio
DELIMITER //
	create procedure sp_AgregarServicio(in fechaServicio date, tipoServicio varchar(150), horaServicio time, 
		 lugarServicio varchar(150), telefonoContacto varchar(150), codigoEmpresa int)
        begin
			insert into Servicios (fechaServicio, tipoServicio, horaServicio, lugarServicio, telefonoContacto, codigoEmpresa)
				values(fechaServicio, tipoServicio, horaServicio, lugarServicio, telefonoContacto, codigoEmpresa);
        end//
DELIMITER ;
call sp_AgregarServicio ("2023-09-04",'Buffete',curtime(5),'Jardin Las Flores','13468978',1);
call sp_AgregarServicio ("2023-12-12",'Convivio','21:00:00','Pecorino','13468978',2);
call sp_AgregarServicio ("2023-09-14",'Estandar','17:00:00','TerraEsperanza','12345678',3);
call sp_AgregarServicio ("2023-07-07",'Estandar','20:00:00','Saul Bistro','87654321',4);
call sp_AgregarServicio ("2023-10-08",'Buffete','14:00:00','Casa Ariana','23456789',5);
call sp_AgregarServicio ("2023-06-24",'Estandar','19:30:00','Finca Shangrila','56789012',6);
call sp_AgregarServicio ("2023-07-23",'Buffete','18:00:00','Trefra Group','98701234',7);
call sp_AgregarServicio ("2023-07-21",'Estandar','22:00:00','Teatro Abril','34567890',8);
call sp_AgregarServicio ("2023-11-12",'Convivio','21:00:00','Florencia','89012345',9);
call sp_AgregarServicio ("2023-10-12",'Convivio','20:00:00','Jardin Las Flores','45678901',10);

-- Editar Servicio
DELIMITER //
	create procedure sp_EditarServicio(in codServicio int, fecha date, tipo varchar(150), hora time,
		 lugar varchar(150), telefono varchar(150))
         begin
			update Servicios S
				set S.fechaServicio = fecha, S.tipoServicio = tipo, S.horaServicio = hora, S.lugarServicio = lugar, 
					S.telefonoContacto = telefono
						where S.codigoServicio = codServicio;
         end//
DELIMITER ;
-- call sp_EditarServicio(1,"2023-09-04",'Comida',curtime(5),'Jardin Las Rosas','23564549');

-- Eliminar Servicio
DELIMITER //
	create procedure sp_EliminarServicio(in codServicio int)
		begin
			delete from Servicios
				where codigoServicio = codServicio;
        end//
DELIMITER ;
-- call sp_EliminarServicio(1);

-- Listar Servicios
DELIMITER //
	create procedure sp_ListarServicios()
		begin
			select
				S.codigoServicio, 
                S.fechaServicio, 
                S.tipoServicio, 
                S.horaServicio, 
                S.lugarServicio, 
                S.telefonoContacto, 
                S.codigoEmpresa
				from Servicios S;
        end//
DELIMITER ;
-- call sp_ListarServicios();

-- Buscar Servicio
DELIMITER //
	create procedure sp_BuscarServicio(in codServicio int)
		begin
			select
				S.codigoServicio, 
                S.fechaServicio, 
                S.tipoServicio, 
                S.horaServicio, 
                S.lugarServicio, 
                S.telefonoContacto, 
                S.codigoEmpresa
				from Servicios S
					where S.codigoServicio = codServicio;
        end//
DELIMITER ;

-- ---------------- Presupuesto ----------------

-- Agregar Presupuesto
DELIMITER //
	create procedure sp_AgregarPresupuesto(in fechaSolicitud date, cantidadPresupuesto decimal(10,2), codigoEmpresa int)
		begin
			insert into Presupuesto (fechaSolicitud, cantidadPresupuesto, codigoEmpresa)
				values(fechaSolicitud, cantidadPresupuesto, codigoEmpresa);
        end//
DELIMITER ;
call sp_AgregarPresupuesto("2023-03-30",5000,1);
call sp_AgregarPresupuesto("2023-12-05",10000,2);
call sp_AgregarPresupuesto("2023-04-23",7000,3);
call sp_AgregarPresupuesto("2023-07-05",15000,4);
call sp_AgregarPresupuesto("2023-03-25",20000,5);
call sp_AgregarPresupuesto("2023-12-04",15000,6);
call sp_AgregarPresupuesto("2023-02-22",17000,7);
call sp_AgregarPresupuesto("2023-04-22",19000,8);
call sp_AgregarPresupuesto("2023-11-05",12000,9);
call sp_AgregarPresupuesto("2023-02-23",10000,10);

-- Editar Presupuesto
DELIMITER //
	create procedure sp_EditarPresupuesto(in codPresupuesto int, fecha date, cantidad decimal(10,2))
		begin
			update Presupuesto P
				set P.fechaSolicitud = fecha, P.cantidadPresupuesto = cantidad
					where P.codigoPresupuesto = codPresupuesto;
        end//
DELIMITER ;
-- call sp_EditarPresupuesto(2,"2023-04-30", 5000);

-- Eliminar Presupuesto
DELIMITER //
	create procedure sp_EliminarPresupuesto(in codPresupuesto int)
		begin
			delete from Presupuesto
				where codigoPresupuesto = codPresupuesto;
        end//
DELIMITER ;
-- call sp_EliminarPresupuesto(4);

-- Listar Presupuestos
DELIMITER //
	create procedure sp_ListarPresupuestos()
		begin
			select
				P.codigoPresupuesto, 
				P.fechaSolicitud, 
				P.cantidadPresupuesto, 
				P.codigoEmpresa
				from Presupuesto P;
		end//
DELIMITER ;
-- call sp_ListarPresupuestos();

-- Buscar Presupuestos
DELIMITER //
	create procedure sp_BuscarPresupuesto(in codPresupuesto int)
		begin
			select
				P.codigoPresupuesto, 
				P.fechaSolicitud, 
				P.cantidadPresupuesto, 
				P.codigoEmpresa
				from Presupuesto P
					where P.codigoPresupuesto = codPresupuesto;
		end//
DELIMITER ;

-- ---------------- Platos ----------------

-- Agregar Plato
DELIMITER //
	create procedure sp_AgregarPlato(in cantidad int, nombrePlato varchar(50), descripcionPlato varchar(150), 
		precioPlato decimal(10,2), codigoTipoPlato int)
        begin
			insert into Platos(cantidad, nombrePlato, descripcionPlato, precioPlato, codigoTipoPlato)
				values (cantidad, nombrePlato, descripcionPlato, precioPlato, codigoTipoPlato);
        end//
DELIMITER ;
call sp_AgregarPlato(30,'Hamburguesa','Hamburguesa sencilla con queso',25,8);
call sp_AgregarPlato(10,'Paella','Plato tradicional hecho con arroz, mariscos y pollo',150,1);
call sp_AgregarPlato(20,'Sushi','Arroz de sushi combinado con pescado fresco',50,3);
call sp_AgregarPlato(100,'Tacos al pastor','Tortillas de maiz rellenas de carne de cerdo',15,2);
call sp_AgregarPlato(13,'Moussaka','capas de berenjenas, carne picada, tomate y bechamel.',200,1);
call sp_AgregarPlato(50,'Atol de elote','bebida espesa y dulce hecha con maiz tierno, leche, azucar',10,5);
call sp_AgregarPlato(50,'Rellenitos','bolas de masa de platano rellenas de frijoles dulces o frijoles negros',10,4);
call sp_AgregarPlato(15,'Pepian','guiso espeso y sabroso hecho con carne',70,1);
call sp_AgregarPlato(25,'Chiles rellenos','rellenos de carne molida o queso',10,2);
call sp_AgregarPlato(10,'Jocon de pollo','guiso de pollo cocinado en una salsa verde hecha de tomate',75,1);

-- Editar Plato
DELIMITER //
	create procedure sp_EditarPlato(in codPlato int, cant int, nombre varchar(50), descripcion varchar(150), 
		precio decimal(10,2))
        begin
			update Platos P 
				set P.cantidad = cant, P.nombrePlato = nombre, P.descripcionPlato = descripcion, 
					P.precioPlato = precio
						where P.codigoPlato = codPlato;
        end//
DELIMITER ;
-- call sp_EditarPlato(1,10,'Hamburguesa','Hamburguesa sencilla',30);

-- Eliminar Plato
DELIMITER //
	create procedure sp_EliminarPlato(in codPlato int)
		begin
			delete from Platos
				where codigoPlato = codPlato;
        end//
DELIMITER ;
-- call sp_EliminarPlato(1);

-- Listar Platos
DELIMITER //
	create procedure sp_ListarPlatos()
		begin
			select 
				P.codigoPlato, 
                P.cantidad, 
                P.nombrePlato, 
                P.descripcionPlato, 
                P.precioPlato, 
                P.codigoTipoPlato
				from Platos P;
        end//
DELIMITER ;
-- call sp_ListarPlatos();

-- Buscar Plato
DELIMITER //
	create procedure sp_BuscarPlato(in codPlato int)
		begin
			select 
				P.codigoPlato, 
                P.cantidad, 
                P.nombrePlato, 
                P.descripcionPlato, 
                P.precioPlato, 
                P.codigoTipoPlato
				from Platos P
					where P.codigoPlato = codPlato;
        end//
DELIMITER ;
-- call sp_BuscarPlato(1);

-- ---------------- Productos_has_Platos ----------------

-- Agregar Productos_has_Platos
DELIMITER //
	create procedure sp_AgregarProductos_has_Platos(in Productos_codigoProducto int,codigoPlato int, codigoProducto int)
		begin
			insert into Productos_has_Platos(Productos_codigoProducto,codigoPlato, codigoProducto)
				values (Productos_codigoProducto,codigoPlato, codigoProducto);
        end//
DELIMITER ;
call sp_AgregarProductos_has_Platos(1,1,1);
call sp_AgregarProductos_has_Platos(2,2,2);
call sp_AgregarProductos_has_Platos(3,3,3);
call sp_AgregarProductos_has_Platos(4,4,4);
call sp_AgregarProductos_has_Platos(5,5,5);
call sp_AgregarProductos_has_Platos(6,6,6);
call sp_AgregarProductos_has_Platos(7,7,7);
call sp_AgregarProductos_has_Platos(8,8,8);
call sp_AgregarProductos_has_Platos(9,9,9);
call sp_AgregarProductos_has_Platos(10,10,10);

-- Editar Productos_has_Platos
DELIMITER //
	create procedure sp_EditarProductos_has_Platos(in Productos_codProducto int)
		begin
			update Productos_has_Platos PHP
				set PHP.Productos_codigoProducto = Productos_codProducto
					where PHP.Productos_codigoProducto = Productos_codProducto;
        end//
DELIMITER ;

-- Eliminar Productos_has_Platos
DELIMITER //
	create procedure sp_EliminarProductos_has_Platos(in Productos_codProducto int)
		begin
			delete from Productos_has_Platos
				where Productos_codigoProducto = Productos_codProducto;
        end//
DELIMITER ;

-- Listar Productos_has_Platos
DELIMITER //
	create procedure sp_ListarProductos_has_Platos()
		begin
			select
				PHP.Productos_codigoProducto, 
                PHP.codigoPlato, 
                PHP.codigoProducto
                from Productos_has_Platos PHP;
        end//
DELIMITER ;

-- Buscar Productos_has_Platos
DELIMITER //
	create procedure sp_BuscarProductos_has_Platos(in Productos_codProducto int)
		begin
			select
				PHP.Productos_codigoProducto, 
                PHP.codigoPlato, 
                PHP.codigoProducto
                from Productos_has_Platos PHP
					where PHP.Productos_codigoProducto = Productos_codProducto;
        end//
DELIMITER ;

-- ---------------- Servicios_has_Platos ----------------

-- Agregar Servicios_has_Platos
DELIMITER //
	create procedure sp_AgregarServicios_has_Platos(in Servicios_codigoServicio int, codigoPlato int, codigoServicio int)
		begin
			insert into Servicios_has_Platos (Servicios_codigoServicio, codigoPlato, codigoServicio)
				values (Servicios_codigoServicio, codigoPlato, codigoServicio);
        end//
DELIMiTER ;
call sp_AgregarServicios_has_Platos(1,1,1);
call sp_AgregarServicios_has_Platos(2,2,2);
call sp_AgregarServicios_has_Platos(3,3,3);
call sp_AgregarServicios_has_Platos(4,4,4);
call sp_AgregarServicios_has_Platos(5,5,5);
call sp_AgregarServicios_has_Platos(6,6,6);
call sp_AgregarServicios_has_Platos(7,7,7);
call sp_AgregarServicios_has_Platos(8,8,8);
call sp_AgregarServicios_has_Platos(9,9,9);
call sp_AgregarServicios_has_Platos(10,10,10);

-- Editar Servicios_has_Platos
DELIMITER //
	create procedure sp_EditarServicios_has_Platos(in Servicios_codServicio int)
		begin
			update Servicios_has_Platos SHP
				set SHP.Servicios_codigoServicio = Servicios_codServicio
					where SHP.Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;

-- Eliminar Servicios_has_Platos
DELIMITER //
	create procedure sp_EliminarServicios_has_Platos(in Servicios_codServicio int)
		begin
			delete from Servicios_has_Platos
				where Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;

-- Listar Servicios_has_Platos
DELIMITER //
	create procedure sp_ListarServicios_has_Platos()
		begin
			select
				SHP.Servicios_codigoServicio, 
                SHP.codigoPlato, 
                SHP.codigoServicio
                from Servicios_has_Platos SHP;
        end//
DELIMITER ;

-- Buscar Servicios_has_Platos
DELIMITER //
	create procedure sp_BuscarServicios_has_Platos(in Servicios_codServicio int)
		begin
			select
				SHP.Servicios_codigoServicio, 
                SHP.codigoPlato, 
                SHP.codigoServicio
                from Servicios_has_Platos SHP
					where SHP.Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;

-- ---------------- Servicios_has_Empleados ----------------

-- Agregar Servicios_has_Empleados
DELIMITER //
	create procedure sp_AgregarServicios_has_Empleados(in Servicios_codigoServicio int, codigoServicio int, codigoEmpleado int, 
		fechaEvento date, horaEvento time, lugarEvento varchar(150))
        begin
			insert into Servicios_has_Empleados(Servicios_codigoServicio, codigoServicio, codigoEmpleado, fechaEvento, horaEvento, lugarEvento)
				values (Servicios_codigoServicio, codigoServicio, codigoEmpleado, fechaEvento, horaEvento, lugarEvento);
        end//
DELIMITER ;
call sp_AgregarServicios_has_Empleados(1,1,1,"2023-04-10","13:00:00",'Jardines las flores');
call sp_AgregarServicios_has_Empleados(2,2,2,"2023-12-12","21:00:00",'Pecorino');
call sp_AgregarServicios_has_Empleados(3,3,3,"2023-09-14","17:00:00",'TerraEsperanza');
call sp_AgregarServicios_has_Empleados(4,4,4,"2023-07-07","20:00:00",'Saul Bistro');
call sp_AgregarServicios_has_Empleados(5,5,5,"2023-10-08","14:00:00",'Casa Ariana');
call sp_AgregarServicios_has_Empleados(6,6,6,"2023-06-24","19:30:00",'Finca Shangrila');
call sp_AgregarServicios_has_Empleados(7,7,7,"2023-07-23","18:00:00",'Trefra Group');
call sp_AgregarServicios_has_Empleados(8,8,8,"2023-07-21","22:00:00",'Teatro Abril');
call sp_AgregarServicios_has_Empleados(9,9,9,"2023-11-12","21:00:00",'Florencia');
call sp_AgregarServicios_has_Empleados(10,10,10,"2023-10-12","20:00:00",'Jardin Las Flores');

-- Editar Servicios_has_Empleados
DELIMITER //
	create procedure sp_EditarServicios_has_Empleados(in Servicios_codServicio int, fecha date, hora time, lugar varchar(150))
        begin
			update Servicios_has_Empleados SHE
				set SHE.fechaEvento = fecha, SHE.horaEvento = hora, SHE.lugarEvento = lugar
						where SHE.Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;
-- call sp_EditarServicios_has_Empleados(1,"2023-04-09","14:00:00",'Jardines');

-- Eliminar Servicios_has_Empleados
DELIMITER //
	create procedure sp_EliminarServicios_has_Empleados(in Servicios_codServicio int)
		begin
			delete from Servicios_has_Empleados
				where Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;

-- Listar Servicios_has_Empleados
DELIMITER //
	create procedure sp_ListarServicios_has_Empleados()
		begin
			select
				SHE.Servicios_codigoServicio, 
                SHE.codigoServicio, 
                SHE.codigoEmpleado, 
                SHE.fechaEvento, 
                SHE.horaEvento, 
                SHE.lugarEvento
                from Servicios_has_Empleados SHE;
        end//
DELIMITER ;
-- call sp_ListarServicios_has_Empleados();

-- Buscar Servicios_has_Empleados
DELIMITER //
	create procedure sp_BuscarServicios_has_Empleados(in Servicios_codServicio int)
		begin
			select
				SHE.Servicios_codigoServicio, 
                SHE.codigoServicio, 
                SHE.codigoEmpleado, 
                SHE.fechaEvento, 
                SHE.horaEvento, 
                SHE.lugarEvento
                from Servicios_has_Empleados SHE
					where SHE.Servicios_codigoServicio = Servicios_codServicio;
        end//
DELIMITER ;
-- call sp_BuscarServicios_has_Empleados(1);

-- Reporte General
DELIMITER //
	create procedure sp_ReporteGeneral(in codEmpresa int)
		begin
			select * from Empresas E
					inner join Presupuesto P 
						on E.codigoEmpresa = P.codigoEmpresa
					inner join Servicios S 
						on S.codigoEmpresa = E.codigoEmpresa
					inner join Servicios_has_Empleados SHE
						on SHE.codigoServicio = S.codigoServicio
					inner join Empleados EP
						on EP.codigoEmpleado = SHE.codigoEmpleado    
					inner join TipoEmpleado TE
						on TE.codigoTipoEmpleado = EP.codigoTipoEmpleado
                    inner join Servicios_has_Platos SHP
						on SHP.codigoServicio = S.codigoServicio
					inner join Platos PL
						on PL.codigoPlato = SHP.codigoPlato
					inner join TipoPlato TP
						on TP.codigoTipoPlato = PL.codigoTipoPlato
					inner join Productos_has_Platos PHP
						on PHP.codigoPlato = PL.codigoPlato
					inner join Productos PR 
						on PR.codigoProducto = PHP.codigoProducto
							where E.codigoEmpresa = codEmpresa;
        end//
DELIMITER ;

-- call sp_ReporteGeneral(2);

DELIMITER //
	create procedure sp_AgregarUsuario(in nombreUsuario varchar(100), in apellidoUsuario varchar(100), 
		in usuarioLogin varchar(50), in contrasena varchar(50))
	begin
		insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena)
			values (nombreUsuario, apellidoUsuario, usuarioLogin, contrasena);
    end//
DELIMITER ;

DELIMITER //
	create procedure sp_ListarUsuarios()
		begin
			select
				U.codigoUsuario, 
                U.nombreUsuario, 
                U.apellidoUsuario, 
                U.usuarioLogin, 
                U.contrasena
			from Usuario U;
        end//
DELIMITER ;

call sp_AgregarUsuario('Sergio','Tepaz','stepaz','hola:)');
-- call sp_ListarUsuarios();