let login = document.querySelector("#login")
let createCount = document.querySelector("#crearCuenta")

const miembros = (JSON.parse(localStorage.getItem("miembros")) || []);
const organizaciones = (JSON.parse(localStorage.getItem("organizaciones")) || []);
localStorage.setItem("usuarioLogueado", JSON.stringify(""));
let idMiembro = 1;
let idOrganizacion = 1;

function irACrearCuenta(){
    login.style.display="none";
    createCount.style.display="flex";
}

function regresarALogin(){
    login.style.display="flex";
    createCount.style.display="none";
}

const esMiembro = (usuario, clave) => {
    return miembros.some(m => m.nombre == usuario && m.clave == clave);
}

const esOrganizacion = (usuario, clave) => {
    return organizaciones.some(o => o.nombre == usuario && o.clave == clave);
}



function logueo(e){
    e.preventDefault();
    let usuario = document.querySelector("#userName").value;
    let clave = document.querySelector("#userPassword").value;
    if(esMiembro(usuario, clave)){
        alert("Usuario " + usuario + " logueado con éxito");
        localStorage.setItem("usuarioLogueado", JSON.stringify(miembros.find(m => m.nombre == usuario)));
        setTimeout(() => {
            location.href = "../miembro/indexMiembro.html";
        }, 1500)
    }
    else if(esOrganizacion(usuario, clave)){
        alert("Usuario " + usuario + " logueado con éxito");
        localStorage.setItem("usuarioLogueado", JSON.stringify(organizaciones.find(m => m.nombre == usuario)));
        setTimeout(() => {
            location.href = "../organizacion/indexOrg.html";
        }, 1500)
    }
    else {
        alert("ERROR, usuario y/o contraseña incorrectos");
    }
}

const estaEntreLasPeoresClaves = (clave) => {
    return clave == "1234"; //TODO FIJARSE EN EL ARCHIVO DE 10000 PEORES CONTRASEÑAS
}

function registro(e){
    e.preventDefault();
    let usuario = document.querySelector("#newUserName").value;
    let clave = document.querySelector("#newUserPassword").value;
    let email = document.querySelector("#newUserEmail").value;
    let telefono = document.querySelector("#newUserPhone").value;
    let tipoUsuario = document.querySelector("#userType").value;
    if(!esMiembro(usuario, clave) || !esOrganizacion(usuario, clave)){
        if(estaEntreLasPeoresClaves(clave)){
            alert("ERROR, clave inválida");
        }
        else{
            if(tipoUsuario == ""){
                alert("ERROR, elija tipo de usuario");
            }
            else if(tipoUsuario == "Miembro"){
                miembros.push({
                    id: idMiembro,
                    nombre: usuario,
                    clave: clave,
                    email: email,
                    telefono: telefono,
                    trayectos: []
                })
                idMiembro++;
                localStorage.setItem("usuarioLogueado", JSON.stringify(miembros.find(m => m.nombre == usuario)));
                localStorage.setItem("miembros", JSON.stringify(miembros));
                setTimeout(() => {
                    location.href = "../miembro/indexMiembro.html";
                }, 1500)
            }
            else{
                organizaciones.push({
                    id: idOrganizacion,
                    nombre: usuario,
                    clave: clave,
                    email: email,
                    telefono: telefono,
                    miembros: [],
                    solicitantes: []
                })
                idOrganizacion++;
                localStorage.setItem("usuarioLogueado", JSON.stringify(organizaciones.find(m => m.nombre == usuario)));
                localStorage.setItem("organizaciones", JSON.stringify(organizaciones));
                setTimeout(() => {
                    location.href = "../organizacion/indexOrg.html";
                }, 1500)
            }
            alert("Bienvenido " + usuario);
        }
    }
    else{
        alert("ERROR, usuario ya registrado en el sistema")
    }
}


let crearCuenta = document.querySelector(".botonCrearCuenta");
crearCuenta.addEventListener("click", irACrearCuenta);

let volverALogin = document.querySelector(".botonVolver");
volverALogin.addEventListener("click", regresarALogin);

document.querySelector("#formularioLogin").addEventListener("submit", logueo);
document.querySelector("#formularioRegistro").addEventListener("submit", registro);