package com.alvaro.curso.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alvaro.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.alvaro.curso.springboot.jpa.springbootjpa.entities.Person;

// Esta interfaz extiende CrudRepository, lo que proporciona métodos CRUD básicos para la entidad Person
public interface PersonRepository extends CrudRepository<Person, Long>{

// Consulta personalizada para obtener personas por una lista de IDs
@Query("select p from Person p where p.id in ?1")
public List<Person> getPersonByIds(List<Long> ids);

// Consulta personalizada para obtener el nombre más corto y su longitud
@Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
public List<Object[]> getShorterName();

// Consulta personalizada para obtener el último registro de persona
@Query("select p from Person p where p.id=(select max(p.id) from Person p)")
public Optional<Person> getLastRegistration();

// Consulta personalizada para obtener un resumen de funciones de agregación (min, max, sum, avg, count)
@Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
public Object getResumeAggregationFunction();

// Consulta personalizada para obtener la longitud mínima del nombre en la tabla de personas
@Query("select min(length(p.name)) from Person p")
public Integer getMinLengthName();

// Consulta personalizada para obtener la longitud máxima del nombre en la tabla de personas
@Query("select max(length(p.name)) from Person p")
public Integer getMaxLengthName();

// Consulta personalizada para obtener nombres y sus longitudes
@Query("select p.name, length(p.name) from Person p")
public List<Object[]> getPersonNameLength();

// Consulta personalizada para obtener el total de personas en la tabla
@Query("select count(p) from Person p")
Long getTotalPerson();

// Consulta personalizada para obtener el valor mínimo del ID en la tabla de personas
@Query("select min(p.id) from Person p")
Long getMinId();

// Consulta personalizada para obtener el valor máximo del ID en la tabla de personas
@Query("select max(p.id) from Person p")
Long getMaxId();

// Método de repositorio para encontrar todas las personas ordenadas por nombre de forma ascendente y apellido de forma descendente
List<Person> findAllByOrderByNameAscLastnameDesc();

// Consulta personalizada para obtener todas las personas ordenadas por nombre y apellido de forma ascendente
@Query("select p from Person p order by p.name, p.lastname asc")
List<Person> getAllOrdered();

// Método generado automáticamente para encontrar personas cuyos IDs estén en un rango específico, ordenadas por nombre de forma ascendente
List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

// Método generado automáticamente para encontrar personas cuyos nombres estén en un rango específico, ordenadas por nombre de forma descendente y apellido de forma descendente
List<Person> findByNameBetweenOrderByNameDescLastnameDesc(String name1, String name2);

// Consulta personalizada para encontrar personas cuyos IDs estén en un rango específico, ordenadas por nombre de forma descendente
@Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc")
List<Person> findAllBetweenId(Long id1, Long id2);

// Consulta personalizada para encontrar personas cuyos nombres estén en un rango específico, ordenadas por nombre de forma ascendente y apellido de forma descendente
@Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc, p.lastname desc")
List<Person> findAllBetweenName(String c1, String c2);

// Consulta personalizada para obtener datos de todas las personas, incluyendo transformaciones de mayúsculas y minúsculas
@Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
List<Object[]> findAllPersonDataListCase();

// Consulta personalizada para obtener nombres completos concatenados en mayúsculas
@Query("select upper(p.name || ' ' || p.lastname) from Person p")
List<String> findAllFullNameConcatUpper();

// Consulta personalizada para obtener nombres completos concatenados en minúsculas
@Query("select lower(concat(p.name,' ', p.lastname)) from Person p")
List<String> findAllFullNameConcatLower();

// Consulta personalizada para obtener nombres completos concatenados
@Query("select p.name || ' ' || p.lastname from Person p")
List<String> findAllFullNameConcat();

// Consulta personalizada para contar la cantidad de lenguajes de programación distintos
@Query("select count(distinct p.programmingLanguage) from Person p")
Long findAllProgrammingLanguageDistinctCount();

// Consulta personalizada para obtener una lista de lenguajes de programación distintos
@Query("select distinct p.programmingLanguage from Person p")
List<String> findAllProgrammingLanguageDistinct();

// Consulta personalizada para obtener una lista de todos los nombres
@Query("select p.name from Person p")
List<String> findAllNames();

// Consulta personalizada para obtener una lista de nombres distintos
@Query("select distinct p.name from Person p")
List<String> findAllNamesDistinct();

// Consulta personalizada para mapear los datos de la persona a un DTO específico
@Query("select new com.alvaro.curso.springboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
List<PersonDto> findAllPersonDto();

// Consulta personalizada para obtener objetos Person sin realizar transformaciones
@Query("select new Person(p.name, p.lastname) from Person p")
List<Person> findAllObjectPersonPersonalized();


    // Consulta personalizada para obtener el nombre de la persona por su ID
    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    // Consulta personalizada para obtener el ID de la persona por su ID
    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    // Consulta personalizada para obtener el nombre completo de la persona por su ID
    @Query("select concat(p.name,' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);


    // Consulta personalizada para encontrar una persona por su ID
    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    // Consulta personalizada para encontrar una persona por su nombre
    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    // Consulta personalizada para encontrar una persona cuyo nombre contenga el valor proporcionado
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    // Consulta generada automáticamente para encontrar una persona cuyo nombre contenga el valor proporcionado
    Optional<Person> findByNameContaining(String name);

    // Consulta generada automáticamente para encontrar personas por lenguaje de programación
    List<Person> findByProgrammingLanguage(String programmingLanguage);


    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> finAllMixPerson();


    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Optional<Object> obtenerPersonDataById(Long id);




    // Consulta personalizada para encontrar personas por lenguaje de programación y nombre
    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    // Consulta generada automáticamente para encontrar personas por lenguaje de programación y nombre
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    // Consulta personalizada para obtener datos específicos de la persona (nombre y lenguaje de programación)
    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    // Consulta personalizada para obtener datos específicos de la persona por nombre
    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);

    // Consulta personalizada para obtener datos específicos de la persona por lenguaje de programación y nombre
    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

    // Consulta personalizada para obtener datos específicos de la persona por lenguaje de programación
    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);
}
