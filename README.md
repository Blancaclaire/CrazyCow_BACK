<h1>Conexiones con la base de datos</h1>
<p>
MYSQL_URL = "jdbc:mysql://db.cnfp5sduwtx2.us-east-1.rds.amazonaws.com:3306/CCdb"
MYSQL_USER ="HR"
MYSQL_PASS = "Cambiame2025"
</p>

<h1>EndPoints</h1>

<h2>Products</h2>

<p>

<b>Añadir un producto:</b> /CrazyCow_Server/Controller?ACTION=PRODUCT.ADD&category_id=?&product_name=?&description=?&price=?&image=?

<b>Actualizar un producto:</b> /CrazyCow_Server/Controller?ACTION=PRODUCT.UPDATE

<b>Borrar un producto:</b> /CrazyCow_Server/Controller?ACTION=PRODUCT.DELETE&product_id=?

<b>Buscar todos los productos :</b> /CrazyCow_Server/Controller?ACTION=PRODUCT.FIND_ALL

<b>Buscar por id un producto .Añade la informacion de ingredientes y alergenos: </b>/CrazyCow_Server/Controller?ACTION=PRODUCT.FIND_BY_ID&product_id=?

</p>

<h2>Ofertas de Trabajo</h2>

<p>

<b>Buscar todas las ofertas de trabajo:</b> /CrazyCow_Server/Controller?ACTION=JOB_OFFER.FIND_ALL

</p>


<h2>Candidatos</h2>

<p>

<b>Buscar todos los candidatos:</b> /CrazyCow_Server/Controller?ACTION=APPLICANT.FIND_ALL

<b>Añadir un candidato. Al añadir un candidato se añade tambien la información a la tabla intermedia JOB_OFFER_APPLICANTS:</b> /CrazyCow_Server/Controller?ACTION=APPLICANT.ADD&name=?&surname=?&email=?&phone_number=?&address=?&resume=?f&job_offer_id=?

</p>


<h2>Clientes</h2>

<p>

<b>Buscar todos los clientes: </b>/CrazyCow_Server/Controller?ACTION=CUSTOMER.FIND_ALL

<b>Buscar un cliente por su email:</b> /CrazyCow_Server/Controller?ACTION=CUSTOMER.FIND_BY_EMAIL&email=?

<b>Validar el login:</b> /CrazyCow_Server/Controller?ACTION=CUSTOMER.LOGIN&email=?&password=?

<b>Registrar un nuevo cliente: </b>/CrazyCow_Server/Controller?ACTION=CUSTOMER.REGISTER&name=?&surname=?&email=?&phone_number=?&user_name=?&password=?&address=?


</p>

<h2>Empleados</h2>

<p>

<b>Buscar todos los empleados:</b> /CrazyCow_Server/Controller?ACTION=EMPLOYEE.FIND_ALL

<b>Buscar un empleado por su email:</b> /CrazyCow_Server/Controller?ACTION=EMPLOYEE.FIND_BY_EMAIL&email=?

<b>Validar el login:</b> /CrazyCow_Server/Controller?ACTION=EMPLOYEE.LOGIN&email=?&password=?


</p>

<h2>Pedidos</h2>

<p>

<b>Buscar todos los pedidos, admite filtro por restaurante:</b> /CrazyCow_Server/Controller?ACTION=ORDER.FIND_ALL

<b>Añadir un nuevo pedido. Al añadirse un pedido se añade automaticamente  a las tabla de PAYMENTS y ORDER_PRODUCTS: </b>

/CrazyCow_Server/Controller?ACTION=ORDER.ADD&customer_id=?&restaurant_id=?&order_status=?&total=?&location=?&order_details=?:?,?:?&holder_name=?&holder_number=?&cvv=?&card_type=?

</p>
