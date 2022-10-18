const user = JSON.parse(localStorage.getItem("usuarioLogueado"))
const organizaciones = JSON.parse(localStorage.getItem("organizaciones"))
let solicitantes
let aceptados = [];
let seleccionados = [];
let solicElegido = "";

document.querySelector("#aceptarVinculaciones").addEventListener("click", aceptarSeleccionado);
document.querySelector("#rechazarVinculaciones").addEventListener("click", rechazarSeleccionado);

actualizarSolicitantes();


function aceptarSeleccionado(e){
    if(solicElegido == ""){
        alert("Por favor, elija a un solicitante")
    }
    else{
        organizaciones[user.id-1].solicitantes = organizaciones[user.id-1].solicitantes.filter(s => s.id != user.id);
    organizaciones[user.id-1].miembros.push(solicElegido);
    user.solicitantes = organizaciones[user.id-1].solicitantes;
    localStorage.setItem("organizaciones", JSON.stringify(organizaciones));
    localStorage.setItem("usuarioLogueado", JSON.stringify(user));
    actualizarSolicitantes();
    alert("Solicitante aceptado");
    }
    e.preventDefault();

}

function rechazarSeleccionado(e){
    if(solicElegido == ""){
        alert("Por favor, elija a un solicitante")
    }
    else{
        organizaciones[user.id-1].solicitantes = organizaciones[user.id-1].solicitantes.filter(s => s.id != user.id);
        user.solicitantes = organizaciones[user.id-1].solicitantes;
        localStorage.setItem("organizaciones", JSON.stringify(organizaciones));
        localStorage.setItem("usuarioLogueado", JSON.stringify(user));
        actualizarSolicitantes();
        alert("Solicitante rechazado");
    }
    e.preventDefault();
}

function seleccionar(e, id){
    e.preventDefault();
    seleccionados.push(solicitantes.find(s => s.id == id));
    document.querySelector(`solicitante${s.id}`).classList.add("seleccionado");
}

function actualizarSolicitantes(){
    let html = `<tr>
    <th>Nombre</th>
    <th>Email</th>
    <th>Teléfono</th>
</tr>"`;
    solicitantes = user.solicitantes;
    solicitantes.forEach(s => {
        html += `<tr id="solicitante${s.id}" class="solicitante" onClick="elegirSolicitante(${s.id})">
                <th>${s.nombre}</th>
                <th>${s.email}</th>
                <th>${s.telefono}</th>
            </tr>`
        document.querySelector("#listaSolicitantes").innerHTML = html;
    })
}

function elegirSolicitante(id){
    for(let i = 1; i <= solicitantes.length; i++){
        document.querySelector(`#solicitante${i}`).style.color="black";
    }
    document.querySelector(`#solicitante${id}`).style.color="green";
    const solic = solicitantes.find(o => o.id == id);
    solicElegido = solic;
}