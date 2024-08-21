package gm.rh.servicio;

import gm.rh.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    // agragamos el metodo para listar todos los empleados, va hacer una lista publica
    public List<Empleado> listarEmpleados();
    // agregamos el motod para buscar empleado por id

    public Empleado buscarEmpleadoPorId(Integer idEmpleado);
    // agragamnos mettodo para guardar empleado este metodo se encargar de cuadar y actualizar
    public Empleado guardarEmpleado(Empleado empleado);
    // agregamos el metdod de eliminar

    public void eliminarEmpleado(Empleado empleado);


}
