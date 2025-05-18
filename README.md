<h1>Conexiones con la base de datos</h1>
<p>
MYSQL_URL = "jdbc:mysql://db.cnfp5sduwtx2.us-east-1.rds.amazonaws.com:3306/CCdb"
MYSQL_USER ="HR"
MYSQL_PASS = "Cambiame2025"
</p>

<h1>EndPoints</h1>

<h2>Products<h2>

<p>

Añadir un producto: /CrazyCow_Server/Controller?ACTION=PRODUCT.ADD&category_id=?&product_name=?&description=?&price=?&image=?

Actualizar un producto: /CrazyCow_Server/Controller?ACTION=PRODUCT.UPDATE

Borrar un producto: /CrazyCow_Server/Controller?ACTION=PRODUCT.DELETE&product_id=?

Buscar todos los productos : /CrazyCow_Server/Controller?ACTION=PRODUCT.FIND_ALL

Buscar por id un producto .Añade la informacion de ingredientes y alergenos: /CrazyCow_Server/Controller?ACTION=PRODUCT.FIND_BY_ID&product_id=?

</p>

<h2>Ofertas de Trabajo<h2>

<p>

Buscar todas las ofertas de trabajo: /CrazyCow_Server/Controller?ACTION=JOB_OFFER.FIND_ALL

</p>


<h2>Candidatos<h2>

<p>

Buscar todos los candidatos: /CrazyCow_Server/Controller?ACTION=APPLICANT.FIND_ALL

Añadir un candidato. Al añadir un candidato se añade tambien la información a la tabla intermedia JOB_OFFER_APPLICANTS: /CrazyCow_Server/Controller?ACTION=APPLICANT.ADD&name=?&surname=?&email=?&phone_number=?&address=?&resume=?f&job_offer_id=?

</p>


<h2>Clientes<h2>

<p>

Buscar todos los clientes: /CrazyCow_Server/Controller?ACTION=CUSTOMER.FIND_ALL

Buscar un cliente por su email: /CrazyCow_Server/Controller?ACTION=CUSTOMER.FIND_BY_EMAIL&email=?

Validar el login: /CrazyCow_Server/Controller?ACTION=CUSTOMER.LOGIN&email=?&password=?

Registrar un nuevo cliente: /CrazyCow_Server/Controller?ACTION=CUSTOMER.REGISTER&name=?&surname=?&email=?&phone_number=?&user_name=?&password=?&address=?


</p>

<h2>Empleados<h2>

<p>

Buscar todos los empleados: /CrazyCow_Server/Controller?ACTION=EMPLOYEE.FIND_ALL

Buscar un empleado por su email: /CrazyCow_Server/Controller?ACTION=EMPLOYEE.FIND_BY_EMAIL&email=?

Validar el login: /CrazyCow_Server/Controller?ACTION=EMPLOYEE.LOGIN&email=?&password=?


</p>

<h2>Pedidos<h2>

<p>

Buscar todos los pedidos, admite filtro por restaurante: /CrazyCow_Server/Controller?ACTION=ORDER.FIND_ALL

Añadir un nuevo pedido. Al a ñadirse un pedido se añade automaticamente  a las tabla de PAYMENTS y ORDER_PRODUCTS: 

/CrazyCow_Server/Controller?ACTION=ORDER.ADD&customer_id=?&restaurant_id=?&order_status=?&total=?&location=?&order_details=?:?,?:?&holder_name=?&holder_number=?&cvv=?&card_type=?

</p>
