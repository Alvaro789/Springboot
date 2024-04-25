package com.alvaro.curso.springboot.jpa.springbootjpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.alvaro.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.alvaro.curso.springboot.jpa.springbootjpa.entities.Person;
import com.alvaro.curso.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        delete();
    }
    // Método para realizar una consulta utilizando la cláusula WHERE IN
@Transactional(readOnly = true)
public void whereIn(){
    System.out.println("====================== consulta where in ======================");
    // Consulta personas cuyos IDs estén en la lista proporcionada
    List<Person> persons = repository.getPersonByIds(Arrays.asList(1L, 2L, 5L));
    persons.forEach(System.out::println);
}

// Método para realizar consultas que involucran subconsultas
@Transactional(readOnly = true)
public void subQueries(){

    System.out.println("====================== consulta por el nombre mas corto y su largo ======================");
    // Consulta para obtener el nombre más corto y su longitud
    List<Object[]> registers = repository.getShorterName();
    registers.forEach(reg -> {
        String name = (String) reg[0];
        Integer length = (Integer) reg[1];
        System.out.println("name=" + name + ", length=" + length);
    });

    System.out.println("====================== consulta para obtener el ultimo registro de persona ======================");
    // Consulta para obtener el último registro de persona
    Optional<Person> optionalPerson = repository.getLastRegistration();
    optionalPerson.ifPresent(System.out::println);
}

// Método para realizar consultas que involucran funciones de agregación
@Transactional(readOnly = true)
public void queriesFunctionAggregation(){

    System.out.println("====================== consulta con el total de regiatros de la tabla persona ======================");
    // Consulta para obtener el total de registros en la tabla de persona
    Long count = repository.getTotalPerson();
    System.out.println(count);

    System.out.println("====================== consulta con el valor minimo del id ======================");
    // Consulta para obtener el valor mínimo del ID
    Long min = repository.getMinId();
    System.out.println(min);

    System.out.println("====================== consulta con el valor maximo del id ======================");
    // Consulta para obtener el valor máximo del ID
    long max = repository.getMaxId();
    System.out.println(max);

    System.out.println("====================== consulta con el nombre y su largo ======================");
    // Consulta para obtener los nombres y sus longitudes
    List<Object[]> regs = repository.getPersonNameLength();
    regs.forEach(reg -> {
        String name = (String) reg[0];
        Integer length = (Integer) reg[1];
        System.out.println("name=" + name + ", length=" + length);
    });
    
    System.out.println("====================== consulta con el nombre mas corto ======================");
    // Consulta para obtener la longitud del nombre más corto
    Integer minLengthName = repository.getMinLengthName();
    System.out.println(minLengthName);

    System.out.println("====================== consulta con el nombre mas largo ======================");
    // Consulta para obtener la longitud del nombre más largo
    Integer maxLengthName = repository.getMaxLengthName();
    System.out.println(maxLengthName);

    System.out.println("====================== consultas resumen de funciones de agregacion min, max, sum, avg, count ======================");
    // Consulta para obtener un resumen de funciones de agregación (min, max, sum, avg, count)
    Object[] resumReg = (Object[]) repository.getResumeAggregationFunction();
    System.out.println(
        "min=" + resumReg[0] +
        ", max=" + resumReg[1] +
        ", sum=" + resumReg[2] +
        ", avg=" + resumReg[3] +
        ", count=" + resumReg[4]);
}


    // Método para realizar consultas personalizadas utilizando rangos de IDs y
    // ordenando los resultados por nombre de forma ascendente
    @Transactional(readOnly = true)
    public void personalizedQueriesBetween() {
        System.out.println("====================== consulta por rangos ======================");
        // Consulta personas cuyos IDs estén entre 2 y 5, ordenadas por nombre de forma
        // ascendente
        List<Person> persons = repository.findByIdBetweenOrderByNameAsc(2L, 5L);
        persons.forEach(System.out::println);

        // Consulta personas cuyos nombres estén entre "J" y "Q", ordenadas por nombre
        // de forma descendente y apellido de forma descendente
        persons = repository.findByNameBetweenOrderByNameDescLastnameDesc("J", "Q");
        persons.forEach(System.out::println);

        persons = repository.findAllByOrderByNameAscLastnameDesc();
        persons.forEach(System.out::println);
    }

    // Método para realizar consultas personalizadas utilizando transformaciones de
    // nombres y apellidos
    @Transactional(readOnly = true)
    public void personalizedQueriesConcatUpperAndLowerCase() {
        System.out.println("====================== Consulta nombres y apellidos de personas ======================");
        // Consulta nombres completos concatenados
        List<String> names = repository.findAllFullNameConcat();
        names.forEach(System.out::println);

        System.out.println("====================== consultas nombre y apellidos mayuscula ======================");
        // Consulta nombres completos concatenados en mayúsculas
        names = repository.findAllFullNameConcatUpper();
        names.forEach(System.out::println);

        System.out.println("====================== consultas nombre y apellidos miniscula ======================");
        // Consulta nombres completos concatenados en minúsculas
        names = repository.findAllFullNameConcatLower();
        names.forEach(System.out::println);

        System.out.println(
                "====================== consultas personalizada persona upper y lower case ======================");
        // Consulta datos de todas las personas con transformaciones de mayúsculas y
        // minúsculas
        List<Object[]> regs = repository.findAllPersonDataListCase();
        regs.forEach(reg -> System.out
                .println("id =" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
    }

    // Método para realizar consultas de nombres de personas, nombres únicos de
    // personas, lenguajes de programación únicos y total de lenguajes de
    // programación
    @Transactional(readOnly = true)
    public void personalizedQueriesDistinct() {
        System.out.println("====================== Consultas con nombres de personas ======================");
        // Consulta todos los nombres de personas
        List<String> names = repository.findAllNames();
        names.forEach(System.out::println);

        System.out.println("====================== Consultas con nombres únicos de personas ======================");
        // Consulta nombres únicos de personas
        names = repository.findAllNamesDistinct();
        names.forEach(System.out::println);

        System.out.println("====================== Consultas con Lenguaje unico de personas ======================");
        // Consulta lenguajes de programación únicos
        List<String> language = repository.findAllProgrammingLanguageDistinct();
        language.forEach(System.out::println);

        System.out.println(
                "====================== Consultas con total de lenguajes de programacion unicas ======================");
        // Consulta el total de lenguajes de programación únicos
        Long totalLanguage = repository.findAllProgrammingLanguageDistinctCount();
        System.out.println("Total de lenguajes de programacion: " + totalLanguage);
    }

    // Método para realizar consultas personalizadas que involucran objetos y DTOs
    // personalizados
    @Transactional(readOnly = true)
    public void personalizedQueries2() {
        System.out.println(
                "====================== consulta por objeto persona y lenguaje de programacion ======================");
        // Consulta combinada para obtener objetos de persona y lenguajes de
        // programación
        List<Object[]> personsRegs = repository.finAllMixPerson();
        personsRegs.forEach(reg -> {
            System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
        });

        System.out.println("consulta que puebla y devuelve objeto entity de una instancia personalizada");
        // Consulta para obtener objetos de persona personalizados
        List<Person> persons = repository.findAllObjectPersonPersonalized();
        persons.forEach(System.out::println);

        System.out.println("consulta de puebla y devuelve objeto dto de una clase personalizada");
        // Consulta para obtener DTOs de persona personalizados
        List<PersonDto> personDto = repository.findAllPersonDto();
        personDto.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================== consulta solo el nombre por el id ======================");
        System.out.println("Ingrese el id para el nombre:");
        Long id = scanner.nextLong();
        scanner.close();

        System.out.println("========= mostrando solo el nombre =========");
        // Consulta el nombre de la persona por su ID
        String name = repository.getNameById(id);
        System.out.println(name);

        System.out.println("========= mostrando solo el id =========");
        // Consulta el ID de la persona por su ID
        Long idDb = repository.getIdById(id);
        System.out.println(idDb);

        System.out.println("========= mostrando el nombre completo con concat =========");
        // Consulta el nombre completo de la persona por su ID
        String fullname = repository.getFullNameById(id);
        System.out.println(fullname);

        System.out.println("========= Consulta por campos personalizados por el id =========");
        Optional<Object> optionalReg = repository.obtenerPersonDataById(id);
        if (optionalReg.isPresent()) {
            Object[] personReg = (Object[]) optionalReg.get();
            System.out.println("id =" + personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2]
                    + ", lenguaje=" + personReg[3]);

        }

        System.out.println("===== Consulta por campos personalizados lista =====");
        List<Object[]> regs = repository.obtenerPersonDataList();
        regs.forEach(reg -> System.out
                .println("id =" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
    }

    // Método para eliminar una persona por su ID
    @Transactional
    public void delete() {
        repository.findAll().forEach(System.out::println); // Muestra todas las personas
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id a eliminar:");
        Long id = scanner.nextLong();
        repository.deleteById(id); // Elimina la persona por ID

        repository.findAll().forEach(System.out::println); // Muestra todas las personas después de la eliminación
        scanner.close();
    }

    // Método para eliminar una persona por su ID, usando Optional para verificar su
    // existencia
    @Transactional
    public void delete2() {
        repository.findAll().forEach(System.out::println); // Muestra todas las personas
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id a eliminar:");
        Long id = scanner.nextLong();

        Optional<Person> optionalPerson = repository.findById(id); // Busca la persona por ID
        optionalPerson.ifPresentOrElse(repository::delete,
                () -> System.out.println("Lo sentimos, no existe la persona con ese id")); // Si existe, elimina la
                                                                                           // persona; si no, muestra un
                                                                                           // mensaje de error

        repository.findAll().forEach(System.out::println); // Muestra todas las personas después de la eliminación
        scanner.close();
    }

    // Método para actualizar la información de una persona
    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona:");
        Long id = scanner.nextLong();

        Optional<Person> optionalPerson = repository.findById(id);
        if (optionalPerson.isPresent()) { // Verifica si la persona existe
            Person personDB = optionalPerson.orElseThrow(); // Obtiene la persona de la base de datos
            System.out.println(personDB);

            // Solicita la nueva información de la persona al usuario
            System.out.println("Ingrese el nuevo nombre de la persona");
            String name = scanner.next();
            personDB.setName(name);

            System.out.println("Ingrese el nuevo apellido de la persona");
            String lastname = scanner.next();
            personDB.setLastname(lastname);

            System.out.println("Ingrese el lenguaje de programacion:");
            String programmingLanguage = scanner.next();
            personDB.setProgrammingLanguage(programmingLanguage);

            // Guarda los cambios en la base de datos
            Person personUpdate = repository.save(personDB);
            System.out.println(personUpdate);
        } else {
            System.out.println("El usuario indicado no existe");
        }
        scanner.close();
    }

    // Método para crear una nueva persona
    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre por favor:");
        String name = scanner.next();
        System.out.println("Ingrese el apellido:");
        String lastname = scanner.next();
        System.out.println("Ingrese el lenguaje de programacion:");
        String programmingLanguage = scanner.next();
        scanner.close();

        // Crea una nueva persona y la guarda en la base de datos
        Person person = new Person(null, name, lastname, programmingLanguage);
        Person personNew = repository.save(person);
        System.out.println(personNew);

        repository.findById(personNew.getId()).ifPresent(System.out::println); // Muestra la información de la persona
                                                                               // creada
    }

    // Método para encontrar una persona cuyo nombre contenga cierto valor
    @Transactional(readOnly = true)
    public void findOne() {
        repository.findOneLikeName("Al").ifPresent(System.out::println); // Muestra la persona cuyo nombre contiene "Al"
    }

    // Método para listar personas y sus datos
    @Transactional(readOnly = true)
    public void list() {
        // Busca personas por lenguaje de programación y nombre
        List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Python", "Pepe");
        persons.stream().forEach(person -> {
            System.out.println(person);
        });

        // Obtiene datos específicos de personas por lenguaje de programación
        List<Object[]> personsValues = repository.obtenerPersonDataByProgrammingLanguage("Java");
        personsValues.stream().forEach(person -> {
            System.out.println(person[0] + " es experto en " + person[1]);
        });
    }
}
