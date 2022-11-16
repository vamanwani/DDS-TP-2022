document.querySelector("#btnAgregarTramo").addEventListener("click", menuAgregarTramo);
document.querySelector("#btnVolver").addEventListener("click", menuAgregarTrayecto);
//document.querySelector("#btnConfirmarTramo").addEventListener("click", confirmarTramo);

//document.querySelector("#btnAgregarTrayecto").addEventListener("click", agregarTrayecto);


let tramos = [];
const user = JSON.parse(localStorage.getItem("usuarioLogueado"))
let trayectos = (user.trayectos || []);
let idContador = 1;
let idTrayectoContador = trayectos.length + 1;

function menuAgregarTramo(e){
    e.preventDefault();
    document.querySelector(".bloqueAgregarTrayecto").style.display="none";
    document.querySelector(".bloqueAgregarTramo").style.display="block";
}

function menuAgregarTrayecto(e){
    e.preventDefault();
    document.querySelector(".bloqueAgregarTramo").style.display="none";
    document.querySelector(".bloqueAgregarTrayecto").style.display="block";
}

function confirmarTramo(e){
    e.preventDefault();
    agregarTramo();
    menuAgregarTrayecto(e);
}

function agregarTramo(){
    const puntoInicio = document.querySelector("#valuePuntoInicio").value;
    const puntoFin = document.querySelector("#valuePuntoDestino").value;
    const medio = document.querySelector("#valueMedioTransporte").value;
    tramos.push({
        id: idContador,
        pInicio: puntoInicio,
        pFin: puntoFin,
        medio: medio,
        informacion: {
            tipoParticular: document.querySelector("#valueVehiculo").value,
            tipoCombustible: document.querySelector("#valueCombustible").value,
            linea: document.querySelector("#valueLinea").value,
            tipoPublico: document.querySelector("#valueTransportePublico").value,
            tipoServicio: document.querySelector("#valueServicio").value,
            tipoAnalogico: document.querySelector("#valueTransporteAnalogico").value
        }});
    console.log(tramos);
    idContador++;
    actualizarValores();
    actualizarTramos();
}

function actualizarValores(){
    document.querySelector("#valuePuntoInicio").value = "";
    document.querySelector("#valuePuntoDestino").value = "";
    document.querySelector("#valueMedioTransporte").value = "";
    document.querySelector("#valueVehiculo").value = "";
    document.querySelector("#valueCombustible").value = "";
    document.querySelector("#valueLinea").value = "";
    document.querySelector("#valueTransportePublico").value = "";
    document.querySelector("#valueServicio").value = "";
    document.querySelector("#valueTransporteAnalogico").value = "";
}

function actualizarTramos(){
    let html = `<tr>
                <th>Punto Inicio</th>
                <th>Punto Destino</th>
                <th>Medio Transporte</th>
            </tr>`;
    tramos.forEach(t => {
        html+=`<tr>
                <th>${t.pInicio}</th>
                <th>${t.pFin}</th>
                <th>${t.medio}</th>
            </tr>`;
    })
    document.querySelector("#listaTramos").innerHTML = html;
}

function agregarTrayecto(){
    trayectos.push({
        id: idTrayectoContador,
        nombre: document.querySelector("#exampleFormControlInput1").value,
        tramos: tramos
    })
    localStorage.setItem("usuarioLogueado", JSON.stringify(user));
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'TRAYECTO GUARDADO CON EXITO',
        showConfirmButton: false,
        timer: 1500
    })
    setTimeout(() => {
        location.href = "editarTrayectos.html";
    }, 1500)
}