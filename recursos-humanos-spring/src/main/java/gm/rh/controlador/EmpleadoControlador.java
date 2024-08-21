package gm.rh.controlador;

import gm.rh.excepcion.RecursoNoEncontradoExcepcion;
import gm.rh.modelo.Empleado;
import gm.rh.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// restcontoller para poder utilizar la fabria de spring, asi mismo recibimos y realizamos
// peticiones http
@RestController
// utilizada para la ruta de nuestra app
// http ://localhost:8080/rh-app/
@RequestMapping("rh-app")
// se utiliza para recibir las peticiones qu estan el react en el puerto que sele asigne en react
@CrossOrigin(value = "http://localhost:3000")

public class EmpleadoControlador {
    // con la variabel de logger ya podemos enviar informacion a consola
    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoControlador.class);

    // nos comunicamos con la capa de servicio
    @Autowired
    private IEmpleadoServicio empleadoServicio;

    // agrgamos una peticion de tipo get para obtener todos los empleados
    @GetMapping("/empleados")
    // http ://localhost:8080/rh-app/empleados ,, asi seria la ruta para realizar una peticon
// metodo para listar todod los empledos
    public List<Empleado> obtenerEmpleados() {
        var empleados = empleadoServicio.listarEmpleados();
        // demanera intermedia podemos listar los empleados
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

    /**
     * agregamos postmapping para agregar la url el empleado
     **/

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        logger.info("empleado a agregar : " + empleado);
        return empleadoServicio.guardarEmpleado(empleado);
    }

    /**
     * para editar un empleado agregamos un metodo get para recuparerar el id
     */

    @GetMapping("/empleados/{id}")

    /**agregamos la respuesta en un metodo publico que se llama ResponseEntity
     y le agregamos la clase Empleado que es la costrusctura */

    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        return ResponseEntity.ok(empleado);

    }
    /** agregamos la notacion @PutMapping para la peticion update */
    @PutMapping("/empleados/{id}")

    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id,
                       @RequestBody Empleado empleadoRecibido){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null)
            throw new RecursoNoEncontradoExcepcion("El ID recibido no es existe: " + id);
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);

    }

    /**notacion para eliminar */
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>>
        eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado == null)
            throw new RecursoNoEncontradoExcepcion("El ID Recibido No Existe: " + id);
        empleadoServicio.eliminarEmpleado(empleado);
        /** la respuesta viene en un formato de tipo json de la siguiente manera
         *{"eliminado":"true"}*/
        Map<String , Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}