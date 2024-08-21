package gm.rh.servicio;

import gm.rh.modelo.Empleado;
import gm.rh.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
/**implementamos la clase de interface recien creada **/
public class EmpleadoServicio implements IEmpleadoServicio {
// con el auto inyectar, el repositorio se comunicara con el servicio y asi mismo el servicio con la base de datos

    @Autowired
// creamos un atributo privado, co este metodo nos comunicamos con la capa de datos
    private EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarEmpleados() {
        // retornamos  return empleadoRepositorio.findAll(); y esto nos retornada objetos de tipo empleados
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        Empleado empleado = empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepositorio.delete(empleado);
    }
}
