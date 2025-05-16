package Controller.Actions;

import Model.DAO.ProductDao;
import Model.Entities.Product;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase que implementa las acciones relacionadas con productos.
 * Gestiona las operaciones CRUD a través de peticiones HTTP.
 */
public class ProductAction implements IAction {

    /**
     * Ejecuta la acción solicitada para productos.
     *
     * @param request Objeto HttpServletRequest con los datos de la petición
     * @param response Objeto HttpServletResponse para la respuesta
     * @param action Acción a ejecutar (ADD, UPDATE, DELETE, FIND_ALL, FIND_BY_ID)
     * @param objectParams Mapa de parámetros recibidos en la petición
     * @return String con el resultado de la operación en formato JSON o mensaje de estado
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {
        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";
        switch (action) {
            case "ADD":
                strReturn =add(objectParams);
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=PRODUCT.ADD&category_id=1000&product_name=MuuBurger&description=DeliciousHamburger&price=9.99&image=burguer2.png
                break;
            case "UPDATE":
                strReturn = update(objectParams);
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=PRODUCT.UPDATE&product_id=8308&product_name=SuperMuuBurger&price=12.99
                break;
            case "DELETE":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=PRODUCT.DELETE&product_id=8308
                strReturn = delete(objectParams);
                break;
            case "FIND_ALL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=PRODUCT.FIND_ALL
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=PRODUCT.FIND_ALL&category_id=1001
                strReturn = findAll(objectParams);
                break;
            case "FIND_BY_ID":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=PRODUCT.FIND_BY_ID&product_id=8000
                strReturn =findById(objectParams);
                break;

            default:
                strReturn = "ERROR. Invalid action";
                break;

        }

        return strReturn;
    }

    // ==================== MÉTODOS PRIVADOS ====================

    /**
     * Obtiene todos los productos que coincidan con los filtros especificados.
     *
     * @param objectParams Mapa de parámetros con los filtros (product_id, category_id)
     * @return String con la lista de productos en formato JSON
     */

    private String findAll(Map<String, String[]> objectParams) {
        String strReturn = "";
        ProductDao proDao = new ProductDao(); //Se inicializa la conexion con la base de detos

        // Crear un objeto Product para enviar como parámetro a `findAll(Object bean)`
        Product filtro = new Product();

        //Procesamos product_id
        if (objectParams.get("product_id") != null && objectParams.get("product_id").length > 0) {
            System.out.println("Valor recibido product_id: " + objectParams.get("product_id")[0]);
            filtro.setProduct_id(Integer.parseInt(objectParams.get("product_id")[0]));
        } else {
            System.out.println("El parámetro product_id no llegó correctamente.");
        }
        //Procesamos category_id
        if (objectParams.get("category_id") != null && objectParams.get("category_id").length > 0) {
            System.out.println("Valor recibido category_id: " + objectParams.get("category_id")[0]);
            filtro.setCategory_id(Integer.parseInt(objectParams.get("category_id")[0]));
        } else {
            System.out.println("El parámetro category_id no llegó correctamente.");
        }


        ArrayList<Product> listProducts = proDao.findAll(filtro);
        return Product.toArrayJson(listProducts);

    }

    /**
     * Busca un producto por su ID.
     *
     * @param objectParams Mapa de parámetros que debe contener product_id
     * @return String con el producto en formato JSON o mensaje de error
     */
    private String findById(Map<String, String[]> objectParams) {
        String strReturn = "";
        ProductDao proDao = new ProductDao(); //Se inicializa la conexion con la base de detos

        // Crear un objeto `Product` para enviar como parámetro a `findAll(Object bean)`
        Product filtro = new Product();

        //Procesamos product_id
        if (objectParams.get("product_id") != null && objectParams.get("product_id").length > 0) {
            System.out.println("Valor recibido product_id: " + objectParams.get("product_id")[0]);
            filtro.setProduct_id(Integer.parseInt(objectParams.get("product_id")[0]));
        } else {
            System.out.println("El parámetro product_id no llegó correctamente.");
        }

        Product product = proDao.findById(filtro.getProduct_id());
        return Product.toJson(product);

    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param objectParams Mapa de parámetros que debe contener product_id
     * @return String con el resultado de la operación
     */
    private String delete(Map<String, String[]> objectParams) {
        ProductDao prodao = new ProductDao();

        int filasAfectadas = 0;

        // Verificamos si tenemos el parámetro product_id
        if (objectParams.get("product_id") != null && objectParams.get("product_id").length > 0) {
            try {
                int idProduct = Integer.parseInt(objectParams.get("product_id")[0]);
                filasAfectadas = prodao.delete(idProduct); // Pasamos el ID directamente sin crear un objeto Product
            } catch (NumberFormatException e) {
                System.out.println("Error: product_id no es un número válido.");

            }
        } else {
            System.out.println("El parámetro product_id no llegó correctamente.");

        }

        return "Producto eliminado correctamente. Las filas afectadas son :  " + filasAfectadas;

    }

    /**
     * Añade un nuevo producto a la base de datos.
     *
     * @param objectParams Mapa de parámetros con los datos del producto
     * @return String con el resultado de la operación
     */
    private String add(Map<String, String[]> objectParams) {
        ProductDao proDao = new ProductDao();
        Product newProduct = new Product();
        String strReturn = "";


        try {

            // Procesar category_id
            if (objectParams.get("category_id") != null && objectParams.get("category_id").length > 0) {
                System.out.println("Valor recibido category_id: " + objectParams.get("category_id")[0]);
                newProduct.setCategory_id(Integer.parseInt(objectParams.get("category_id")[0]));
            } else {
                System.out.println("Error: El parámetro category_id es obligatorio.");
            }

            // Procesar product_name
            if (objectParams.get("product_name") != null && objectParams.get("product_name").length > 0) {
                System.out.println("Valor recibido product_name: " + objectParams.get("product_name")[0]);
                newProduct.setProduct_name(objectParams.get("product_name")[0]);
            } else {
                System.out.println("Error: El parámetro product_name es obligatorio.");
            }

            // Procesar description
            if (objectParams.get("description") != null && objectParams.get("description").length > 0) {
                System.out.println("Valor recibido description: " + objectParams.get("description")[0]);
                newProduct.setDescription(objectParams.get("description")[0]);
            } else {
                System.out.println("Error: El parámetro description es obligatorio.");
            }

            // Procesar price
            if (objectParams.get("price") != null && objectParams.get("price").length > 0) {
                System.out.println("Valor recibido price: " + objectParams.get("price")[0]);
                try {
                    double price = Double.parseDouble(objectParams.get("price")[0]);
                    newProduct.setPrice(price);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El parámetro price debe ser un número válido.");
                }
            } else {
                System.out.println("Error: El parámetro price es obligatorio.");
            }

            // Procesar image
            if (objectParams.get("image") != null && objectParams.get("image").length > 0) {
                System.out.println("Valor recibido image: " + objectParams.get("image")[0]);
                newProduct.setImage(objectParams.get("image")[0]);
            } else {
                System.out.println("Error: El parámetro imagen es obligatorio.");
            }


            int filasAfectadas = proDao.add(newProduct);

            if (filasAfectadas > 0) {
                strReturn = "Producto añadido correctamente" + ", las filas afectadas son :"+filasAfectadas ;
            } else {
                strReturn = "No se pudo añadir el producto";
            }

        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud de añadir producto: " + e.getMessage());
            strReturn = "Error interno al procesar la solicitud";
        }

        return strReturn;

    }

    /**
     * Actualiza un producto existente.
     *
     * @param objectParams Mapa de parámetros con los datos a actualizar
     * @return String con el resultado de la operación
     */

    public String update(Map<String, String[]> objectParams){
        ProductDao proDao = new ProductDao();
        Product updateProduct = new Product();
        String strReturn = "";

        try {
            // Procesar product_id (obligatorio para actualizar)
            if (objectParams.get("product_id") != null && objectParams.get("product_id").length > 0) {
                System.out.println("Valor recibido product_id: " + objectParams.get("product_id")[0]);
                try {
                    int productId = Integer.parseInt(objectParams.get("product_id")[0]);
                    updateProduct.setProduct_id(productId);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El parámetro product_id debe ser un número válido.");
                    return "Error: El parámetro product_id debe ser un número válido.";
                }
            } else {
                System.out.println("Error: El parámetro product_id es obligatorio para actualizar.");
                return "Error: El parámetro product_id es obligatorio para actualizar.";
            }

            // Procesar category_id (opcional)
            if (objectParams.get("category_id") != null && objectParams.get("category_id").length > 0) {
                System.out.println("Valor recibido category_id: " + objectParams.get("category_id")[0]);
                try {
                    int categoryId = Integer.parseInt(objectParams.get("category_id")[0]);
                    updateProduct.setCategory_id(categoryId);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El parámetro category_id debe ser un número válido.");
                }
            }

            // Procesar product_name (opcional)
            if (objectParams.get("product_name") != null && objectParams.get("product_name").length > 0) {
                System.out.println("Valor recibido product_name: " + objectParams.get("product_name")[0]);
                updateProduct.setProduct_name(objectParams.get("product_name")[0]);
            }

            // Procesar description (opcional)
            if (objectParams.get("description") != null && objectParams.get("description").length > 0) {
                System.out.println("Valor recibido description: " + objectParams.get("description")[0]);
                updateProduct.setDescription(objectParams.get("description")[0]);
            }

            // Procesar price (opcional)
            if (objectParams.get("price") != null && objectParams.get("price").length > 0) {
                System.out.println("Valor recibido price: " + objectParams.get("price")[0]);
                try {
                    double price = Double.parseDouble(objectParams.get("price")[0]);
                    updateProduct.setPrice(price);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El parámetro price debe ser un número válido.");
                }
            }

            // Procesar image (opcional)
            if (objectParams.get("image") != null && objectParams.get("image").length > 0) {
                System.out.println("Valor recibido image: " + objectParams.get("image")[0]);
                updateProduct.setImage(objectParams.get("image")[0]);
            }

            // Intentar actualizar el producto
            int filasAfectadas = proDao.update(updateProduct);

            if (filasAfectadas > 0) {
                strReturn = "Producto actualizado correctamente. Filas afectadas: " + filasAfectadas;
            } else {
                strReturn = "No se pudo actualizar el producto o no se realizaron cambios";
            }

        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud de actualizar producto: " + e.getMessage());
            strReturn = "Error interno al procesar la solicitud de actualización";
        }

        return strReturn;

    }

}
