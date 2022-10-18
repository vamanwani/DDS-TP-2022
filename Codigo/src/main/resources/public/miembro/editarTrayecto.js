document.querySelector("#btnAgregarTramo").addEventListener("click", menuEditarTramo);
document.querySelector("#btnVolver").addEventListener("click", menuEditarTrayecto);
document.querySelector("#btnConfirmarTramo").addEventListener("click", confirmarTramo);

document.querySelector("#btnAgregarTrayecto").addEventListener("click", confirmarEdicion);


const user = JSON.parse(localStorage.getItem("usuarioLogueado"))
let trayectos = (user.trayectos || []);
const trayectoElegido = JSON.parse(localStorage.getItem("trayectoParaEditar"));
let tramoElegido = "";


let tramos = trayectoElegido.tramos;
let idContador = 1;
actualizarTramos();

function menuEditarTramo(e){
    e.preventDefault();
    if(tramoElegido != ""){
        document.querySelector(".bloqueAgregarTrayecto").style.display="none";
        document.querySelector(".bloqueAgregarTramo").style.display="block";
        setearFormulario();
    }
    else{
        alert("Por favor, elija un tramo")
    }
}

function setearFormulario(){
    document.querySelector("#valuePuntoInicio").value = tramoElegido.pInicio;
    document.querySelector("#valuePuntoDestino").value = tramoElegido.pFin;
    document.querySelector("#valueMedioTransporte").value = tramoElegido.medio;
    document.querySelector("#valueVehiculo").value = tramoElegido.informacion.tipoParticular;
    document.querySelector("#valueCombustible").value = tramoElegido.informacion.tipoCombustible;
    document.querySelector("#valueLinea").value = tramoElegido.informacion.linea;
    document.querySelector("#valueTransportePublico").value = tramoElegido.informacion.tipoPublico;
    document.querySelector("#valueServicio").value = tramoElegido.informacion.tipoServicio;
    document.querySelector("#valueTransporteAnalogico").value = tramoElegido.informacion.tipoAnalogico;
}

function menuEditarTrayecto(e){
    e.preventDefault();
    document.querySelector(".bloqueAgregarTramo").style.display="none";
    document.querySelector(".bloqueAgregarTrayecto").style.display="block";
}

function confirmarTramo(e){
    e.preventDefault();
    editarTramo();
    menuEditarTrayecto(e);
}

function editarTramo(){
    const editado = tramos.find(t => t.id = tramoElegido.id);
    editado.pInicio = document.querySelector("#valuePuntoInicio").value;
    editado.pFin = document.querySelector("#valuePuntoDestino").value;
    editado.medio = document.querySelector("#valueMedioTransporte").value;
    editado.informacion.tipoParticular = document.querySelector("#valueVehiculo").value;
    editado.informacion.tipoCombustible = document.querySelector("#valueCombustible").value;
    editado.informacion.linea = document.querySelector("#valueLinea").value;
    editado.informacion.tipoPublico = document.querySelector("#valueTransportePublico").value;
    editado.informacion.tipoServicio = document.querySelector("#valueServicio").value;
    editado.informacion.tipoAnalogico = document.querySelector("#valueTransporteAnalogico").value;
    trayectoElegido.tramos = editado;
    actualizarTramos();
}

function actualizarTramos(){
    let html = `<tr>
                <th>Punto Inicio</th>
                <th>Punto Destino</th>
                <th>Medio Transporte</th>
            </tr>`;
    tramos.forEach(t => {
        html+=`<tr class="opcionTramo tramo${t.id}" onClick="elegirTramo(${t.id})">
                <th>${t.pInicio}</th>
                <th>${t.pFin}</th>
                <th>${t.medio}</th>
            </tr>`;
    })
    document.querySelector("#listaTramos").innerHTML = html;
}

function elegirTramo(id){
    for(let i = 1; i <= tramos.length; i++){
        document.querySelector(`.tramo${i}`).style.color="black";
    }
    document.querySelector(`.tramo${id}`).style.color="green";
    const tramo = tramos.find(t => t.id == id);
    tramoElegido = tramo;
}

function confirmarEdicion(){
    const tEditado = user.trayectos.find(t => t.id == trayectoElegido.id);
    tEditado.tramos = tramos;
    localStorage.setItem("usuarioLogueado", JSON.stringify(user));
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'TRAYECTO EDITADO CON EXITO',
        showConfirmButton: false,
        timer: 1500
    })
    setTimeout(() => {
        location.href = "editarTrayectos.html";
    }, 1500)
}