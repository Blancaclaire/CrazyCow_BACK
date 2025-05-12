package Model.DAO;

import java.util.ArrayList;

public interface IDao <E,I>{

    //E:entidad
    //I:Identificador

    public int add(E bean);
    public int delete (I e);
    public  int update(E bean);
    public ArrayList<E> findAll(E bean);
}
