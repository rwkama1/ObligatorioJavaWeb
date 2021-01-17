DROP database IF EXISTS Obligatorio;
Create database Obligatorio;
Use Obligatorio;

create table Empleado(
CedulaEmpleado bigint primary key not null,
NomEmpleado varchar(70) not null,
Clave varchar(50) not null,
Sueldo double not null,
FechaIngreso date not null,
BajaEmpleado bit not null default false
);
create table Cobrador(
CedulaCobrador bigint primary key not null,
Transporte varchar(50) not null,
foreign key(CedulaCobrador) references Empleado(CedulaEmpleado)
);
create table Tecnico(
CedulaTecnico bigint primary key not null,
EspCamaras boolean not null,
EspAlarmas boolean not null,
foreign key(CedulaTecnico) references Empleado(CedulaEmpleado)
);
create table Administrador(
CedulaAdmin bigint primary key not null,
foreign key(CedulaAdmin) references Empleado(CedulaEmpleado)
);
create table Cliente(
CedulaCliente bigint primary key not null,
NomCliente varchar(50) not null,
DirCobro varchar(80) not null,
ZonaBarrio varchar(80) not null,
Telefono varchar(15) not null
);
create table Propiedad(
IDPropiedad int not null,
Tipo varchar(50) not null,
DirPropiedad varchar(50) not null,
CedCliente bigint not null,
primary key(IDPropiedad,CedCliente),
foreign key(CedCliente) references Cliente(CedulaCliente)
);
create table Servicio(
NServicio int primary key auto_increment,
FechaContratacion datetime not null,
Monitoreo bit not null,
Propiedad int not null,
ClienteProp bigint not null,
BajaServicio bit not null default false,
foreign key(Propiedad) references Propiedad(IDPropiedad),
foreign key(ClienteProp) references Propiedad(CedCliente)
);
create table ServicioAlarma(
NServicioAlarma int not null primary key,
CodigoAnulacion int null,
foreign key(NServicioAlarma) references Servicio(NServicio)
);
create table ServicioCamara(
NServicioCamara int not null primary key,
TerminalGrabacion bit  null,
foreign key(NServicioCamara) references Servicio(NServicio)
);
create table Dispositivo(
NInventario int primary key not null,
Ubicación varchar(100)  null,
CedTecnico bigint  null,
foreign key(CedTecnico) references Tecnico(CedulaTecnico)
);
create table DispositivoAlarma(
NInventarioAlarma int not null primary key,
NServiceAlarma int null,
foreign key(NInventarioAlarma) references Dispositivo(NInventario),
foreign key(NServiceAlarma) references ServicioAlarma(NServicioAlarma)
);
create table DispositivoCamara(
NInventarioCamara int not null primary key,
ExterioroInterior varchar(100) null,
NServiceCamara int null,
foreign key(NInventarioCamara) references Dispositivo(NInventario),
foreign key(NServiceCamara) references ServicioCamara(NServicioCamara)
);
create table ReciboCobro(
IDRecibo int not null primary key auto_increment,
FechaFacturacion date not null,
ImporteTotal double null,
CedCobrador bigint null,
ClienteR bigint not null,
foreign key(ClienteR) references Cliente(CedulaCliente),
foreign key(CedCobrador) references Cobrador(CedulaCobrador)
);
create table LineaReciboCobro(
IDLinea int not null,
NServicioLinea int not null,
Importe double not null,
IDReciboLinea int not null,
primary key(IDLinea,IDReciboLinea,NServicioLinea),
foreign key(NServicioLinea) references Servicio(NServicio),
foreign key(IDReciboLinea) references ReciboCobro(IDRecibo)
);

insert into Empleado values (1111111,"Juan Ramirez","juan",20000,'2017-1-1',false);
insert into Empleado values (2222222,"Jackso Martinez","jackson",20000,'2017-1-15',false);
insert into Empleado values (3333333,"Roberto Alarcon","roberto",15000,'2017-1-20',false);
insert into Empleado values (4444444,"Antonio Hernandez","antonio",15000,'2017-1-10',false);
insert into Empleado values (5555555,"Ernesto Campos","ernesto",15000,'2017-1-16',false);
insert into Empleado values (6666666,"Juana Elvira","juana",10000,'2017-2-2',false);
insert into Empleado values (7777777,"Gerardo Benitez","gerardo",10000,'2017-2-3',false);
insert into Empleado values (8888888,"Pedro Zalmeron","pedro",10000,'2017-1-14',false);

insert into Administrador values (1111111);
insert into Administrador values (2222222);

insert into Cobrador values (3333333,"Moto");
insert into Cobrador values (4444444,"Auto");
insert into Cobrador values (5555555,"Camioneta");

insert into Tecnico values (6666666,true,true);
insert into Tecnico values (7777777,true,false);
insert into Tecnico values (8888888,false,true);


insert into Cliente values (9999999,"Eduardo Larrazabal","Venezuela 154","Parque Rodo","011111111");
insert into Cliente values (9911111,"Mateo Martinez","Mitre 124","Aguada","022222222");
insert into Cliente values (9922222,"Daniel Lopez","Bartolome Mitre 1434","Cerro","044444444");


insert into Propiedad values (1,"Casa","Av Gral Flores",9999999);
insert into Propiedad values (2,"Apartamento","Av Burgues",9999999);
insert into Propiedad values (3,"Casa","Av Gral Avellaneda",9999999);
insert into Propiedad values (1,"Local Comercial","Av 25 de Mayo ",9911111);
insert into Propiedad values (2,"Apartamento","AV. 5 DE MAYO NO. 1652",9911111);
insert into Propiedad values (3,"Apartamento","Av 9 de Julio ",9911111);
insert into Propiedad values (1,"Casa","Av Alvear ",9922222);
insert into Propiedad values (2,"Casa","Av Cerrito ",9922222);

insert into Servicio values (null,'2017-5-24',true,1,9999999,false);
insert into Servicio values (null,'2017-5-21',false,2,9911111,false);
insert into Servicio values (null,'2017-5-8',true,3,9999999,false);
insert into Servicio values (null,'2017-5-12',false,1,9911111,false);
insert into Servicio values (null,'2017-5-15',true,1,9922222,false);
insert into Servicio values (null,'2017-5-12',false,3,9911111,false);
insert into Servicio values (null,'2017-5-15',true,2,9999999,false);
insert into Servicio values (null,'2017-5-15',false,2,9922222,false);

insert into ServicioAlarma values (1,14500);
insert into ServicioAlarma values (2,15350);
insert into ServicioAlarma values (4,12300);
insert into ServicioAlarma values (7,13206);

insert into ServicioCamara values (3,true);
insert into ServicioCamara values (5,false);
insert into ServicioCamara values (6,true);
insert into ServicioCamara values (8,false);

insert into Dispositivo values (10101,"Puerta Principal",6666666);
insert into Dispositivo values (10102,"Vidriera",7777777);
insert into Dispositivo values (10103,"Tragaluz de la cocina",8888888);
insert into Dispositivo values (10104,"Sin Servicio",null);
insert into Dispositivo values (10105,"Garaje",6666666);
insert into Dispositivo values (10106,"Sin Servicio",null);
insert into Dispositivo values (10107,"Sin Servicio",null);
insert into Dispositivo values (10108,"Sin Servicio",null);
insert into Dispositivo values (10109,"Sin Servicio",null);

insert into DispositivoAlarma values (10101,1);
insert into DispositivoAlarma values (10103,2);
insert into DispositivoAlarma values (10105,4);
insert into DispositivoAlarma values (10104,null);
insert into DispositivoAlarma values (10107,null);

insert into DispositivoCamara values (10102,"Interior",3);
insert into DispositivoCamara values (10106,"Sin Servicio",null);
insert into DispositivoCamara values (10108,"Sin Servicio",null);
insert into DispositivoCamara values (10109,"Sin Servicio",null);

insert into ReciboCobro values (null,'2017-05-29',9000,null,9999999);


insert into LineaReciboCobro values (1,1,3000,1);
insert into LineaReciboCobro values (2,3,3000,1);
insert into LineaReciboCobro values (3,7,3000,1);



#Agregar Propiedad
DELIMITER //
Create Procedure AgregarPropiedad(ptipo varchar(50),pdirp varchar(50),pcedulac bigint)

begin
if not exists (select * from Propiedad where CedCliente=pcedulac)then
insert into Propiedad values (1,ptipo,pdirp,pcedulac);

else
set @idpropiedad = (Select MAX(IDPropiedad) from Propiedad where CedCliente=pcedulac)+1;
Insert into Propiedad values (@idpropiedad,ptipo,pdirp,pcedulac);
end if;
end//
DELIMITER ;
#Eliminar Dispositivo Camara
DELIMITER //
Create Procedure EliminarDCamara(pni int)
cuerpo:begin
declare transaccionActiva bit;

declare exit handler for sqlexception
begin
 if transaccionActiva then
	rollback;
 end if;
end;
set transaccionActiva=1;
start transaction;
delete from DispositivoCamara where NInventarioCamara=pni;
delete from Dispositivo where  NInventario=pni;
commit;
set transaccionActiva=0;
end//
Delimiter ;
#Eliminar Dispositivo Alarma
DELIMITER //
Create Procedure EliminarDAlarma(pni int)
cuerpo:begin
declare transaccionActiva bit;

declare exit handler for sqlexception
begin
 if transaccionActiva then
	rollback;
 end if;
end;
set transaccionActiva=1;
start transaction;
delete from DispositivoAlarma where NInventarioAlarma=pni;
delete from Dispositivo where  NInventario=pni;
commit;
set transaccionActiva=0;
end//
Delimiter ;
#Agregar  Recibo de Cobro
DELIMITER //
Create Procedure AgregarRecibo(ptotal double,pfecha date,cliente bigint)

begin
Insert into ReciboCobro VALUES (null,pfecha,ptotal,null,cliente);
end//
DELIMITER ;

#Agregar Linea de Recibo
DELIMITER //
Create Procedure AgregarLinea(pServicio int,pimporte double)
begin
if not exists (select * from LineaReciboCobro where IDReciboLinea=last_insert_id())then
Insert into LineaReciboCobro
values (1,pServicio,pimporte,last_insert_id());
else
set @idlinea= (Select MAX(IDLinea) from LineaReciboCobro where IDReciboLinea=last_insert_id())+1;
Insert into LineaReciboCobro
values(@idlinea,pServicio,pimporte,last_insert_id());
end if;
end//
DELIMITER ;


