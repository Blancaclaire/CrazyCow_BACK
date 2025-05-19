package Model.DAO;

import java.util.ArrayList;

/**
 * Interfaz genérica para Data Access Objects (DAO) que define las operaciones CRUD básicas.
 *
 * @param <E> Tipo de la entidad/modelo que manejará el DAO (Ej: Product, Customer)
 * @param <I> Tipo del identificador único de la entidad (Ej: Integer, String)
 *
 * <p>Proporciona una abstracción para el acceso a datos independiente
 * del origen de datos subyacente (base de datos, API, etc.).</p>
 */
public interface IDao <E,I>{
    /**
     * Agrega una nueva entidad al sistema de persistencia.
     *
     * @param bean Objeto de la entidad a persistir
     * @return Número de registros afectados (1 si fue exitoso, 0 si falló)
     */
    public int add(E bean);
    /**
     * Elimina una entidad del sistema de persistencia usando su identificador único.
     *
     * @param **id** Identificador único de la entidad a eliminar
     * @return Número de registros afectados (1 si fue exitoso, 0 si no se encontró)
     */
    public int delete (I e);
    /**
     * Actualiza una entidad existente en el sistema de persistencia.
     *
     * @param bean Objeto de la entidad con los datos actualizados
     * @return Número de registros afectados (1 si fue exitoso, 0 si falló)
     */
    public  int update(E bean);
    /**
     * Recupera todas las entidades del sistema de persistencia que coincidan con el ejemplo proporcionado.
     *
     * @param bean Objeto de ejemplo con los criterios de búsqueda (puede ser null para obtener todos)
     * @return ArrayList con todas las entidades encontradas (vacío si no hay coincidencias)
     */
    public ArrayList<E> findAll(E bean);
}
