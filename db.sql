SQLite format 3   @                                                                     .?�   � 
�                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         �,%%�tabledetalleVentadetalleVentaCREATE TABLE detalleVenta (
	detalleVentaId INTEGER NOT NULL,
	ventaId INTEGER NOT NULL,
	productoId INTEGER NOT NULL,
	unidades INTEGER NOT NULL,
	precioUnidad REAL NOT NULL,
	total REAL NOT NULL,
	CONSTRAINT detalleVentaId PRIMARY KEY (detalleVentaId)
)��mtableventaventaCREATE TABLE venta (
	ventaId INTEGER NOT NULL,
	folio INTEGER NOT NULL,
	subTotal REAL NOT NULL,
	iva REAL NOT NULL,
	total REAL NOT NULL,
	fecha TEXT NOT NULL,
	estado INTEGER NOT NULL,
	CONSTRAINT ventaId PRIMARY KEY (ventaId)
)�i�%tableproductoproductoCREATE TABLE producto (
	productoId INTEGER NOT NULL,
	codigo INTEGER NOT NULL,
	marca TEXT NOT NULL,
	nombre TEXT NOT NULL,
	precio REAL NOT NULL,
	CONSTRAINT productoId PRIMARY KEY (productoId)
)� � ���                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ( /r+truperMartillo de acero@R�     ) 1rOTruperDesarmador de cruz@/��G�{   %/r+truperMartillo de aceror+   � ����e                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         �!	i@S������@)��$�0@W/|�hs28-06-2020   T!	@lP     @B�Q�@pk�
=p�28-06+ !g@O��G�{@$w�kP��@R�c�A \28-06-2020% 	f@/��G�{@w�kP��@2�c�A \2019 		
?�z�G�{@$Q��R2019                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              