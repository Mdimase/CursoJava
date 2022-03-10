// Call the dataTables jQuery plugin
$(document).ready(function() {  //ejecuta una vez cargada la pagina
    //on ready!!!
});


//register con fetch
async function registrarUsuario(){
    let datos = {};
    //obtener valores de los inputs del form
    datos.nombre = document.getElementById("txtNombre").value;
    datos.apellido = document.getElementById("txtApellido").value;
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;

    // verificar igualdad de password
    let repetirPassword = document.getElementById("txtRepetirPassword").value;
    if(datos.password != repetirPassword){
        alert("las password no coinciden");
        return;
    }

    //peticion asincrona
    const request = await fetch('usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos) //convertir JSON a string
      });
      alert("cuenta creada");
      window.location.href = "login.html";
}