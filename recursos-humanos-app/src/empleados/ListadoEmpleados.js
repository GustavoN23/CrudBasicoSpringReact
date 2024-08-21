import axios from "axios";
import React, { useEffect, useState } from "react";
import { NumericFormat } from "react-number-format";
import { Link } from "react-router-dom";

export default function ListadoEmpleados() {
  // agregamos un hut para la comunicacion de la intreface con el back-end

  const urlBase = "http://localhost:8080/rh-app/empleados";
  // definimoa un arreglo que s del consepto de hub

  const [empleados, setEmpleados] = useState([]);

  useEffect(() => {
    cargarEmpleados();
  }, []);

  const cargarEmpleados = async () => {
    const resultado = await axios.get(urlBase);
    console.log("Resultados cargar empleados");
    console.log(resultado.data);
    setEmpleados(resultado.data);
  };
  const eliminarEmpleado = async (id) =>{
    await axios.delete(`${urlBase}/${id}`);
      cargarEmpleados();
    
  }

  return (
    <div className="container">
      <div className="container text-center " style={{ margin: "30px" }}>
        <h1>Sistemas de Recursos Humanos</h1>
      </div>
      {/* agragamos una tabla de bootratp */}
      <table className="table table-striped table-hover align-middle">
        <thead className="table-dark">
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Empleado</th>
            <th scope="col">Departamento</th>
            <th scope="col">Salario</th>
            <th scope="col">Salario</th>
          </tr>
        </thead>
        <tbody>
          {/* creamos la el codigo para que se liste los empleados de manera  dinamica  
         IteraNDO EL ARREGLO mapeando el mismo  */}

          {empleados.map((empleado, indice) => (
            <tr key={indice}>
              <th scope="row">{empleado.idEmpleado}</th>
              <td>{empleado.nombre}</td>
              <td>{empleado.departamento}</td>
              <td>
                <NumericFormat
                  value={empleado.sueldo}
                  displayType={"text"}
                  thousandSeparator=","
                  prefix={"$"}
                  decimalScale={2}
                  fixedDecimalScale
                />
              </td>
              <td className="text-center">
                <div>
                  <Link to={`/editar/${empleado.idEmpleado}`} className="btn btn-warning btn-sm me-3">Actualizar</Link>
                  <button onClick={()=>eliminarEmpleado(empleado.idEmpleado)} to={`/eliminar/${empleado.idEmpleado}`} className="btn btn-danger btn-sm">Eliminar</button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
