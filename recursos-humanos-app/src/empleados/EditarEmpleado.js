import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditarEmpleado() {
  // agregamos la ruta base que usaremos para actulizar el empleado
  const urlBase = "http://localhost:8080/rh-app/empleados";

  const navegacion = useNavigate();

const {id} = useParams();

  const [empleado, setEmpleado] = useState({
    nombre: "",
    departamento: "",
    sueldo: "",
  });

  const { nombre, departamento, sueldo } = empleado;

  useEffect(()=>{
    cargarEmpleado();
  },[])

  const cargarEmpleado = async () =>{
    const resultado = await axios.get(`${urlBase}/${id}`)
    setEmpleado(resultado.data)
  }

  const onInputChange = (e) => {
    // utilisamos  el operador spread operator ... que se utiliza para expandir los atributos
    setEmpleado({ ...empleado, [e.target.name]: e.target.value });
    // setEmpleado({...empleado, [e.target.name]: e.target.value})
    // con esta line de codigo se actualiza el estado del empleado, viendo si corresponde el valor y se actualiza
  };
  const onSubmit = async (e) => {
    e.preventDefault();
    // con esta funcion predeteminada se oculta la infomacion en la url preventDefault()

    await axios.put(`${urlBase}/${id}`, empleado);
    // redirigimos a la pagina de inicio
    navegacion("/");
  };

  return (
    <div className="container">
      <div className="container text-center" style={{ margin: "30px" }}>
        <h3>Actualizar Empleado</h3>
      </div>
      <form onSubmit={(e) => onSubmit(e)}>
        <div className="mb-3">
          <label htmlFor="nombre" className="form-label">
            Nombre
          </label>
          <input
            type="text"
            className="form-control"
            id="nombre"
            name="nombre"
            required
            value={nombre}
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="departamento" className="form-label">
            Departamento
          </label>
          <input
            type="text"
            className="form-control"
            id="departamento"
            name="departamento"
            required
            value={departamento}
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="sueldo" className="form-label">
            Salario
          </label>
          <input
            type="number"
            step="any"
            className="form-control"
            id="sueldo"
            name="sueldo"
            required
            value={sueldo}
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="text-center">
          <button type="submit" className="btn btn-warning btn-sm me-3">
            Guardar
          </button>
          <a href="/" className="btn btn-danger btn-sm">
            Regresar
          </a>
        </div>
      </form>
    </div>
  );
}

export default EditarEmpleado;
