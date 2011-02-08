package libiada.Root;

/**
 * Created by IntelliJ IDEA.
 * User: InquisitioN
 * Date: 19.11.2010
 * Time: 17:10:33
 * To change this template use File | Settings | File Templates.
 */
public interface IBaseObject {
        /// Метод клонирования объекта
        ///</summary>
        ///<returns>Копию данного объекта</returns>
        IBaseObject Clone();

        ///<summary>
        /// Метод реализующий отношение эквивалентности
        ///</summary>
        ///<param name="obj">Объект c которым происходит проверка на эквивалентность</param>
        ///<returns>True если объекты эквивалентны, иначе false</returns>
        boolean Equals(Object obj);

        IBin GetBin();
}
