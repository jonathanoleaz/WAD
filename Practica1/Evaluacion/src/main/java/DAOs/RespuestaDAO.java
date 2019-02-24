// Generated by DB Solo 5.2.3 on 24/02/2019 at 01:32:57 PM
package DAOs;

import java.util.List;


public interface RespuestaDAO
{
  // CRUD methods
  public Respuesta getByPrimaryKey(int idrespuesta) throws DAOException;

  public List selectAll() throws DAOException;

  public List select(String whereStatement, Object[] bindVariables)
    throws DAOException;

  public long selectCount() throws DAOException;

  public long selectCount(String whereStatement, Object[] bindVariables)
    throws DAOException;

  public int update(Respuesta obj) throws DAOException;

  public int insert(Respuesta obj) throws DAOException;

  public int delete(Respuesta obj) throws DAOException;

  // Finders
  public List getByOpcionelegida(Integer opcionelegida)
    throws DAOException;

  public List getByPreguntaIdpregunta(int preguntaIdpregunta)
    throws DAOException;

  public List getByResultadoexamenIdresultado(int resultadoexamenIdresultado)
    throws DAOException;
}
