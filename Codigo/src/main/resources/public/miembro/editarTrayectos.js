const miembros = JSON.parse(localStorage.getItem("miembros"))
const user = JSON.parse(localStorage.getItem("usuarioLogueado"))
let trayectos = (user.trayectos || []);
let trayectoElegido = "";

cargarTrayectos();


function cargarTrayectos(){
    let html = "";
    trayectos.forEach(t => {
        html += `<div class="opcionTrayecto trayecto${t.id}">
                    <h5 onClick="elegirTrayecto(${t.id})">${t.nombre}</h5>
                </div>`
    })
    document.querySelector("#listaTrayectos").innerHTML = html;
    console.log(trayectos);
}

function elegirTrayecto(id){
    const trayecto = trayectos.find(t => t.id == id);
    document.querySelector("#cantidadTramos").innerHTML = trayecto.tramos.length;
    document.querySelector("#nombreTrayectoElegido").innerHTML = trayecto.nombre;
    trayectoElegido = trayecto;
}

document.querySelector("#btnEditarTrayecto").addEventListener("click", editarTrayecto);

function editarTrayecto(){
    if(trayectoElegido != ""){
        localStorage.setItem("trayectoParaEditar", JSON.stringify(trayectoElegido));
        location.href = "editarTrayecto.html";
    }
    else{
        alert("Por favor, elija un trayecto");
    }
}


document.querySelector("#btnEliminarTrayecto").addEventListener("click", eliminarTrayecto);

function eliminarTrayecto(){
    if(trayectoElegido != ""){
        user.trayectos = trayectos.filter(t => t.id != trayectoElegido.id);
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'TRAYECTO ELIMINADO CON EXITO',
        showConfirmButton: false,
        timer: 1500
    })
    localStorage.setItem("usuarioLogueado", JSON.stringify(user));
    cargarTrayectos();
    document.querySelector(`.trayecto${trayectoElegido.id}`).style.display="none"
    }
    else{
        alert("Por favor, elija un trayecto");
    }

}

document.querySelector("#btnGuardarCambios").addEventListener("click", guardarCambios);

function guardarCambios(){
    miembros[user.id-1] = user;
    localStorage.setItem("miembros", JSON.stringify(miembros));
}