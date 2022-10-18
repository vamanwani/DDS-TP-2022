const organizaciones = JSON.parse(localStorage.getItem("organizaciones"))
const user = JSON.parse(localStorage.getItem("usuarioLogueado"))
actualizarOrganizaciones();
let orgElegida = "";

function actualizarOrganizaciones(){
    let html = `<tr>
                <th>Nombre</th>
                <th>Cant. Miembros</th>
            </tr>`;
    organizaciones.forEach(o => {
        html+=`<tr class="opcionOrg org${o.id}" onClick="elegirOrg(${o.id})">
                <th>${o.nombre}</th>
                <th>${o.miembros.length}</th>
            </tr>`;
    })
    document.querySelector("#listaOrganizaciones").innerHTML = html;
}

function elegirOrg(id){
    for(let i = 1; i <= organizaciones.length; i++){
        document.querySelector(`.org${i}`).style.color="black";
    }
    document.querySelector(`.org${id}`).style.color="green";
    const org = organizaciones.find(o => o.id == id);
    orgElegida = org;
}

document.querySelector("#btnSolicitarUnirse").addEventListener("click", solicitarUnirse);

function solicitarUnirse(){
    if(orgElegida == ""){
        alert("Por favor, ingrese una organización")
    }
    else if(organizaciones[orgElegida.id - 1].solicitantes.some(s => s.id == user.id)){
        alert("Usted ya tiene una solicitud pendiente con esta organización")
    }
    else{
        organizaciones[orgElegida.id - 1].solicitantes.push(user);
        console.log(organizaciones)
        localStorage.setItem("organizaciones", JSON.stringify(organizaciones));
    }
}